package com.virtualthreads;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualthreads.service.ResourceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resource")
public class ResourceController {

	private final ResourceService resourceService;

	@GetMapping("/upload")
	public void uploadSmallFile() throws InterruptedException {
		// upload small file
		resourceService.uploadSmallFile();
	}

	public void uploadLargeFile() {
		// upload large file
		resourceService.uploadLargeFile();
	}
}
