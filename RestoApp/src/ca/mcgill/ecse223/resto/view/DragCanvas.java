package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import acm.graphics.GCanvas;
import ca.mcgill.ecse223.resto.model.Seat;
import ca.mcgill.ecse223.resto.model.Table;

public class DragCanvas extends GCanvas implements MouseListener, MouseMotionListener{
	public static ArrayList<GLabeledRect> labeledRects;
	public static HashMap<Table, ArrayList<GLabeledCircle>> tableSeatMap;
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
		tableSeatMap = new HashMap<Table, ArrayList<GLabeledCircle>>();
		labeledRects =  new ArrayList<GLabeledRect>();
		for(Table table: list){
			System.out.println("In DragCanvas, table values, table num = "+ table.getNumber()+" x = "+table.getX()+", y = "+table.getY()+" "+" num seats = "+table.getSeats().size());
			rect = new GLabeledRect(table.getX(), table.getY(), table.getLength(), table.getWidth(), table.getNumber());
			if(table.getStatus().equals(Table.Status.Available)){
				rect.setColor(Color.GREEN);
			}else{
				rect.setColor(Color.RED);
			}
			
			labeledRects.add(rect);
			add(rect);
			int numOfSeats = table.getCurrentSeats().size();
			if(numOfSeats == 2){
					double radius = table.getWidth() / 4;
					double x1 = table.getX() + (table.getLength()/2) - (radius/2) ;
					double y1 = table.getY() - radius - 5;
					double x2 = table.getX() + (table.getLength()/2) - (radius/2);
					double y2 = table.getY() + table.getWidth() + radius - 5;
					GLabeledCircle circle1 = new GLabeledCircle(x1, y1, radius, radius);
					GLabeledCircle circle2 = new GLabeledCircle(x2, y2, radius, radius);
					this.add(circle1);
					this.add(circle2);
					ArrayList<GLabeledCircle> circles = new ArrayList<GLabeledCircle>();
					circles.add(circle1);
					circles.add(circle2);
					tableSeatMap.put(table, circles);
				
			}
			else if(numOfSeats == 4){
				double radius = table.getWidth() / 4;
				double x1 = table.getX() + (table.getLength()/4) - (radius/2) ;
				double y1 = table.getY() - radius - 5;
				double x2 = table.getX() + (table.getLength()/4) - (radius/2);
				double y2 = table.getY() + table.getWidth() + radius - 5;
				double x3 = table.getX() + ((3*table.getLength())/4) - (radius/2) ;;
				double y3 = table.getY() - radius - 5;
				double x4 = table.getX() + ((3*table.getLength())/4) - (radius/2) ;;
				double y4 = table.getY() + table.getWidth() + radius - 5;
				GLabeledCircle circle1 = new GLabeledCircle(x1, y1, radius, radius);
				GLabeledCircle circle2 = new GLabeledCircle(x2, y2, radius, radius);
				GLabeledCircle circle3 = new GLabeledCircle(x3, y3, radius, radius);
				GLabeledCircle circle4 = new GLabeledCircle(x4, y4, radius, radius);
				this.add(circle1);
				this.add(circle2);
				this.add(circle3);
				this.add(circle4);
				ArrayList<GLabeledCircle> circles = new ArrayList<GLabeledCircle>();
				circles.add(circle1);
				circles.add(circle2);
				circles.add(circle3);
				circles.add(circle4);
				tableSeatMap.put(table, circles);
				
			}
			else if(numOfSeats == 6){
				double radius = table.getWidth() / 6;
				double x1 = table.getX() + (table.getLength()/6) - (radius/2) ;
				double y1 = table.getY() - radius - 5;
				double x2 = table.getX() + (table.getLength()/6) - (radius/2);
				double y2 = table.getY() + table.getWidth() + radius - 5;
				double x3 = table.getX() + ((3*table.getLength())/6) - (radius/2) ;
				double y3 = table.getY() - radius - 5;
				double x4 = table.getX() + ((3*table.getLength())/6) - (radius/2) ;
				double y4 = table.getY() + table.getWidth() + radius - 5;
				double x5 = table.getX() + ((5*table.getLength())/6) - (radius/2) ;
				double y5 = table.getY() - radius - 5;
				double x6 = table.getX() + ((5*table.getLength())/6) - (radius/2) ;
				double y6 = table.getY() + table.getWidth() + radius - 5;
				GLabeledCircle circle1 = new GLabeledCircle(x1, y1, radius, radius);
				GLabeledCircle circle2 = new GLabeledCircle(x2, y2, radius, radius);
				GLabeledCircle circle3 = new GLabeledCircle(x3, y3, radius, radius);
				GLabeledCircle circle4 = new GLabeledCircle(x4, y4, radius, radius);
				GLabeledCircle circle5 = new GLabeledCircle(x5, y5, radius, radius);
				GLabeledCircle circle6 = new GLabeledCircle(x6, y6, radius, radius);
				this.add(circle1);
				this.add(circle2);
				this.add(circle3);
				this.add(circle4);
				this.add(circle5);
				this.add(circle6);
				ArrayList<GLabeledCircle> circles = new ArrayList<GLabeledCircle>();
				circles.add(circle1);
				circles.add(circle2);
				circles.add(circle3);
				circles.add(circle4);
				circles.add(circle5);
				circles.add(circle6);
				tableSeatMap.put(table, circles);
				
			}
			
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
	
	public void addSeats(Table table){
		
	}
	
	public HashMap<Table, ArrayList<GLabeledCircle>> getTableSeatMap(){
		return this.tableSeatMap;
	}
	

}
