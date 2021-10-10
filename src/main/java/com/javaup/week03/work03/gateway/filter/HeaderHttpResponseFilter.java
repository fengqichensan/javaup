package com.javaup.week03.work03.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter{

	@Override
	public void filter(FullHttpResponse response) {
		response.headers().add("course", "java training");
	}

}
