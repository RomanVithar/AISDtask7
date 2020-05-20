package com.univ.proj.util.draw;

import com.univ.proj.util.collect.Graph;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Roman Anokhin
 */
public class GraphDrawing {
    private class Cord {
        int x;
        int y;

        public Cord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private HashMap<String, Cord> nodes = new HashMap<String, Cord>();
    private int wightCircle = 30;

    public void paint(Graphics g, Graph graph) {
        double angle = 360/(float)graph.size();
        angle = (angle*Math.PI)/180d;
        double angleSample = angle;
        Graphics2D g2 = (Graphics2D) g;
        int nullCordx = 450;
        int nullCordy = 150;
        for (String key : graph.keySet()) {
            nodes.put(key, new Cord(nullCordx, nullCordy));
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g2.setStroke(new BasicStroke(2.0f));
            g2.drawString(key, nullCordx + wightCircle / 2, nullCordy - wightCircle / 2);
            g2.drawOval(nullCordx, nullCordy, wightCircle, wightCircle);

            nullCordx = (int) (nullCordx + (Math.cos(angle) * 150));
            nullCordy = (int) (nullCordy + (Math.sin(angle) * 150));
            angle += angleSample;

        }
        for (String key : graph.keySet()) {
            for(int i = 0;i<graph.getListIncident(key).size();i++){
                String node = graph.getValue(key).getIncidentAt(i);
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawString(String.valueOf(graph.getValue(key).getWeightAt(i)),
                        (nodes.get(key).x+nodes.get(node).x)/2,
                        (nodes.get(key).y+nodes.get(node).y)/2);
                g2.drawLine(nodes.get(key).x + wightCircle / 2,
                        nodes.get(key).y + wightCircle / 2,
                        nodes.get(node).x + wightCircle / 2,
                        nodes.get(node).y + wightCircle / 2);
            }
//            for (String node : graph.getListIncident(key)) {
//                g2.drawLine(nodes.get(key).x + wightCircle / 2,
//                        nodes.get(key).y + wightCircle / 2,
//                        nodes.get(node).x + wightCircle / 2,
//                        nodes.get(node).y + wightCircle / 2);
//            }
        }
    }

    public void highlightEnemy(Graphics g, String a, String b) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5.0f));
        g2.setColor(Color.red);
        g2.drawOval(nodes.get(a).x, nodes.get(a).y, wightCircle, wightCircle);
        g2.setColor(Color.green);
        g2.drawOval(nodes.get(b).x, nodes.get(b).y, wightCircle, wightCircle);
    }

    public void highlightGreen(Graphics g, String a) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5.0f));
        g2.setColor(Color.green);
        g2.drawOval(nodes.get(a).x, nodes.get(a).y, wightCircle, wightCircle);
    }

    public void highlightRed(Graphics g, String a) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5.0f));
        g2.setColor(Color.red);
        g2.drawOval(nodes.get(a).x, nodes.get(a).y, wightCircle, wightCircle);
    }
}
