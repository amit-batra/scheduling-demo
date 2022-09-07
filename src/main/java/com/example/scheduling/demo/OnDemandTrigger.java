package com.example.scheduling.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class OnDemandTrigger {

	private List<Job> jobsList = new LinkedList<>();

	@Autowired
	private DemoTask task;

	private ExecutorService executorService = Executors.newCachedThreadPool();

	public void addJob(Job job) {
		// Synchronization of List<Job> omitted for brevity
		this.jobsList.add(job);
		if (this.jobsList.size() >= 100) {
			executorService.submit(task);
		}
	}
}