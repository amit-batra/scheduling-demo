package com.example.scheduling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FixedDelayTrigger {

	@Autowired
	private DemoTask task;

	@Scheduled(fixedDelay = 200L)
	public void triggerDemoTaskPeriodically() {
		task.run();
	}
}