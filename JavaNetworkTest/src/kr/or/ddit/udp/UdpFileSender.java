package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	public static void main(String[] args) {
		
		String serverIp = "127.0.0.1";
		int port = 8888;
		
		File file = new File("d:/D_Other/bo_left.png"); //보내줄 파일
		
		DatagramSocket socket = null;
		
		//파일 있는지 확인
		if(!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}
		long fileSize = file.length();
		long totalReadBytes = 0;
		double startTime = 0;
		
		try {
			socket = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(serverIp);
			startTime = System.currentTimeMillis();
			
			String str = "start"; //전송 시작을 알려주기 위한 문자열
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			socket.send(dp); //start
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];
			
			str = String.valueOf(fileSize); //총 파일 사이즈 정보 (문자열로 바꿈)
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			socket.send(dp); //파일 사이즈 정보
			
			while(true) {
				Thread.sleep(10); //패킷 전송간의 시간 간격 주기 위해서...
				int readBytes = fis.read(buffer, 0, buffer.length); //temp했던 거처럼 0부터 buffer사이즈 만큼 처리
				if(readBytes == -1) {// 다읽음
					break;
				}
				
				//읽어온 파일 내용 패킷에 담기
				dp = new DatagramPacket(buffer, readBytes, serverAddr, port); //상대방 주소, 포트번호
				socket.send(dp);
				
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes
									+"/" + fileSize + "Bytes("
									+(totalReadBytes * 100 / fileSize)
									+ "%)");
			}
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + "(초)");
			System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s");
			
			System.out.println("전송 완료...");
			
			fis.close();
			socket.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}