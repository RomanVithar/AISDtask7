package com.univ.proj.util.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Roman Anokhin
 */
public class Graph {
    private HashMap<String, Node> graph = new HashMap<String, Node>();

    public Graph() {
    }

    public void parseGraph(String dot) {
        StringBuilder dots = new StringBuilder(dot);
        dots.delete(0, dots.indexOf("{") + 1);
        while (dots.indexOf(";") != -1) {
            StringBuilder operator = new StringBuilder(dots.substring(0, dots.indexOf(";")).trim().replace(" ", ""));
            dots.delete(0, dots.indexOf(";") + 1);
            graph.put(operator.substring(0, operator.indexOf("-")), new Node());
            graph.put(operator.substring(operator.indexOf("-") + 2, operator.indexOf("[")), new Node());
        }
        dot = dot.replace(" ", "");
        dot = dot.replace("\n", "");
        dot = dot.substring(dot.indexOf("{")+1);
        String dotSave = dot;
        for (String item : graph.keySet()) {
            dot = dotSave;
            while (dot.contains(";")) {
                String block = dot.substring(0,dot.indexOf(";"));
                dot=dot.substring(dot.indexOf(";")+1);
                Node node = graph.get(item);
                if(block.substring(0,block.indexOf("-")).equals(item)){
                    node.addIncidentVertex(block.substring(block.indexOf("-")+2,block.indexOf("[")));
                    node.addWeight(Double.parseDouble(block.substring(block.indexOf("[")+1,block.indexOf("]"))));
                    graph.put(item, node);
                }
                if(block.substring(block.indexOf("-")+2,block.indexOf("[")).equals(item)){
                    node.addIncidentVertex(block.substring(0,block.indexOf("-")));
                    node.addWeight(Double.parseDouble(block.substring(block.indexOf("[")+1,block.indexOf("]"))));
                    graph.put(item, node);
                }
            }
        }
    }

    public int size() {
        return graph.size();
    }

    public Set<String> keySet() {
        return graph.keySet();
    }
    public List<String> getListIncident(String key) {
        return graph.get(key).getIncident();
    }
    public Node getValue(String str) {
        return graph.get(str);
    }
}
