package com.virtualthreads;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ClientBootVirtualThreadsApp {

	@Value("${spring.threads.virtual.enabled:false}")
	private boolean virtualThreadFlag;

	public static void main(String[] args) {
		SpringApplication.run(ClientBootVirtualThreadsApp.class, args);
		log.info("availableProcessors: {}", Runtime.getRuntime().availableProcessors());
	}
	// @Bean
	// public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
	// 	return protocolHandler -> protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
	// }

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		if(virtualThreadFlag) {
			log.info("Started App in Tomcat [VirtualThread] mode");
		}else{
			log.info("Started App in Tomcat [PlatformThread] mode");
		}

	}
}
