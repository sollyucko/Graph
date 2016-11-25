package com.solly.graph;


import java.math.BigDecimal;


public class Time extends Graphable {
	public static BigDecimal	min	= new BigDecimal(Long.MIN_VALUE);
	public static BigDecimal	max	= new BigDecimal(Long.MAX_VALUE);
	
	public Time(java.sql.Time time) {
		super(time.getTime(), time.toString());
	}
}
