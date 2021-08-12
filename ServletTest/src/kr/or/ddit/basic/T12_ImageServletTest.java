package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/T12_ImageServletTest")
public class T12_ImageServletTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Content-Type 설정
		// 요청 없이 바로 이미지 파일 쏘기
		// 컨텐츠 타입을 확인해야함
		resp.setContentType("image/jpeg");
		
		ServletOutputStream out = resp.getOutputStream();
		FileInputStream fis = new FileInputStream("d:/D_Other/cat.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int readBytes = 0;	//읽은 바이트 수
		
		while((readBytes = bis.read()) != -1) {
			bos.write(readBytes);
		}
		
		bos.close();
		bis.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
