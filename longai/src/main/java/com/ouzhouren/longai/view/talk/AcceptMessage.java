package com.ouzhouren.longai.view.talk;

import com.google.gson.Gson;
import com.ouzhouren.longai.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class AcceptMessage extends Thread{
	private BufferedReader bufferedReader;
	private Socket socket;
	private boolean status=true;
	public AcceptMessage(Socket s) throws UnknownHostException, IOException{
		this.socket=s;
		this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
//	private String gson(String content,int to,int from){
//		
//		return msg;
//	}
	
	public void run(){
		String msg = null;
		while(true){
			//System.out.println("dasdasda");
			try {
				msg = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("socket close");
				return;
			}

			if(msg!=null){
				System.out.println("recive msg is:"+msg);
				Gson gs= new Gson();
				Message message=gs.fromJson(msg, Message.class);
				System.out.println(message.getMessage());
			}
		}
//		try {
////			this.socket.close();
////			System.out.println("close socket");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
//	public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException{
//		Client client = new Client("localhost",8899,5);
//		AcceptMessage socketConnect = new AcceptMessage(client.getSocket());
//		socketConnect.start();
//		System.out.println("OOBInline status is:"+client.getSocket().getOOBInline());
//		client.sendMessage("test I am 5", 5);
//		sleep(3000);
//		//client.closeSocket();
//		System.out.println("exit");
//	}
}
