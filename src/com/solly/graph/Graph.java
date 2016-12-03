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
		
		String string = "<svg viewbox=\"" + xmin.subtract(BigDecimal.ONE).subtract(BigDecimal.ONE).toPlainString() + " " + ymin.subtract(ymax).subtract(BigDecimal.ONE).toPlainString() + " " + xmax.subtract(xmin).add(BigDecimal.ONE).add(BigDecimal.ONE).add(BigDecimal.ONE).toPlainString() + " " + ymax.subtract(ymin).add(BigDecimal.ONE).add(BigDecimal.ONE).add(BigDecimal.ONE).toPlainString() + "\">\n";
		string += "\t<g transform=\"translate(0, " + ymin + ") scale(1,-1)\">\n";
		string += "\t\t<line x1=\"" + xmin.toPlainString() + "\" y1=\"" + ymin.toPlainString() + "\" x2=\"" + xmin.toPlainString() + "\" y2=\"" + ymax.toPlainString() + "\" stroke-width=\"1\" stroke=\"black\"/>\n";
		string += "\t\t<line x1=\"" + xmin.toPlainString() + "\" y1=\"" + ymin.toPlainString() + "\" x2=\"" + xmax.toPlainString() + "\" y2=\"" + ymin.toPlainString() + "\" stroke-width=\"1\" stroke=\"black\"/>\n";
		if(this.max != null) {
			string += "\t\t<line x1=\"" + xmin.subtract(BigDecimal.ONE).toPlainString() + "\" y1=\"" + this.max.value.toPlainString() + "\" x2=\"" + xmax.subtract(xmin).add(BigDecimal.ONE).add(BigDecimal.ONE).toPlainString() + "\" y2=\"" + this.max.value.toPlainString() + "\" stroke-width=\"1\" stroke=\"red\"></line>\n";
		}
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			string += "\t\t<circle cx=\"" + point.getKey().value.toPlainString() + "\" cy=\"" + point.getValue().value.toPlainString() + "\" r=\"1\"></circle>\n";
		}
		string += "\t</g>\n";
		string += "\t<g transform=\"translate(1, " + ymin.toPlainString() + ")\">\n";
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			string += "\t<text font-size=\"1\" x=\"" + point.getKey().value.toPlainString() + "\" y=\"" + point.getValue().value.negate().toPlainString() + "\">" + "(" + point.getKey().meaning + ", " + point.getValue().meaning + ")" + "</text>\n";
		}
		string += "\t</g>";
		return string + "</svg>";
	}
}
