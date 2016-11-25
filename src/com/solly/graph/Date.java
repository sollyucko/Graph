package com.solly.graph;


import java.math.BigDecimal;


public class Date extends Graphable {
	public static BigDecimal	min	= new BigDecimal(Long.MIN_VALUE);
	public static BigDecimal	max	= new BigDecimal(Long.MAX_VALUE);
	
	public Date(java.util.Date date) {
		super(date.getTime(), date.toString());
	}
	
	public Date(java.sql.Date date) {
		super(date.getTime(), date.toString());
	}
}
