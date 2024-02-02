package com.hivestreaming.assignment;

import java.nio.ByteBuffer;

import com.hivestreaming.assignment.cpuusage.CpuUsage;
import com.hivestreaming.assignment.sender.MessageSender;

public class Producer implements Runnable {

	private CpuUsage usage;

	private MessageSender sender;

	public Producer(CpuUsage usage, MessageSender sender) {
		this.usage = usage;
		this.sender = sender;
	}

	public void run() {
		while (true) {
			try {
				this.sender.sendMessage(this.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(Configuration.MESSAGE_FREQUENCY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private byte[] getMessage() {
		ByteBuffer buffer = ByteBuffer.allocate(Configuration.MESSAGE_LENGTH);

		buffer.put(this.getClientId().getBytes());
		buffer.putLong(this.getTimestamp());
		int percent = this.getPercent();
		System.out.print(percent);
		buffer.putInt(percent);

		return buffer.array();
	}

	private String getClientId() {
		return String.format("%1$" + Configuration.CLIENT_ID_LENGTH + "s", Configuration.clientId);
	}

	private long getTimestamp() {
		return System.currentTimeMillis();
	}

	private int getPercent() {
		return this.usage.getPercent();
	}

}
