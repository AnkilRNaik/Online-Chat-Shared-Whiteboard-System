

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanelServer extends JPanel{
	
	JButton connectBtn, disConnectBtn, clearBtn, sendDrawBtn, loginBtn, logOutBtn, loadBtn, saveBtn;
	MainFrameServer mainFrame;
	ChatPanelServer chatPanel;
	DrawPanelServer drawPanel;
	
	public ControlPanelServer(MainFrameServer mf){
		
		mainFrame = mf;
		drawPanel = new DrawPanelServer(mainFrame);
		
		connectBtn = new JButton("Connect");
		disConnectBtn = new JButton("Disconnect");
		clearBtn = new JButton("Clear");
		sendDrawBtn = new JButton("Send Drawing");
		loginBtn = new JButton("Login");
		logOutBtn = new JButton("Logout");
		loadBtn = new JButton("Load");
		saveBtn = new JButton("Save");
		
		connectBtn.setBackground(Color.BLACK);
		connectBtn.setForeground(Color.WHITE);
		connectBtn.setOpaque(true);
		connectBtn.setBorderPainted(false);
		
		disConnectBtn.setBackground(Color.BLACK);
		disConnectBtn.setForeground(Color.WHITE);
		disConnectBtn.setOpaque(true);
		disConnectBtn.setBorderPainted(false);
		
		clearBtn.setBackground(Color.BLACK);
		clearBtn.setForeground(Color.WHITE);
		clearBtn.setOpaque(true);
		clearBtn.setBorderPainted(false);
		
		sendDrawBtn.setBackground(Color.BLACK);
		sendDrawBtn.setForeground(Color.WHITE);
		sendDrawBtn.setOpaque(true);
		sendDrawBtn.setBorderPainted(false);
		
		loginBtn.setBackground(Color.BLACK);
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setOpaque(true);
		loginBtn.setBorderPainted(false);
		
		logOutBtn.setBackground(Color.BLACK);
		logOutBtn.setForeground(Color.WHITE);
		logOutBtn.setOpaque(true);
		logOutBtn.setBorderPainted(false);
		
		loadBtn.setBackground(Color.BLACK);
		loadBtn.setForeground(Color.WHITE);
		loadBtn.setOpaque(true);
		loadBtn.setBorderPainted(false);
		
		saveBtn.setBackground(Color.BLACK);
		saveBtn.setForeground(Color.WHITE);
		saveBtn.setOpaque(true);
		saveBtn.setBorderPainted(false);
		
		connectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mainFrame.connect();
				connectBtn.setVisible(false);
				disConnectBtn.setVisible(true);
				clearBtn.setVisible(true);
				sendDrawBtn.setVisible(true);
				loginBtn.setVisible(true);
				logOutBtn.setVisible(true);
				loadBtn.setVisible(true);
				saveBtn.setVisible(true);
			}
		});
		disConnectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mainFrame.close();
				connectBtn.setVisible(true);
				disConnectBtn.setVisible(false);
				clearBtn.setVisible(false);
				sendDrawBtn.setVisible(false);
				loginBtn.setVisible(false);
				logOutBtn.setVisible(false);
				loadBtn.setVisible(false);
			}
		});

		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mainFrame.clear();
			}
		});
		
		sendDrawBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//mainFrame.connect();
				/*drawPanel = new DrawPanelServer(mainFrame);
				drawPanel.setEnabled(true);
				drawPanel.list.setEnabled(true);
				drawPanel.setBackground(Color.WHITE);*/
				LineMessage lm = new LineMessage();
				lm.setMessage(mainFrame.drawPanel.linelist);
				mainFrame.sendMessage(lm);
				
			}
		});

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mainFrame.login(ae);
				logOutBtn.setVisible(true);
			}
		});

		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				mainFrame.logout(ae);
				loginBtn.setVisible(true);
				logOutBtn.setVisible(false);

				connectBtn.setVisible(true);
				disConnectBtn.setVisible(false);
				clearBtn.setVisible(false);
				sendDrawBtn.setVisible(false);
				loginBtn.setVisible(false);
				logOutBtn.setVisible(true);
				loadBtn.setVisible(false);
				saveBtn.setVisible(false);
			}
		});

		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					mainFrame.loadchat();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mainFrame.savechat();

			}
		});
		add(connectBtn);
		add(loginBtn);
		add(clearBtn);
		add(loadBtn);
		add(saveBtn);
		add(logOutBtn);
		add(disConnectBtn);
		add(sendDrawBtn);
		
	}

}
