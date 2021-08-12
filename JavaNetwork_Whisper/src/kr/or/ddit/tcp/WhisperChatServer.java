package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WhisperChatServer {
	private Map<String, Socket> clients;
	
	public static void main(String[] args) {
		new WhisperChatServer().startServer();
	}
	
	public WhisperChatServer() {
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public void startServer() {
		Socket socket = null;
		
		try(ServerSocket server = new ServerSocket(7777)) {
			System.out.println("서버를 오픈합니다.");
			
			while(true) {
				socket = server.accept();
				System.out.println("- [" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 입장");
				
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	class ServerReceiver extends Thread {
		private String nickname;
		private Socket socket;
		private DataInputStream dis = null;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			//이름 입력 받기
			try {
				nickname = dis.readUTF();
				sendMessageAll(nickname + "님이 입장했습니다.");
				
				clients.put(nickname, socket);
				
				while(dis != null) {
					sendMessageTo(dis.readUTF());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				sendMessageAll(nickname + "님이 퇴장했습니다.");
				
				clients.remove(nickname);
				System.out.println("- [" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 퇴장");
			}
				
			
		}
		
		public void sendMessageAll(String msg) {
			Iterator<String> itr = clients.keySet().iterator();
			
			while(itr.hasNext()) {
				try {
					DataOutputStream dos = new DataOutputStream(clients.get(itr.next()).getOutputStream());
					dos.writeUTF(msg);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void sendMessageTo(String msg) {
			String[] org = msg.split(" ");
			
			try {
				DataOutputStream dosForReturn = new DataOutputStream(socket.getOutputStream());
				
				if(!org[0].equals("/w")) {
					dosForReturn.writeUTF("[서버] >> 명령어를 잘못 입력했습니다. (/w 닉네임 내용)");
					return;
				}
				
				Set<String> keys = clients.keySet();
				if(!keys.contains(org[1])) {
					dosForReturn.writeUTF("[서버] >> 닉네임을 잘못 입력했습니다.");
					return;
				}
				
				String message = "";
				for(int i = 2 ; i<org.length ; i++) {
					message += org[i] + " ";
				}
				
				DataOutputStream dosForWhisper = new DataOutputStream(clients.get(org[1]).getOutputStream());
				dosForWhisper.writeUTF("[" + nickname + "] >> " + message);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
