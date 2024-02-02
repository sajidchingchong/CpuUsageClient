package com.hivestreaming.assignment;

import com.hivestreaming.assignment.cpuusage.CpuUsage;
import com.hivestreaming.assignment.cpuusage.RandomUsage;
import com.hivestreaming.assignment.cpuusage.SinWaveUsage;
import com.hivestreaming.assignment.sender.ActiveMQSender;

public class App {

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			if (args[0].length() > Configuration.CLIENT_ID_LENGTH) {

			}
			Configuration.clientId = args[0].length() > Configuration.CLIENT_ID_LENGTH
					? args[0].substring(0, Configuration.CLIENT_ID_LENGTH)
					: args[0];
		} else {
			System.out.println("Please provide some client id");
			System.exit(-1);
		}

		int peek = 0;
		if (args.length > 1) {
			peek = Integer.parseInt(args[1]);
		}

		CpuUsage usage = new SinWaveUsage(peek);
		if (args.length > 2 && args[2].equalsIgnoreCase(Configuration.RANDOM)) {
			usage = new RandomUsage(peek);
		}

		thread(new Producer(usage, new ActiveMQSender()), false);
	}

	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

}
