package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
	public static void main(String[] args) {
		//입출력의 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192byte(8Kb)로 설정된다
			//버퍼 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5); //바이트 단위 //단위를 너무 크게 잡으면 램을 많이 잡아먹음
			
			for (int i = '1'; i <='9'; i++) { //하드디스크 자체에 들어있는 데이터는 1~5 까지 되있음 (버퍼 사이즈가 5기 때문에 다 꽉차면 write함 // 6~9까지는 안들어가짐)
				System.out.println("for문" + i);
				bos.write(i);
			}
			
			bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.(close시 자동으로 호출됨)(버퍼가 5바이트기 때문에 5꽉차면 출력하는데 6~9까지는 출력이 안됨 ==>flush해줌)
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bos.close();
				System.out.println("작업 끝...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}