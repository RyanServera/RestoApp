package ca.mcgill.ecse223.resto.view;

import java.awt.Color;

import acm.graphics.*;

//From Prof Ferrie's ECSE 202 Class

public class GLabeledRect extends GCompound {
	
	public GLabeledRect (double width, double height, int tableNum){
		frame = new GRect(width, height);
		add(frame);
	    label = new GLabel(Integer.toString(tableNum));
		add(label);
		recenterLabel();
	}
	
	public GLabeledRect (double x, double y, double width, double height, int tableNum){
		this(width, height, tableNum);
		setLocation(x, y);
	}
	
	public void setFont (String font){
		label.setFont(font);
		recenterLabel();
	}
	
	public void setLabel(int tableNum){
		label.setLabel(Integer.toString(tableNum));
		recenterLabel();
	}
	
	public String getLabel(){
		return label.getLabel();
	}
	
	private void recenterLabel(){
		double x = (frame.getWidth()- label.getWidth()) / 2;
		double y = (frame.getHeight()- label.getAscent()) / 2;
		label.setLocation(x, y);
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(String aColor){
		this.color = Color.getColor(aColor);
	}
	private Color color;
	private GRect frame;
	private GLabel label;

}
