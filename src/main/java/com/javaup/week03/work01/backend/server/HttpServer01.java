package com.javaup.week03.work01.backend.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class HttpServer01 {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8801);){
			System.out.println("http server 01 start ...");
			while (true) {
				Socket socket = serverSocket.accept();
				service(socket);
			}
		} catch (Exception e) {
		}
		
	}
	
	private static void service(Socket socket) {
		try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)){
			
			printWriter.println("HTTP/1.1 200 OK");
			printWriter.println("Content-Type:text/html;charset=utf-8");
			String body = "hello, nio 1";
			printWriter.println("Content-Length:" + body.getBytes(Charset.forName("utf-8")).length);
			printWriter.println();
			printWriter.write(body);
			printWriter.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
