package ca.mcgill.ecse223.resto.view;

import java.awt.Color;

import acm.graphics.*;

//From Prof Ferrie's ECSE 202 Class

public class GLabeledCircle extends GCompound {

    public GLabeledCircle (double width, double height){
        frame = new GOval(width, height);
        add(frame);
    }

    public GLabeledCircle (double x, double y, double width, double height){
        this(width, height);
        this.setLocation(x, y);
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(String aColor){
        this.color = Color.getColor(aColor);
    }
    private Color color;
    private GOval frame;


}
