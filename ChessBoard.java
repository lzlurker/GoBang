import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ChessBoard extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int MARGIN = 30;
	public static final int GRID_SPAN = 25;
	public static final int ROWS = 30;
	public static final int COLS = 30;
	
	Pointe[] chesslist = new Pointe[((ROWS+1)*(COLS+1))];
	boolean isBlack = true;
	boolean gameOver = false;
	
	int chessCount;
	int xIndex, yIndex;
	
	public ChessBoard(){
		setBackground(Color.orange);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int x1 = (e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				int y1 = (e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				if(x1<0||x1>ROWS||y1<0||y1>COLS||gameOver||findChess(x1,y1)){
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					
				}
				else{
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
		});
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i=0;i<=ROWS;i++){
			g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
		}
		for (int i=0;i<=COLS;i++){
			g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
		}
		for (int i=0;i<chessCount;i++){
			int xPos = chesslist[i].getX()*GRID_SPAN+MARGIN;
			int yPos = chesslist[i].getY()*GRID_SPAN+MARGIN;
			g.setColor(chesslist[i].getColor());
			g.fillOval(xPos-Pointe.DIAMETER/2, yPos-Pointe.DIAMETER/2, Pointe.DIAMETER, Pointe.DIAMETER);
			
			if(i == chessCount-1){
				// last one with red rectangle
				g.setColor(Color.red);
				g.drawRect(xPos-Pointe.DIAMETER/2,yPos-Pointe.DIAMETER/2, Pointe.DIAMETER, Pointe.DIAMETER);
			}
		}
		
		
	}

	private boolean findChess(int x1, int y1) {
		for (Pointe c:chesslist){
			if(c!=null&&c.getX() == x1 && c.getY()==y1){
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(gameOver){
			return;
		}
		String colorName = isBlack? "Black":"White";
		xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
		yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
		
		if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS){
			return;
		}
		
		if(findChess(xIndex, yIndex)){
			return;
		}
		
		Pointe ch = new Pointe(xIndex, yIndex, isBlack?Color.BLACK:Color.white);
		chesslist[chessCount++]=ch;
		repaint();
		
		if(isWin()){
			String msg= String.format("%s win!", colorName);
			JOptionPane.showMessageDialog(this, msg);
			gameOver=true;
		}
		isBlack=!isBlack;
	}

	private boolean isWin() {
		int continueCount = 1;
		//Horizontal Judgment
		for (int x=xIndex-1;x>=0;x--){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(x,yIndex,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		for (int x=xIndex+1;x<=ROWS;x++){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(x,yIndex,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount>4){
			return true;
		}
		else{
			continueCount = 1;
		}
		//End of Horizontal Judgment
		//Vertical Judgment
		for (int y=yIndex-1;y>=0;y--){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(xIndex,y,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		for (int y=yIndex+1;y<=ROWS;y++){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(xIndex,y,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount>4){
			return true;
		}
		else{
			continueCount = 1;
		}
		//End of Vertical Judgment
		//45 degree Judgment
		for (int x=xIndex-1,y=yIndex-1;x>=0&&y>=0;x--,y--){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(x,y,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		for (int x=xIndex+1,y=yIndex+1;x<=COLS&&y<=ROWS;x++,y++){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(x,y,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount>4){
			return true;
		}
		else{
			continueCount = 1;
		}
		//End of 45 degree Judgment
		//-45 degree Judgment
		//45 degree Judgment
		for (int x=xIndex-1,y=yIndex+1;x>=0&&y<=ROWS;x--,y++){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(x,y,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		for (int x=xIndex+1,y=yIndex-1;x<=COLS&&y>=0;x++,y--){
			Color c= isBlack?Color.black:Color.white;
			if(getChess(x,y,c)!=null){
				continueCount++;
			}
			else
				break;
		}
		if(continueCount>4){
			return true;
		}
		else{
			continueCount = 1;
		}
		//End of -45 degree Judgment
		return false;
	}

	private Pointe getChess(int xIndex, int yIndex, Color color) {
		for(Pointe c:chesslist){
			if(c!=null&&c.getX()==xIndex&&c.getY()==yIndex&&c.getColor()==color){
				return c;
			}
		}
		return null;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	public void Restart() {
		for (int i=0;i<chesslist.length;i++){
			chesslist[i]=null;
		}
		isBlack=true;
		gameOver=false;
		chessCount=0;
		repaint();
	}

	public void goBack() {
		if(chessCount==0){
			return;
		}
		chesslist[chessCount-1]=null;
		chessCount--;
		if(chessCount>0){
			xIndex=chesslist[chessCount-1].getX();
			yIndex=chesslist[chessCount-1].getY();
			
		}
		isBlack=!isBlack;
		repaint();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(MARGIN*2+GRID_SPAN*COLS, MARGIN*2+GRID_SPAN*ROWS);
		
	}

}
