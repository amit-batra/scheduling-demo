package com.example.scheduling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class DemoTask implements Runnable {

	private static final Logger logger = Logger.getLogger(DemoTask.class.getName());
	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
	private static final long DESIRED_GAP_BETWEEN_INVOCATIONS = 200L;

	@Autowired
	private QuotaConcurrentLinkedQueue queue;

	private long previousTimeMillis;

	public void run() {
		final long currentTimeMillis = System.currentTimeMillis();

		if (this.queue.size() >= 100 || currentTimeMillis - previousTimeMillis >= DESIRED_GAP_BETWEEN_INVOCATIONS) {
			synchronized (this) {
				if (this.queue.size() >= 100 || currentTimeMillis - previousTimeMillis >= DESIRED_GAP_BETWEEN_INVOCATIONS) {
					final String previousDate = dateFormat.format(new Date(previousTimeMillis));
					final String currentDate = dateFormat.format(new Date(currentTimeMillis));

					logger.info(String.format("Previous execution: [%s]; Current execution: [%s]", previousDate, currentDate));
				}
			}
		}

		previousTimeMillis = currentTimeMillis;
	}
}