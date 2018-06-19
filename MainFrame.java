import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ChessBoard chessBoard;
	private JPanel toolBar;
	private JButton startB;
	private JButton backB;
	private JButton exitB;
	private JMenuBar menuBar;
	private JMenu sysMenu;
	private JMenuItem startMenuItem;
	private JMenuItem backMenuItem;
	private JMenuItem exitMenuItem;
	
	public MainFrame (){
		setTitle("BWGame");
		chessBoard = new ChessBoard();
		menuBar = new JMenuBar();
		sysMenu = new JMenu("System");
		startMenuItem = new JMenuItem("ReStart");
		backMenuItem = new JMenuItem("Back");
		exitMenuItem = new JMenuItem("Exit");
		sysMenu.add(startMenuItem);
		sysMenu.add(backMenuItem);
		sysMenu.add(exitMenuItem);
		menuBar.add(sysMenu);
		setJMenuBar(menuBar);
		
		//setSize(600,650);
		pack();
		
		MyItemListener lis = new MyItemListener();
		
		startMenuItem.addActionListener(lis);
		backMenuItem.addActionListener(lis);
		exitMenuItem.addActionListener(lis);
		
		toolBar = new JPanel();
		startB = new JButton("ReStart");
		backB = new JButton("Back");
		exitB = new JButton("Exit");
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(startB);
		toolBar.add(backB);
		toolBar.add(exitB);
		
		startB.addActionListener(lis);
		backB.addActionListener(lis);
		exitB.addActionListener(lis);
		
		add(toolBar, BorderLayout.SOUTH);
		add(chessBoard);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class MyItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj==MainFrame.this.startMenuItem || obj==startB){
				System.out.println("restarting....");
				chessBoard.Restart();
			}
			else if(obj== exitB || obj == exitMenuItem){
				System.exit(0);
			}
			else if(obj== backB || obj == backMenuItem){
				chessBoard.goBack();
			}
		}
		
	}
//	public static void main(String arg[]){
//
//	}

}
