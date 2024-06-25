package org.example.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx)
		throws Exception {

		// RequestData msg = new RequestData();
		// msg.setIntValue(123);
		// msg.setStringValue(
		// 	"all work and no play makes jack a dull boy");
		// ChannelFuture future = ctx.writeAndFlush(msg);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
		throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		byte[] array;
		int offset;
		int length;

		if (buf.hasArray()) {
			array = buf.array();
			offset = buf.arrayOffset() + buf.readerIndex();
			length = buf.readableBytes();
		} else {
			array = new byte[buf.readableBytes()];
			offset = 0;
			length = array.length;
			buf.getBytes(buf.readerIndex(), array);
		}
		System.out.println("Received: " + buf);
	}
}