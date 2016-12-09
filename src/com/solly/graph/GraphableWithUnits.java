package com.solly.graph;

import java.math.BigDecimal;

public class GraphableWithUnits extends Graphable {
	public GraphableWithUnits(BigDecimal value, String beforeunits, String afterunits) {
		super(value, beforeunits + value + afterunits);
	}
	
	public GraphableWithUnits(double value, String beforeunits, String afterunits) {
		super(value, beforeunits + value + afterunits);
	}
}
