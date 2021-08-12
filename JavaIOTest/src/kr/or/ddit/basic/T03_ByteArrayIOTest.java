package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		//인소스에 적힌 것을 아웃소스에 복사해서 출력하기(인풋용을 가져다 읽고, 아웃풋용을 가져다 씀 (read, write))
		byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; //바이트 배열
		byte[] outSrc = null; //바이트 배열
		
		//스트림 선언 및 객체 생성
		ByteArrayInputStream bais = null; //바이트어레이인풋스트림 선언
		bais = new ByteArrayInputStream(inSrc); //객체 생성 (바이트 배열인 inSrc를 읽을 거기 때문에 읽을 정보를 안에 넣어줌)
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //바이트어레이아웃풋스트림 선언
		
		int data; //읽어온 자료를 저장할 변수
		
		//read() 메서드 => byte 단위로 자료를 읽어와 int형으로 반환한다.
		//			  => 더이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = bais.read()) != -1) { //10번  (int : 4byte , byte : 1byte)
			baos.write(data); //출력하기 (int값 data를 write함) ByteArrayOutputStream의 내부버퍼(자기 객체 내)에 저장
		}
		
		//출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray(); //바이트 어레이로 값을 리턴해줌
		System.out.println("inSrc =>" + Arrays.toString(inSrc));
		System.out.println("outSrc =>" + Arrays.toString(outSrc));
		
		//스트림 객체 닫기 (예외 던짐)
		bais.close();
		baos.close();
	}
}