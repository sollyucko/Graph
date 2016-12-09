package com.solly.graph;


import java.math.BigDecimal;
import java.text.DateFormat;


public class Time extends Graphable {
	public static BigDecimal	min	= new BigDecimal(Long.MIN_VALUE);
	public static BigDecimal	max	= new BigDecimal(Long.MAX_VALUE);
	
	public Time(java.sql.Time time) {
		super(time.getTime(), time.toString());
	}
	
	public Time(java.util.Date time) {
		super(time.getTime(), DateFormat.getTimeInstance().format(time));
	}
}
