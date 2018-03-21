package ca.mcgill.ecse223.resto.view;

import acm.graphics.*;



public class GLabeledRect extends GCompound {
	
	public GLabeledRect (int width, int height, int tableNum){
		frame = new GRect(width, height);
		add(frame);
	    label = new GLabel(Integer.toString(tableNum));
		add(label);
		recenterLabel();
	}
	
	public GLabeledRect (int x, int y, int width, int height, int tableNum){
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
	
	private GRect frame;
	private GLabel label;

}
