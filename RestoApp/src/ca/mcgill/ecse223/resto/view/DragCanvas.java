package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GCanvas;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class DragCanvas extends GCanvas implements MouseListener, MouseMotionListener{
	public static ArrayList<GLabeledRect> labeledRects;
	double x, y, offX, offY;
	double width, height;
	boolean dragging = false;
	int tabNum;
	GLabeledRect rect;
	
	public DragCanvas(double xTable, double yTable, double widthTable, double lengthTable, int tableNum){
		x = xTable;
		y = yTable;
		width = widthTable;
		height = lengthTable;
		tabNum = tableNum;
		
		rect = new GLabeledRect(x, y, width, height, tableNum);
		labeledRects.add(rect);	
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public DragCanvas(List<Table> list) {
		labeledRects =  new ArrayList<GLabeledRect>();
		for(Table table: list){
			System.out.println("In DragCanvas, table values, table num = "+ table.getNumber()+" x = "+table.getX()+", y = "+table.getY()+" ");
			rect = new GLabeledRect(table.getX(), table.getY(), table.getLength(), table.getWidth(), table.getNumber());
			if(table.getStatus().equals(Table.Status.Available)){
				rect.setColor(Color.GREEN);
			}else{
				rect.setColor(Color.RED);
			}
			labeledRects.add(rect);
			add(rect);
		}
	}
	public DragCanvas(ArrayList<GLabeledRect> labRects){
		for(GLabeledRect rects: labRects){
			System.out.println("In DragCanvas, table values, table num = "+ rects.getLabel()+" x = "+rects.getX()+", y = "+rects.getY()+" ");
			rect = new GLabeledRect(rects.getX(), rects.getY(), rects.getWidth(), rects.getHeight(),Integer.parseInt(rects.getLabel()));
			add(rect);
		}
	}
	
	public void addTableToCanvas (double xTable, double yTable, double widthTable, double lengthTable, int tableNum) {
		x = xTable;
		y = yTable;
		width = widthTable;
		height = lengthTable;
		tabNum = tableNum;
		
		rect = new GLabeledRect(x, y, width, height, tableNum);
		labeledRects.add(rect);	
		add(rect);
		
		addMouseListener(this);
		addMouseMotionListener(this);
				
	}
	
	public void addGRectToCanvas (GLabeledRect rect){
		labeledRects.add(rect);
		add(rect);
	}
	
	public void refreshData(){
		this.removeAll();
		for(GLabeledRect table: labeledRects) this.add(table);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(dragging){
			double xDrag = e.getX();
			double yDrag = e.getY();
			
			x = xDrag - offX;
			y = yDrag - offY;
			refreshData();
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
