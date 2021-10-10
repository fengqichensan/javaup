package com.javaup.week03.work03.backend.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				){
			
			String gender;
			String inputStr;
			while ( true ) {
				inputStr = br.readLine();
				if (inputStr.endsWith("HTTP/1.1")) {
					String uri = inputStr.split(" ")[1];
					gender = uri.substring(uri.lastIndexOf("/") + 1);
					break;
				}
			}
			printWriter.println("HTTP/1.1 200 OK");
			printWriter.println("Content-Type:text/html;charset=utf-8");
			String body = "hello, " + gender;
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
