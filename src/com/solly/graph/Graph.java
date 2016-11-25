package com.solly.graph;


import java.util.HashMap;
import java.util.Map;


public class Graph {
	Map<Graphable, Graphable> points;
	
	public Graph(Graphable[] xs, Graphable[] ys) {
		this.points = new HashMap<>();
		for(int i = 0; i < Math.min(xs.length, ys.length); i++ ) {
			this.points.put(xs[i], ys[i]);
		}
	}
	
	public Graph(Map<Graphable, Graphable> points) {
		this.points = points;
	}
	
	public String toSVG() {
		String string = "<svg>\n";
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			string += "\t<circle cx=\"" + point.getKey().value.toPlainString() + "\" cy=\"" + point.getValue().value.toPlainString() + "\" r=\"1\"/>\n";
		}
		return string + "</svg>";
	}
}
