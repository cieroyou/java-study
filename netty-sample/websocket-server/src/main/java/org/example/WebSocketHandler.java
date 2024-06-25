package org.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

// SimpleChannelInboundHandler: 리소스를 자동으로 해제하여 리소스관리 번거로움을 덜어줌
// TextWebSocketFrame: WebSocket 프레임의 텍스트 데이터를 처리
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.printf("new connection: %s", ctx.channel().id().asLongText());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		System.out.printf("receive msg：%s", msg.text());

		// JSONObject jsonObject = JSONUtil.parseObj(msg.text());
		// String uid = jsonObject.getStr("uid");
		// NettyConfig.getChannelMap().put(uid, ctx.channel());
		// AttributeKey<String> key = AttributeKey.valueOf("userId");
		// ctx.channel().attr(key).setIfAbsent(uid);
		ctx.channel().writeAndFlush(new TextWebSocketFrame("server receive msg"));
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// todo: 연결(세선, 채널)이 해제되면 연결 해제 및 정리
		System.out.printf("user disonline : %s", ctx.channel().id().asLongText());
		// NettyConfig.getChannelGroup().remove(ctx.channel());
		// removeUserId(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// todo: 연결(채널)에서 에러가 발생하면 연결 해제 및 정리
		System.out.printf("error: %s", cause.getMessage());
		// NettyConfig.getChannelGroup().remove(ctx.channel());
		// removeUserId(ctx);
		ctx.close();
	}

	/**
	 * delete the relation that use and channel
	 */
	private void removeUserId(ChannelHandlerContext ctx) {
		// AttributeKey<String> key = AttributeKey.valueOf("userId");
		// String userId = ctx.channel().attr(key).get();
		// NettyConfig.getChannelMap().remove(userId);
	}
}
