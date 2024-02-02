package com.hivestreaming.assignment.cpuusage;

import java.util.Random;

public class RandomUsage extends PeekValueUsage {

	private Random random = new Random(System.currentTimeMillis());

	public RandomUsage(int peek) {
		super(peek);
	}

	public int getPercent() {
		return random.nextInt(this.peek) + 1;
	}

}
