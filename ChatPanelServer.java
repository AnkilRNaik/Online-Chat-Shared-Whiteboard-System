

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class ChatPanelServer extends JPanel{
	
	MainFrameServer mainFrame;
	JTextField textField;
	JTextArea textArea;
	@SuppressWarnings("rawtypes")
	JList list;
	JLabel lable = new JLabel("     The ChatBox                                                                  Online Users");
	@SuppressWarnings("rawtypes")
	DefaultListModel allUsers;
	@SuppressWarnings("rawtypes")
	ArrayList username;
	
	public ChatPanelServer (){
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChatPanelServer (final MainFrameServer mainFrame){
		this.mainFrame = mainFrame;
		
		setLayout(new BorderLayout());
		allUsers = new DefaultListModel();
		list = new JList(allUsers);
		username = new ArrayList();
		
		textField = new JTextField("...");
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = getCurUser() + " : " + textField.getText();
				textField.setText("");
				final ChatMessage msg = new ChatMessage();
				msg.setMessage(text);
				mainFrame.sendMessage(msg);
				
			}
		});
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		add(textField, BorderLayout.SOUTH);
		add(textArea, BorderLayout.CENTER);
		add(lable, BorderLayout.NORTH);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(list, BorderLayout.EAST);
		
	}
	
	public void appendMessage(String m) {
		textArea.append(m + "\n");
	}

	public Dimension getPreferredSize() {
		return new Dimension(470, 100);
	}

	public Dimension getMinimumSize() {
		return new Dimension(470, 100);
	}
	
	public String getCurUser() {
		return list.getSelectedValue().toString();
	}

}
