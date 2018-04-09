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

public class SelectingCanvas extends GCanvas implements MouseListener, MouseMotionListener{
    public static ArrayList<GLabeledRect> labeledRects = null;
    private static HashMap<Integer, Table> tableMap = null;
    private static HashMap<Integer, Seat> seatMap = null;
    public static ArrayList<Table> selectedTables = new ArrayList<>();
    public static ArrayList<Seat> selectedSeats = new ArrayList<Seat>();
    double x, y, offX, offY;
    double width, height;
    boolean dragging = false;
    int tabNum;
    private static Table selectedTable = null;
    private static Seat selectedSeat = null;
    private static final int seatWidth = 10;
    private static final int seatHeight = 10;
    GLabeledRect rect;
    GLabeledCircle circle;

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
        seatMap = new HashMap<Integer, Seat>();

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
            renderSeats(table);

        }
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

        if(labeledRects.contains(e.getSource())){
            selectedTable = tableMap.get(e.getSource().hashCode());
            ArrayList<Table> selected = new ArrayList<Table>();
            selected.add(selected.size(), selectedTable);
            if (selectedTables == null){
                selectedTables = selected;
            }
            else{
                if (!selectedTables.contains(selectedTable) && tableMap.get(e.getSource().hashCode()).getStatus().equals(Table.Status.Available) ){
                    selectedTables.add(selectedTables.size(), selectedTable);
                    GLabeledRect rect = (GLabeledRect)e.getSource();
                    rect.setColor(Color.RED);
                    e.setSource(rect);
                } else {
                    if (tableMap.get(e.getSource().hashCode()).getStatus().equals(Table.Status.Available)){
                        int index = selectedTables.indexOf(tableMap.get(e.getSource().hashCode()));
                        GLabeledRect rect = (GLabeledRect)e.getSource();
                        rect.setColor(Color.GREEN);
                        e.setSource(rect);
                        selectedTables.remove(index);
                    }
                }
            }
        }
        else {
            System.out.println("moused pressed");
            System.out.println(e.getSource());
            selectedSeat = seatMap.get(e.getSource().hashCode());
            ArrayList<Seat> selected = new ArrayList<Seat>();
            selected.add(selected.size(), selectedSeat);
            System.out.println(selectedSeats);
            if (selectedSeats == null) {
                selectedSeats = selected;
            } else {
                if (!selectedSeats.contains(selectedSeat)) {
                    selectedSeats.add(selectedSeats.size(), selectedSeat);
                    System.out.println(selected.size());
                    System.out.println(selectedSeats.size());
                    GLabeledCircle circle = (GLabeledCircle) e.getSource();
                    circle.setColor(Color.BLUE);
                    e.setSource(circle);
                } else {
                    int index = selectedSeats.indexOf(seatMap.get(e.getSource().hashCode()));
                    GLabeledCircle circle = (GLabeledCircle) e.getSource();
                    circle.setColor(Color.BLACK);
                    e.setSource(circle);
                    selectedSeats.remove(index);
                    System.out.println(selectedSeats.size());
                }
            }
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

    // TODO figure out a better way to render the tables
    private void renderSeats (Table table){
        int numSeats = table.getSeats().size();
        int index = 0;
        switch(numSeats)
        {
            case 3:
                for(Seat seat: table.getSeats()){
                    if(index == 0){
                        circle = new GLabeledCircle(table.getX() - 5 - seatWidth, table.getY() + seatHeight/2, seatWidth, seatHeight);
                        circle.addMouseListener(this);
                        seatMap.put(circle.hashCode(), seat);
                        add(circle);
                    }

                    if(index == 1){
                        circle = new GLabeledCircle(table.getX() + table.getWidth() + 5, table.getY() + seatHeight/2 , seatWidth, seatHeight);
                        circle.addMouseListener(this);
                        seatMap.put(circle.hashCode(), seat);
                        add(circle);
                    }

                    if(index == 2){
                        circle = new GLabeledCircle(table.getX() + (table.getWidth())/2, table.getY() - 5, seatWidth, seatHeight);
                        circle.addMouseListener(this);
                        seatMap.put(circle.hashCode(), seat);
                        add(circle);
                    }

                    index++;
                }

                break;
            default:

        }
    }
}
