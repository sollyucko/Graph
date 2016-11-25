package com.solly.graph;

import java.math.BigDecimal;

public class Percent extends Graphable {
	public Percent(BigDecimal value) {
		super(value, value + "%");
	}
	
	public Percent(double value) {
		super(value, value + "%");
	}
}
