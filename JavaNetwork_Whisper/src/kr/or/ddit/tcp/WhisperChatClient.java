package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class WhisperChatClient {
	Scanner sc = new Scanner(System.in);
	private String nickname;
	
	public static void main(String[] args) {
		new WhisperChatClient().startClient();
	}
	
	public void startClient() {
		System.out.print("닉네임 : ");
		nickname = sc.next();
		
		Socket socket = null;
		
		try {
			String address = "127.0.0.1";
			socket = new Socket(address, 7777);
			
			System.out.println("서버에 입장했습니다.");
			
			//송신용
			Sender sender = new Sender(socket, nickname);
			
			//수신용
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//송신
	class Sender extends Thread {
		private DataOutputStream dos;
		private String nickname;
		private Scanner sc = new Scanner(System.in);
		
		public Sender(Socket socket, String nickname) {
			this.nickname = nickname;
			
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		@Override
		public void run() {
			try {
				if(dos != null) {
					dos.writeUTF(nickname);
				}
				
				while(dos != null) {
					dos.writeUTF(sc.nextLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//수신
	class Receiver extends Thread {
		private DataInputStream dis;
		
		public Receiver(Socket socket) {
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(dis != null) {
				try {
					System.out.println(dis.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
