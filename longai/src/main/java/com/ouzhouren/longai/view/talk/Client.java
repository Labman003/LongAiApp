package com.ouzhouren.longai.view.talk;

import com.google.gson.Gson;
import com.ouzhouren.longai.common.utils.MyLogger;
import com.ouzhouren.longai.model.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static PrintWriter printWriter;
	private static int from;
	private static Socket socket;
	
	public static PrintWriter getPrintWriter() {
		return printWriter;
	}
	public static void setPrintWriter(PrintWriter printWriter) {
		printWriter = printWriter;
	}
	
	public static int getFrom() {
		return from;
	}
	
	public static void setFrom(int from) {
		Client.from = from;
	}
	
	public static Socket getSocket() {
		return socket;
	}
	
	public static void setSocket(Socket socket) {
		Client.socket = socket;
	}
	
	public Client(String location,int port,int from) throws UnknownHostException, IOException{
		MyLogger logger = MyLogger.benLog();
		logger.i("client1");
		this.socket = startConnect(location,port);
		logger.i("client2");
		this.printWriter = new PrintWriter(socket.getOutputStream(),true);
		logger.i("client3");
		this.printWriter.println(from);
		logger.i("client4");
		this.from = from;
	}
	
	public Socket startConnect(String location,int port) throws UnknownHostException, IOException{
		try {
			Socket s = new Socket(location,8899);
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("the server port is not open");
			return null;
		}
		
	}
	
	public static Boolean sendMessage(String content,int to){
		Message message = new Message();
		message.setMessage(content);
		message.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
		message.setTo(to);
		message.setFrom(from);
		Gson gson = new Gson();
		String msg = gson.toJson(message);
		System.out.println(msg);
		printWriter.println(msg);
		return true;
	}
	
	public static void closeSocket() throws IOException{
		socket.shutdownInput();
		socket.shutdownOutput();
		socket.close();
		System.out.println("socket is closed");
	}
}
