package com.example.scheduling.demo;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class SampleTask implements Runnable {

	private static final Logger logger = Logger.getLogger(SampleTask.class.getName());

	private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
	private long previousTimeMillis;

	public void run() {
		final long currentTimeMillis = System.currentTimeMillis();

		if (System.currentTimeMillis() - previousTimeMillis >= 100L) {
			final String previousDate = dateFormat.format(new Date(previousTimeMillis));
			final String currentDate = dateFormat.format(new Date(currentTimeMillis));

			logger.info(String.format("Previous execution: [%s]; Current execution: [%s]", previousDate, currentDate));
		} else {
			logger.info("Skipping execution");
		}

		previousTimeMillis = currentTimeMillis;
	}
}