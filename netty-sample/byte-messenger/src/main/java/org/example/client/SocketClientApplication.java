package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.RequiredArgsConstructor;

// 1. 응답메세지 처리하는 ChannelInboundHandlerAdapter 정의하기
// 2. Server main 메소드 작성
@RequiredArgsConstructor
public class SocketClientApplication {

	public static void main(String[] args) throws InterruptedException {
		String host = "localhost";
		int port = 8888;
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				public void initChannel(SocketChannel ch)
					throws Exception {
					ch.pipeline().addLast(new ClientHandler());
				}
			});

			ChannelFuture future = b.connect(host, port).sync();
			Channel channel = future.channel();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String line = in.readLine();
				ByteBuf data = getData(1);
				channel.writeAndFlush(data);

				if (line.equals("exit")) {
					break;
				}

			}

			// f.channel().closeFuture().sync();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	private static ByteBuf getData(int value) {
		byte[] data = new byte[] {
			1, // protocolVersion
			2, // systemId
			3, // objectId
			4, // messageID
			1, // locked (true)
			0, 0, (byte)128, 63, // position.x (1.0f)
			0, 0, 0, 64,  // position.y (2.0f)
			0, 0, 64, 64, // position.z (3.0f)
			0, 0, (byte)128, 63, // rotation.x (1.0f)
			0, 0, 0, 64,  // rotation.y (2.0f)
			0, 0, 64, 64, // rotation.z (3.0f)
			0, 0, (byte)128, 63, // rotation.w (1.0f)
			0, 0, (byte)128, 63, // scale.x (1.0f)
			0, 0, 0, 64,  // scale.y (2.0f)
			0, 0, 64, 64, // scale.z (3.0f)
			(byte)255, // checksumA
			(byte)254  // checksumB
		};
		return Unpooled.wrappedBuffer(data);
	}
}


