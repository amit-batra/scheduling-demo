package com.example.scheduling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FixedDelayTrigger {

	@Autowired
	private TaskScheduler scheduler;

	@Autowired
	private SampleTask task;

	@Scheduled(fixedDelay = 1000L)
	public void triggerHelloTaskPeriodically() {
		task.run();
	}
}