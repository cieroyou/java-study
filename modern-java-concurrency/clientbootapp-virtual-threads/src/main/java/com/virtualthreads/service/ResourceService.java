package com.virtualthreads.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.github.f4b6a3.uuid.UuidCreator;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResourceService {

	private static final String baseDirectory = "/Users/lydia/Developments/sera/java-study/modern-java-concurrency/clientbootapp-virtual-threads/src/main/resources/video";
	private final static String IMAGE_FILE_NAME = "temp.jpg";
	private final static String VIDEO_FILE_NAME = "largefile.mp4";
	private final ServletContext servletContext;

	public void uploadLargeFile() {
	}

	// Semaphore sem = new Semaphore(50);
	public void uploadSmallFile() throws InterruptedException {
		// sem.acquire();
		long beforeTime = System.currentTimeMillis();
		log.info("Upload 시작, thread: {}", Thread.currentThread().getName());
		try (InputStream inputStream = ResourceService.class.getClassLoader()
			.getResource(VIDEO_FILE_NAME)
			.openStream();) {
			String resourceId = String.valueOf(UuidCreator.getTimeOrderedEpoch());
			upload(inputStream, "", resourceId + ".mp4");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// sem.release();
		long afterTime = System.currentTimeMillis();
		log.info("Upload 완료, thread: {}, 시간: {}", Thread.currentThread().getName(), (afterTime - beforeTime));

	}

	private Path getDestinationPath(String destinationPath, String destinationFilename) {
		// destinationPath 가 null 이면 "" 처리
		if (destinationPath == null) {
			destinationPath = "";
		}
		String path = baseDirectory + destinationPath;
		return Paths.get(path, destinationFilename).normalize();
	}

	public void upload(InputStream inputStream, String destinationDirectory, String destinationFilename) throws
		IOException {
		Path destinationPath = getDestinationPath(destinationDirectory, destinationFilename);
		// Create the destination directory if it doesn't exist
		Files.createDirectories(destinationPath.getParent());
		Files.copy(inputStream, destinationPath);
	}

}
