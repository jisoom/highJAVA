package kr.or.ddit.tcp;

import java.net.Socket;

public class TcpChatClient {
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("localhost", 7777); //7777포트는 접속을 하기 위해 사용하는 포트
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}