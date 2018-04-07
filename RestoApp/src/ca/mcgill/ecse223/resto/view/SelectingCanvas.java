package ca.mcgill.ecse223.resto.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import acm.graphics.GCanvas;
import ca.mcgill.ecse223.resto.model.Table;

public class SelectingCanvas extends GCanvas implements MouseListener, MouseMotionListener{
    public static ArrayList<GLabeledRect> labeledRects;
    private static HashMap<Integer, Table> tableMap;
    public static ArrayList<Table> selectedTables = new ArrayList<>();
    double x, y, offX, offY;
    double width, height;
    boolean dragging = false;
    int tabNum;
    private static Table selectedTable;
    GLabeledRect rect;

    public SelectingCanvas(double xTable, double yTable, double widthTable, double lengthTable, int tableNum){
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
    public SelectingCanvas(List<Table> list) {
        labeledRects =  new ArrayList<GLabeledRect>();
        tableMap = new HashMap<Integer, Table>();
        for(Table table: list){
            System.out.println("In SelectingCanvas, table values, table num = "+ table.getNumber()+" x = "+table.getX()+", y = "+table.getY()+" ");
            rect = new GLabeledRect(table.getX(), table.getY(), table.getLength(), table.getWidth(), table.getNumber());
            if(table.getStatus().equals(Table.Status.Available)){
                rect.setColor(Color.GREEN);
            }else{
                rect.setColor(Color.RED);
            }
            tableMap.put(rect.hashCode(), table);
            rect.addMouseListener(this);
            rect.addMouseMotionListener(this);
            labeledRects.add(rect);
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

            labeledRects.remove(rect);
            SelectingCanvas dc = new SelectingCanvas(x, y, width, height, tabNum);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("moused pressed");
        System.out.println(e.getSource());
        double xPress = e.getX();
        double yPress = e.getY();
        selectedTable = tableMap.get(e.getSource().hashCode());
        ArrayList<Table> selected = new ArrayList<Table>();
        selected.add(selected.size(), selectedTable);
        System.out.println(selectedTables);
        if (selectedTables == null){
            selectedTables = selected;
        }
        else{
            if (!selectedTables.contains(selectedTable)){
                selectedTables.add(selectedTables.size(), selectedTable);
            }
        }
        System.out.println(selected.size());
        System.out.println(selectedTables.size());
        GLabeledRect rect = (GLabeledRect)e.getSource();
        rect.setColor(Color.RED);

        e.setSource(rect);

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
