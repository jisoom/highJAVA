package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {
	
	//시작
	public void start() throws IOException {
		DatagramSocket socket = new DatagramSocket(); //소켓 생성
		InetAddress serverAddress = InetAddress.getByName("192.168.42.149");
		
		//데이터가 저장될 공간으로 byte배열을 생성한다.(패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888);
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		socket.send(outPacket); //전송
		socket.receive(inPacket); //수신
		
		System.out.println("현재 서버 시간 => " + new String(inPacket.getData())); 
		
		socket.close(); //소켓 종료
	}
	
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
}