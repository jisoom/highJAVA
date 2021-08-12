package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	/*
	 * 인코딩 방식
	 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나뉜다.
	 * 원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서
	 * EUC-KR 방식에서 확장하였기 때문에 MS949라고도 부른다.
	 * 한글 Windows의 메모장에서 이야기 하는 ANSI 인코딩이란 , CP949(Code Page 949)를 말한다.
	 * 
	 * -MS949 =>윈도우의 기본 한글 인코딩 방식(ANSI계열)
	 * -UTF-8 =>유니코드 UTF-8 인코딩 방식(영문자 및 숫자 : 1byte, 한글 : 3bytes) =>가변적
	 * US-ASCII => 영문 전용 인코딩 방식
	 * 
	 * 유니코드(Unicode)
	 * 서로 다른 문자 인코딩을 사용하는 컴퓨터간의 문서 교환에 어려움을 겪게되고, 
	 * 이런 문제점을 해결하기 위해 전 세계의 모든 문자를 하나의 통일된 문자 집합(CharSet)으로 표현함
	 */
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			/*
			 * FileInputStream 객체를 생성한 후 이 객체를 매개변수로 받는 InputStreamReader객체를 생성한다.
			 */
			
			//fis = new FileInputStream("d:/D_Other/test_ansi.txt"); //이걸로만은 한글 출력할 수 없음 (byte 단위이기 때문에)
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			isr = new InputStreamReader(fis, "MS949"); //인풋스트림리더를 통해 바이트기반을 문자기반으로 처리해줌 
			//(메모장 타입을 ANSI로 하고 여기서 UTF-8을 적으면 이상한 문자로 나옴, 반대로 메모장 타입을 UTF-8로 하고 여기서 MS949(CP949또는 EUC-KR)적으면 깨짐(영어는 나옴))
			
			int c;
			while( (c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				isr.close(); //보조 스트림만 닫아도 된다.
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}