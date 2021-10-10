package com.javaup.week03.work03.gateway.filter;

import java.util.Random;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class UriHttpReauestFilter implements HttpRequestFilter{

	@Override
	public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		fullRequest.setUri(this.editUri(fullRequest.uri()));
	}

	private String editUri (String uri) {
		String result = uri;
		Random random = new Random(System.currentTimeMillis());
		String gender = null;
		if (random.nextInt(2) == 1) {
			gender = "boy";
		}else {
			gender = "girl";
		}
		if (uri.endsWith("/")) {
			result = result + gender;
		}else {
			result = result + "/" + gender;
		}
		return result;
	}
}
