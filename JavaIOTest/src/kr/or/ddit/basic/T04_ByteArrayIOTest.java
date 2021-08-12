package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		byte[] outSrc = null;
		
		//읽어야할 때 :input
		//써줘야할 때 : output
		byte[] temp = new byte[4]; //자료 읽을 때 사용할 배열
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc); //inSrc에 있는 내용 넣어줌
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			//available() => 읽어올 수 있는 byte 수를 반환
			while(bais.available() >0) { //bais의 읽어올 수 있는 byte 수 : 10
				/*//temp 배열 크기만큼 읽어와 temp배열에 저장한다.
				bais.read(temp); //4바이트 넣어서 읽음
				
				//temp 배열의 내용을 출력한다.
				baos.write(temp);
				*/
				//실제 읽어온 byte수를 반환한다.
				int len = bais.read(temp);
				
				//temp 배열의 내용 중에서 0번째 부터 len개수만큼 출력한다.
				baos.write(temp, 0, len); //0부터 len까지 개수 출력
			
				
				System.out.println("temp : " + Arrays.toString(temp)); //4개씩인데 10개있기 때문에 4, 4, 4(2개 부족함 ==> 기존에 남아있던 6,7나옴)
			}
			System.out.println();
			outSrc = baos.toByteArray();
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			bais.close();
			baos.close();
					
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}