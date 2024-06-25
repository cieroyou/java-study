package org.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

public class WebSocketServer {
	private final int port;
	private final ProjectInitializer projectInitializer;

	public WebSocketServer(int port, ProjectInitializer projectInitializer) {
		this.port = port;
		this.projectInitializer = projectInitializer;
	}
	public void start(){
		// Configure the server.
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(projectInitializer);

			Channel ch = b.bind(port).sync().channel();
			System.out.println("Web socket server started at port " + port + '.');
			System.out.println("Open your browser and navigate to http://localhost:" + port + '/');
			ch.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
