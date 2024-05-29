package com.virtualthreads;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController {

	private static final String REMOTE_SERVICE_URL = "http://localhost:8085";
	private final RestClient restClient;

	public HomeController(RestClient.Builder builder) {
		this.restClient = builder.baseUrl(REMOTE_SERVICE_URL).build();
	}

	@GetMapping("/block/{seconds}")
	public String home(@PathVariable("seconds") Integer seconds) {
		log.info("Started API with delay: {}", seconds);
		ResponseEntity<Void> result = restClient.get().uri("/block/{seconds}", seconds).retrieve().toBodilessEntity();
		log.info("Ended StatusCode:{}, CurrentThread: {}", result.getStatusCode(), Thread.currentThread());
		return Thread.currentThread().toString();
	}
}
