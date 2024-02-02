package com.hivestreaming.assignment.cpuusage;

public class SinWaveUsage extends PeekValueUsage {

	private boolean up = true;

	private int current = 0;

	public SinWaveUsage(int peek) {
		super(peek);
	}

	public int getPercent() {
		if (this.up) {
			if (this.current < this.peek) {
				return this.current++;
			} else {
				this.up = false;
				return this.current--;
			}
		} else {
			if (this.current > 0) {
				return this.current--;
			} else {
				this.up = true;
				return this.current++;
			}
		}
	}

}
