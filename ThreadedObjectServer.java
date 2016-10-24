import java.util.*;
import java.io.*;
import java.net.*;

public class ThreadedObjectServer{  
	public static void main(String[] args ){  
		ArrayList<ThreadedObjectHandler> handlers = new ArrayList<ThreadedObjectHandler>();
		try{  
			ServerSocket s = new ServerSocket(9990);
			for (;;){
				Socket incoming = s.accept( );
				new ThreadedObjectHandler(incoming, handlers).start();
			}   
		}
		catch (Exception e){  
			System.out.println(e);
		} 
	} 
}

class ThreadedObjectHandler extends Thread{ 
	Object myObject = null;
	private Socket incoming;
	ArrayList<ThreadedObjectHandler> handlers;
	ObjectInputStream in;
	ObjectOutputStream out;

	public ThreadedObjectHandler(Socket incoming, ArrayList<ThreadedObjectHandler> handlers){ 
		this.incoming = incoming;
		this.handlers = handlers;
		handlers.add(this);
	}
	
	public synchronized void broadcast(Object obj){
		Iterator<ThreadedObjectHandler> it = handlers.iterator();
		while(it.hasNext()){
			ThreadedObjectHandler current = it.next();
			try{
				current.out.writeObject(obj);
				current.out.reset();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}
   
	public void run(){  
		try{ 	
			in = new ObjectInputStream(incoming.getInputStream());

			out = new ObjectOutputStream(incoming.getOutputStream());
			String s=new String();
			for(;;){
				myObject = in.readObject();
				if (myObject instanceof UserMessage2)
				{
					UserMessage2 um=(UserMessage2)myObject;
					s=(String) um.getMessage();
					//System.out.println(s);
					savemessage(s);
				}
				else if(myObject.toString().contains("load"))
                {
                	FileInputStream fstream = new FileInputStream("SavedChat.txt");
        			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        			UserMessage2 um=new UserMessage2();
        			
        			while ((s = br.readLine()) != null) {
        				um.setMessage(s);
        				out.writeObject(s);
        				
        			}
                }
				broadcast(myObject);
			}		    
		}catch (Exception e){  
			System.out.println(e);
		}finally{
			handlers.remove(this);
			try{
				in.close();
				out.close();
				incoming.close();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	}

	private void savemessage(String s) throws FileNotFoundException {
		// TODO Auto-generated method stub
		PrintWriter pm=new PrintWriter("SavedChat.txt");
		pm.println(s);
		System.out.println(s);
		pm.close();
	}
}
