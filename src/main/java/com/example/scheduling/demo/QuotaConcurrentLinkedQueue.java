package com.example.scheduling.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class QuotaConcurrentLinkedQueue<E> extends ConcurrentLinkedQueue<E> {

	private AtomicLong numAddCallsPending;

	@Override
	public boolean add(E e) {
		numAddCallsPending.incrementAndGet();
		boolean result;
		try {
			result = super.add(e);
		}
		finally {
			numAddCallsPending.decrementAndGet();
		}
		return result;
	}

	public boolean areAddCallsPending() {
		return this.numAddCallsPending.get() > 0L;
	}
}