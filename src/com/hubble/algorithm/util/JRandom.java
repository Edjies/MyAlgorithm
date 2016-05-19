package com.hubble.algorithm.util;

import java.util.Random;

public class JRandom {
	private final static Random random = new Random();
	
	public static int nextInt(int range) {
		return random.nextInt(range);
	}
}
