

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class DrawPanelServer extends JPanel implements MouseListener, MouseMotionListener, ListSelectionListener{
	
	ArrayList<Line> linelist;
	int lastX, lastY;
	MainFrameServer mainFrame;
	@SuppressWarnings("rawtypes")
	JList list;
	@SuppressWarnings("rawtypes")
	DefaultListModel allColors;
	
	public DrawPanelServer() {
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DrawPanelServer(final MainFrameServer mainFrame) {
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setBackground(Color.WHITE);
		linelist = new ArrayList<Line>();
		this.mainFrame = mainFrame;
		
		allColors=new DefaultListModel();
		
		allColors.addElement("Black");
		allColors.addElement("Yellow");
		allColors.addElement("Red");
		allColors.addElement("Orange");
		allColors.addElement("Light Gray");
		
		list=new JList(allColors);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.setBounds(20,90,100,180);
		list.setBackground(Color.WHITE);
		list.setForeground(Color.BLACK);
		//list.setLayoutOrientation(list.HORIZONTAL_WRAP);
		list.setSelectedIndex(0);
		
		add(list);
	
		list.addListSelectionListener(new ListSelectionListener() {

	         public void valueChanged(ListSelectionEvent arg0) {
	        	 switch(list.getSelectedIndex()) {
	     		case 0:
	     			setForeground(Color.BLACK);
	     			break;
	     		case 1:
	     			setForeground(Color.YELLOW);
	     			break;
	     		case 2:
	     			setForeground(Color.RED);
	     			break;
	     		case 3:
	     			setForeground(Color.ORANGE);
	     			break;
	     		case 4:
	     			setForeground(Color.LIGHT_GRAY);
	     			break;
	     		}
	        	 switch(list.getSelectedIndex()) {
	     		case 0:
	     			list.setSelectionForeground(Color.BLACK);
	     			break;
	     		case 1:
	     			list.setSelectionForeground(Color.YELLOW);
	     			break;
	     		case 2:
	     			list.setSelectionForeground(Color.RED);
	     			break;
	     		case 3:
	     			list.setSelectionForeground(Color.ORANGE);
	     			break;
	     	
	     		case 4:
	     			list.setSelectionForeground(Color.LIGHT_GRAY);
	     			break;
	     		}
	         }
	     });

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		Integer i = allColors.getSize();
		if(i%2==1)
			this.setForeground(Color.GREEN);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int endX = e.getX();
		int endY = e.getY();
		Line line = new Line(lastX, lastY, endX, endY);
		linelist.add(line);
		lastX = endX;
		lastY = endY;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		lastX = e.getX();
		lastY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Iterator<Line> it = linelist.iterator();
		while (it.hasNext()) {
			Line current = it.next();
			g.drawLine(current.getStartX(), current.getStartY(), current.getEndX(), current.getEndY());
		}
		Font f=new Font("Times New Roman", Font.BOLD,15);
		g.setFont(f);
		g.setColor(Color.BLACK);
		g.drawString("Please pick a color to draw.", 20, 20);
		
	}

	public Dimension getPreferredSize() {
		return new Dimension(470,300);
	}

	public Dimension getMinimumSize() {
		return new Dimension(470, 300);
	}

}
