package org.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class ProjectInitializer extends ChannelInitializer<SocketChannel> {
	static final String WEBSOCKET_PROTOCOL = "WebSocket";
	//todo: property로 뺴기
	static final String WEBSOCKET_PATH = "/websocket";

	private final WebSocketHandler webSocketHandler;

	public ProjectInitializer(WebSocketHandler webSocketHandler) {
		this.webSocketHandler = webSocketHandler;
	}

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		ChannelPipeline pipeline = socketChannel.pipeline();
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new ObjectEncoder()); //Java 객체 -> ByteBuf
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, WEBSOCKET_PROTOCOL));
		pipeline.addLast(webSocketHandler);
	}
}