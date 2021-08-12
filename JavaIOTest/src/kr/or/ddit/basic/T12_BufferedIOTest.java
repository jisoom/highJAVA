package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T12_BufferedIOTest {
	/*
	 * 문자 기반의 Buffered스트림 예제
	 */
	
	public static void main(String[] args) throws IOException {
	
		//이클립스에서 만든 자바프로그램이 실행되는 기본 위치는
		//해당 '프로젝트 폴더'가 기본 위치가 된다.
		FileReader fr = new FileReader("src/kr/or/ddit/basic/T11_BufferedIOTest.java");  //상대경로
		//   ./src/kr/or/ddit/basic/T11_BufferedIOTest.java 앞에 ./생략함
		
/*		int c;
		while( (c=fr.read()) != -1) {
			System.out.print((char)c);
		}
		fr.close();*/
		
		//한줄씩 읽을 수 있도록 해주는 readLine을 이용하기 위해서 BufferedReader 사용
		BufferedReader br = new BufferedReader(fr);
		String temp = "";
		for (int i = 1; (temp = br.readLine()) != null ; i++) { //더이상 읽을 게 없으면 null이 됨 i=1 ==>코드 줄번호 
			System.out.printf("%4d : %s\n", i, temp); //줄번호 표시
		}
		br.close();
	}
}