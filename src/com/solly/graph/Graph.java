package com.solly.graph;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class Graph {
	Map<Graphable, Graphable> points;
	Graphable max;
	
	public Graph(Graphable[] xs, Graphable[] ys) {
		this.points = new HashMap<>();
		for(int i = 0; i < Math.min(xs.length, ys.length); i++ ) {
			this.points.put(xs[i], ys[i]);
		}
	}
	
	public Graph(Map<Graphable, Graphable> points) {
		this.points = points;
	}
	
	public Graph(Graphable[] xs, Graphable[] ys, Graphable max) {
		this.points = new HashMap<>();
		for(int i = 0; i < Math.min(xs.length, ys.length); i++ ) {
			this.points.put(xs[i], ys[i]);
		}
		this.max = max;
	}
	
	public Graph(Map<Graphable, Graphable> points, Graphable max) {
		this.points = points;
		this.max = max;
	}
	
	public String toSVG() {
		BigDecimal xmin = null;
		BigDecimal xmax = null;
		BigDecimal ymin = this.max.value;
		BigDecimal ymax = this.max.value;
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			BigDecimal keyval = point.getKey().value;
			BigDecimal valval = point.getValue().value;
			if(xmin == null || keyval.compareTo(xmin) < 0) {
				xmin = keyval;
			}
			if(xmax == null || keyval.compareTo(xmax) > 0) {
				xmax = keyval;
			}
			if(ymin == null || valval.compareTo(ymin) < 0) {
				ymin = valval;
			}
			if(ymax == null || valval.compareTo(ymax) > 0) {
				ymax = valval;
			}
		}
		
		if(xmin == null || xmax == null || ymin == null || ymax == null) {
			return "<svg></svg>";
		}
		
		String string = "<svg viewbox=\"" + xmin.subtract(BigDecimal.ONE) + " " + ymin.subtract(BigDecimal.ONE) + " " + xmax.subtract(xmin).add(BigDecimal.ONE).add(BigDecimal.ONE) + " " + ymax.subtract(ymin).add(BigDecimal.ONE).add(BigDecimal.ONE) + "\">\n";
		if(this.max != null) {
			string += "\t<line x1=\"" + xmin.subtract(BigDecimal.ONE) + "\" y1=\"" + this.max.value.toPlainString() + "\" x2=\"" + xmax.subtract(xmin).add(BigDecimal.ONE).add(BigDecimal.ONE) + "\" y2=\"" + this.max.value.toPlainString() + "\" stroke-width=\"1\" stroke=\"red\"/>\n";
		}
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			string += "\t<circle cx=\"" + point.getKey().value.toPlainString() + "\" cy=\"" + point.getValue().value.toPlainString() + "\" r=\"1\"/>\n";
		}
		return string + "</svg>";
	}
}
