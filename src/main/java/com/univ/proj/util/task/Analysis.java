package com.univ.proj.util.task;

import com.univ.proj.util.collect.Graph;
import com.univ.proj.util.collect.Node;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Roman Anokhin
 */
public class Analysis {
    private ArrayList<String> reserveList;
    private String red;
    private String green;
    private Graph graph;

    public HashMap<String, Color> calculate(String red, String green, Graph graph) {
        reserveList = new ArrayList<String>();
        this.red = red;
        this.green = green;
        this.graph = graph;
        reserveList.add(red);
        reserveList.add(green);
        HashMap<String, Color> colorMap = new HashMap<String, Color>();
        for(String item:graph.keySet()) {
            if(!item.equals(red)&&!item.equals(green)) {
                double greenAttitude = seekSumPathGreen(graph.getValue(item), item);
                double redAttitude = seekSumPathRed(graph.getValue(item), item);
                if(greenAttitude> redAttitude){
                    colorMap.put(item,Color.GREEN);
                }
                if(greenAttitude< redAttitude){
                    colorMap.put(item,Color.RED);
                }
                if(greenAttitude == redAttitude){
                    colorMap.put(item,Color.YELLOW);
                }
            }
        }
        return colorMap;
    }

    private double seekSumPathRed(Node node, String nameNode) {
        reserveList.add(nameNode);
        double sum = 0;
        for (int i = 0; i < node.getIncident().size(); i++) {
            double localSum = 0;
            if (node.getIncidentAt(i).equals(red)) {
                localSum = node.getWeightAt(i);
            }
            if (!isList(node.getIncidentAt(i))) {
                localSum = node.getWeightAt(i)*seekSumPathRed(graph.getValue(node.getIncidentAt(i)),node.getIncidentAt(i));
                removeList(node.getIncidentAt(i));
            }
            sum+= localSum;
        }
        return sum;
    }

    private double seekSumPathGreen(Node node, String nameNode) {
        reserveList.add(nameNode);
        double sum = 0;
        for (int i = 0; i < node.getIncident().size(); i++) {
            double localSum = 0;
            if (node.getIncidentAt(i).equals(green)) {
                localSum = node.getWeightAt(i);
            }
            if (!isList(node.getIncidentAt(i))) {
                localSum = node.getWeightAt(i)*seekSumPathGreen(graph.getValue(node.getIncidentAt(i)),node.getIncidentAt(i));
                removeList(node.getIncidentAt(i));
            }
            sum+= localSum;
        }
        return sum;
    }

    private boolean isList(String a) {
        for (String item : reserveList) {
            if (item.equals(a)) {
                return true;
            }
        }
        return false;
    }
    private void removeList(String a){
        for (int i=0;i<reserveList.size();i++) {
            if(a.equals(reserveList.get(i))){
                reserveList.remove(i);
            }
        }
    }
}
