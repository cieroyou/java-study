package com.sera.tutorial.netty.bytemessenger.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// SimpleChannelInboundHandler<ByteBuf> 를 사용하면 다음 에러 발생하여 ChannelInboundHandlerAdapter 로 변경함
// WARNING: An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.
// io.netty.util.IllegalReferenceCountException: refCnt: 0, decrement: 1
// 버퍼풀 반납 관련한 에러로 보임
public class ByteDataHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
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
		ctx.writeAndFlush(buf); // Fix: cxt.writeAndFlush(buf) -> cxt.writeAndFlush(msg)
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}
}