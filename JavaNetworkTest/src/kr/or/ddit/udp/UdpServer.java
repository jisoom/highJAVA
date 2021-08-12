package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket socket;
	
	//프로그램 시작
	public void start() throws IOException {
		
		//포트 8888번을 사용하는 소켓을 생성한다.
		socket = new DatagramSocket(8888);
		DatagramPacket inPacket, outPacket;
		
		//데이터 그램 패킷에 넣을 데이터는 byte로 해야됨
		byte[] inMsg = new byte[1]; //패킷 수신을 위한 바이트 배열
		byte[] outMsg; //패킷 송신을 위한 바이트 배열
		
		while(true) {
			//데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			System.out.println("패킷 수신 대기중...");
			
			//패킷을 통해 데이터를 수신(Receive)한다.
			socket.receive(inPacket);
			
			System.out.println("패킷 수신 완료.");
			
			//수신한 패킷으로부터 client의 IP주소와 port번호를 얻는다.
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			//서버의 현재 시간을 시분초 형태로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); //시간 문자열을 byte배열로 변환 (byte로 넣어야 되기 때문에)
			
			//패킷을 생성해서 client에게 전송(send)한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port); //보낼메시지, 메시지사이즈, 상대방 주소와 포트번호
			socket.send(outPacket); //전송 시작
		}
	}
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
}