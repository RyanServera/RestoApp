package ca.mcgill.ecse223.resto.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import acm.graphics.GCanvas;

public class DragCanvas extends GCanvas implements MouseListener, MouseMotionListener{
	double x, y, offX, offY;
	double width, height;
	boolean dragging = false;
	int tabNum;
	GLabeledRect rect;
	
	public DragCanvas(){
				
	}
	public DragCanvas (double xTable, double yTable, double widthTable, double lengthTable, int tableNum) {
		x = xTable;
		y = yTable;
		width = widthTable;
		height = lengthTable;
		tabNum = tableNum;
		
		rect = new GLabeledRect(x, y, width, height, tableNum);
		add(rect);	
		
		addMouseListener(this);
		addMouseMotionListener(this);
				
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragging){
			double xDrag = e.getX();
			double yDrag = e.getY();
			
			x = xDrag - offX;
			y = yDrag - offY;
			
			remove(rect);
			DragCanvas dc = new DragCanvas(x, y, width, height, tabNum);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		double xPress = e.getX();
		double yPress = e.getY();
		
		if(xPress > x && xPress < (x+width) && yPress > y && yPress < (y+height)){
			dragging = true;
			offX = xPress - x;
			offY = yPress - y;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;		
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
