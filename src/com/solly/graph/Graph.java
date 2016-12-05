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
		BigDecimal ymin = null;
		BigDecimal ymax = null;
		int maxpairlength = 0;
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			BigDecimal keyval = point.getKey().value;
			BigDecimal valval = point.getValue().value;
			if(xmin == null || keyval.compareTo(xmin) < 0) {
				xmin = keyval;
			}
			if(xmax == null || keyval.compareTo(xmax) > 0) {
				xmax = keyval;
				maxpairlength = point.getKey().meaning.length() + point.getValue().meaning.length() + 3;
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
		
		String string = "<svg viewbox=\"0 " + ymax.negate().subtract(ymin).subtract(BigDecimal.ONE).toPlainString() + " " + xmax.subtract(xmin).add(new BigDecimal(2)).add(new BigDecimal(maxpairlength)).toPlainString() + " " + ymin.negate().add(new BigDecimal(5.25)).toPlainString() + "\">\n";
		if(this.max != null) {
			string += "\t<line x1=\"0\" y1=\"" + this.max.value.negate().subtract(ymin).toPlainString() + "\" x2=\"" + xmax.subtract(xmin).add(new BigDecimal(2)).toPlainString() + "\" y2=\"" + this.max.value.negate().subtract(ymin).toPlainString() + "\" stroke-width=\"1\" stroke=\"red\"></line>\n";
			string += "\t<text font-size=\"1\" font-family=\"monospace\" x=\"0\" y=\"" + this.max.value.negate().subtract(ymin).add(new BigDecimal(0.25)).toPlainString() + "\">" + this.max.meaning + "</text>\n";
		}
		for(Map.Entry<Graphable, Graphable> point : this.points.entrySet()) {
			string += "\t<circle cx=\"" + point.getKey().value.subtract(xmin).add(BigDecimal.ONE).toPlainString() + "\" cy=\"" + point.getValue().value.negate().subtract(ymin).toPlainString() + "\" r=\"1\"></circle>\n";
			string += "\t<text font-size=\"1\" font-family=\"monospace\" x=\"" + point.getKey().value.subtract(xmin).add(new BigDecimal(2)).toPlainString() + "\" y=\"" + point.getValue().value.negate().subtract(ymin).add(new BigDecimal(0.25)).toPlainString() + "\">" + "(" + point.getKey().meaning + ", " + point.getValue().meaning + ")" + "</text>\n";
		}
		return string + "</svg>";
	}
}
