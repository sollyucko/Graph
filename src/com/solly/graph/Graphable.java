package com.solly.graph;


import java.math.BigDecimal;


public class Graphable {
	public BigDecimal			value;
	public String				meaning;
	public static BigDecimal	min;
	public static BigDecimal	max;
	
	public Graphable(BigDecimal value) {
		this(value, value.toString());
	}
	
	public Graphable(double value) {
		this(new BigDecimal(value), String.valueOf(value));
	}
	
	public Graphable(BigDecimal value, String meaning) {
		this.value = value;
		this.meaning = meaning;
	}
	
	public Graphable(double value, String meaning) {
		this(new BigDecimal(value), meaning);
	}
	
	public String toString() {
		return this.meaning;
	}
}
