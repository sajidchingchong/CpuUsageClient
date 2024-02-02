package com.hivestreaming.assignment.cpuusage;

public abstract class PeekValueUsage implements CpuUsage {

	protected int peek;

	public PeekValueUsage(int peek) {
		this.peek = peek <= 0 || peek >= 100 ? 100 : peek;
	}

}
