package kr.or.ddit.udp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileReceiver {
	public static void main(String[] args) throws IOException {
		
		int port= 8888;
		
		long fileSize = 0;
		long totalReadBytes =0;
		
		byte[]buffer = new byte[1000];
		int readBytes = 0;
		
		System.out.println("파일 수신 대기중...");
		
		DatagramSocket socket = new DatagramSocket(port);
		FileOutputStream fos = new FileOutputStream("d:/D_Other/aaa.png");
		
		//수신용
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length); //송신용인지 수신용인지 구분하는 방법 (송신용 : 파라미터 3,4개 서버주소랑 포트번호)
		
		socket.receive(dp);
		String str = new String(dp.getData()).trim();
		
		if(str.equals("start")) { //sender에서 전송을 시작한 경우.. 파일 사이즈 정보 받음
			dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			double startTime = System.currentTimeMillis();
			
			while(true) {
				socket.receive(dp);
				//str = new String(dp.getData()).trim();
				
				readBytes= dp.getLength();
				fos.write(dp.getData(), 0, readBytes);
				totalReadBytes += readBytes;
				
				System.out.println("진행 상태 : " + totalReadBytes
									+"/" + fileSize + "Bytes("
									+(totalReadBytes * 100 / fileSize)
									+ "%)");
				
				if(totalReadBytes >= fileSize) {
					break;
				}
			}
				double endTime = System.currentTimeMillis();
				double diffTime = (endTime - startTime) / 1000;
				double transferSpeed = (fileSize / 1000) / diffTime;
				
				System.out.println("걸린 시간 : " + diffTime + "(초)");
				System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s");
				
				System.out.println("수신 완료...");
				
				fos.close();
				socket.close();
		}else {
			System.out.println("비정상 데이터 발견!!!");
			fos.close();
			socket.close();
		}
			
	}
}
