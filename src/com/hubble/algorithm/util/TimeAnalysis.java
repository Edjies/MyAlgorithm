package com.hubble.algorithm.util;

public class TimeAnalysis {
	private long start = 0l;
	private long end = 0L;
	
	public void startRun() {
		start = System.currentTimeMillis();
	}
	
	public void endRun() {
		end = System.currentTimeMillis();
		System.out.println((end - start) / 1000L + "秒"  + (end - start) % 1000L + "毫秒");
	}
}
