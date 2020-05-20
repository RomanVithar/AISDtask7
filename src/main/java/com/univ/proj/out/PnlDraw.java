package com.univ.proj.out;

import com.univ.proj.util.collect.Graph;
import com.univ.proj.util.draw.GraphDrawing;
import com.univ.proj.util.task.Analysis;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Roman Anokhin
 */
public class PnlDraw extends Panel {
    private String str = null;
    private Graph graph;
    private  GraphDrawing gd;
    private ArrayList<String> greenList;
    private ArrayList<String> redList;

    public PnlDraw() {
        super();
        graph = new Graph();
        greenList = new ArrayList<String>();
        redList = new ArrayList<String>();
        gd = new GraphDrawing();
        super.setSize(new Dimension(700,1000));
        super.setMinimumSize(new Dimension(500,1000));
        super.setBackground(Color.gray);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            graph = new Graph();
            gd = new GraphDrawing();
            graph.parseGraph(str);
            gd.paint(g, graph);
            for(String item: redList){
                gd.highlightRed(g,item);
            }
            for(String item: greenList){
                gd.highlightGreen(g,item);
            }
        }catch (Exception ignore) {

        }
    }

    public void drawGraph(String str){
        this.str = str;
        repaint();
    }

    public void setVertexGreen(String v) {
        greenList.add(v);
    }
    public void setVertexRed(String v){
        redList.add(v);
    }
    public void refresh(){
        redList = new ArrayList<String>();
        greenList = new ArrayList<String>();
    }

    public void drawGraphWithColor(String str){
        this.str = str;
        Analysis analysis = new Analysis();
        graph.parseGraph(str);
        HashMap<String, com.univ.proj.util.task.Color> colorMap = analysis.calculate(redList.get(0),greenList.get(0),graph);
        for(String item:colorMap.keySet()){
            if(colorMap.get(item)==com.univ.proj.util.task.Color.GREEN){
                greenList.add(item);
            }
            if(colorMap.get(item)==com.univ.proj.util.task.Color.RED){
                redList.add(item);
            }
        }
        repaint();
    }
}
