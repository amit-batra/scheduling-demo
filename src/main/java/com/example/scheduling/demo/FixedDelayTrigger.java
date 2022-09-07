package com.example.scheduling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDelayTrigger {

	@Autowired
	private DemoTask task;

	@Scheduled(fixedDelay = 1000L)
	public void triggerDemoTaskPeriodically() {
		task.run();
	}
}