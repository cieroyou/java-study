package com.virtualthreads;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RemoteController {

	AtomicInteger atomicInteger = new AtomicInteger();

	@GetMapping("/block/{seconds}")
	public String block(@PathVariable("seconds") Integer seconds) throws InterruptedException {
		log.info("It will block for {} seconds", seconds);
		Thread.sleep(seconds * 1000);
		var invokeCount = atomicInteger.incrementAndGet();
		log.info("invokeCount: {}", invokeCount);
		return "Hello, delayed for %d seconds".formatted(seconds);
	}
}
