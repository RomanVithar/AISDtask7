package com.univ.proj.util.collect;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Anokhin
 */
public class Node {
    private List<String> incident = new ArrayList<String>();
    private List<Double> weight = new ArrayList<Double>();

    public void addIncidentVertex(String node) {
        incident.add(node);
    }

    public void addWeight(Double w) {
        weight.add(w);
    }
    public List<String> getIncident(){
        return incident;
    }

    public double getWeightAt(int i) {
        return weight.get(i);
    }
    public String getIncidentAt(int i) {
        return incident.get(i);
    }
}
