package org.example;

public class Main {
	public static void main(String[] args) {
		WebSocketHandler webSocketHandler = new WebSocketHandler();
		ProjectInitializer projectInitializer = new ProjectInitializer(webSocketHandler);
		int port = 8888;
		WebSocketServer webSocketServer = new WebSocketServer(port, projectInitializer);
		webSocketServer.start();
	}
}
