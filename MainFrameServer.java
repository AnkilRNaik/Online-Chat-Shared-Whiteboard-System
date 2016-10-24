

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MainFrameServer extends JFrame implements Runnable, ListSelectionListener{
	
	ChatPanelServer chatPanel;
	DrawPanelServer drawPanel;
	ControlPanelServer conPanel;
	boolean connected;
	ObjectOutputStream objOutStr;
	ObjectInputStream objInStr;
	Socket socket;
	String user;
	
	public MainFrameServer(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chatPanel = new ChatPanelServer(this);
		conPanel = new ControlPanelServer(this);
		drawPanel = new DrawPanelServer(this);
		
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBackground(Color.gray);
		
		container.add(chatPanel, BorderLayout.WEST);
		container.add(drawPanel, BorderLayout.EAST);
		getContentPane().add(container, BorderLayout.CENTER);
		
		getContentPane().add(conPanel, BorderLayout.SOUTH);
		conPanel.connectBtn.setVisible(true);
		conPanel.disConnectBtn.setVisible(false);
		conPanel.clearBtn.setVisible(false);
		conPanel.sendDrawBtn.setVisible(false);
		conPanel.loginBtn.setVisible(false);
		conPanel.logOutBtn.setVisible(false);
		conPanel.loadBtn.setVisible(false);
		conPanel.saveBtn.setVisible(false);
		
		setSize(1000, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		@SuppressWarnings("unused")
		MainFrameServer frame = new MainFrameServer("Chat/Shared Whiteboard System");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			objInStr = new ObjectInputStream(socket.getInputStream());
			for (;;) {
				Object o = receiveMessage();
				if (o != null) {
					if (o instanceof ChatMessage) {
						ChatMessage cm = (ChatMessage) o;
						String s = (String) cm.getMessage();
						System.out.println(s);
						chatPanel.appendMessage(s);
					} else if (o instanceof LineMessage) {
						LineMessage lineMsg = (LineMessage) o;
						ArrayList<Line> linelist = (ArrayList) lineMsg.getMessage();
						drawPanel.linelist = linelist;
						drawPanel.repaint();
					}  else if (o instanceof UserMessage2) {
						UserMessage2 um=(UserMessage2) o;
						String s=(String) um.getMessage();
						System.out.println(o + "Savemessage");
						chatPanel.textArea.setText("");
						chatPanel.appendMessage(s);
					}
					 else if(o instanceof String)
                     {
						 
                         chatPanel.appendMessage((String)o);
                     }
				} else {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception: " + e.getMessage());
		} finally {
			try {
				objInStr.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public Object receiveMessage() {

		Object obj = null;
		try {
			obj = objInStr.readObject();
			if (obj instanceof ChatMessage) {
				ChatMessage sm = (ChatMessage) obj;
				String s = (String) sm.getMessage();
				System.out.println("Message is: " + s);
			}
		} catch (IOException e) {
			System.out.println("End of stream.");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return obj;
	}
	
	final public void sendMessage(Object o) {

		if (isConnected()) {
			if (o instanceof LineMessage)
				System.out.println("LineMessage written to stream");
			try {

				objOutStr.writeObject(o);

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void setConnected(boolean c) {
		connected = c;
	}
	
	public void connect() {
		setConnected(true);
		System.out.println("Trying to Connect Server...");
		try {
			socket = new Socket("afsaccess2.njit.edu", 9990);

			objOutStr = new ObjectOutputStream(socket.getOutputStream());

			System.out.println("Connected to Server...");

			new Thread(this).start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close() {
		setConnected(false); 
		try {
			objOutStr.close();
			System.out.println("Connection closed . . . ");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void load() {
		new Thread(this).start();
	}
	
	public void clear() {
		chatPanel.textArea.setText("");
		drawPanel.linelist = new ArrayList<Line>();
		drawPanel.repaint();
	}
	
	@SuppressWarnings("unchecked")
	final public void login(Object o) {

		if (isConnected()) {

			System.out.println("Trying To Login.");
			user = JOptionPane.showInputDialog("Please enter your username");
			chatPanel.textArea.append(user + " just joined the chat." + "\n");
			JOptionPane.showMessageDialog(null, "Welcome to the chat system : " + user);
			chatPanel.allUsers.addElement(user);
		}
	}
	
	final public boolean logout(Object o) {
		
		if (isConnected()) {
			chatPanel.textArea.setText("");
			String curUser;
			curUser = chatPanel.getCurUser();
			int reply = JOptionPane.showConfirmDialog(null, "Really want to quit ?", "LogOut", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				chatPanel.allUsers.removeElement(curUser);
			}

			else
				return false;
		}
		return false;
	}
	
	public void savechat() {
		String text = chatPanel.textArea.getText();
		UserMessage2 save = new UserMessage2();
		System.out.println(text);
		save.setMessage(text);
		sendMessage(save);
	}
	
	@SuppressWarnings("resource")
	public void loadchat() throws IOException {
		objOutStr.writeObject("load");
		}
	

}
