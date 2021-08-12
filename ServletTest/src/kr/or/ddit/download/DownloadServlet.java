package kr.or.ddit.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서버에 있는 파일을 읽어서 상대방에게 쏴줌
@WebServlet("/filedown")
public class DownloadServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = "12.png";
		
		//파일 다운로드 처리를 위한 응답 헤더 정보 설정하기
		resp.setContentType("application/octet-stream"); //바이너리 데이터
		
		//이거를 안쓰면 브라우저에서 파일이 열림(inline방식)
		resp.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\""); 
		/**
		 * Content-Disposition 헤더에 대하여...
		 * 
		 * 1. response header에 사용되는 경우... ex)파일 다운로드
		 * 
		 * Content-Disposition : inline(default)
		 * Content-Disposition : attachment  //파일 다운로드
		 * Content-Disposition : attachment; filename = "name.jpg" // 해당이름으로 다운로드
		 * 
		 * 2. multipart body를 위한 헤더 정보로 사용되는 경우... ex)파일 업로드
		 * Content-Disposition : form-data
		 * Content-Disposition : form-data; name="fieldName" filename없으면 폼데이터
		 * 첨부파일이 있으면 filename까지 옴
		 * Content-Disposition : form-data; name="fieldName"; filename="name.jpg"
		 */
		
		BufferedInputStream bis = 
				new BufferedInputStream(new FileInputStream("d:/D_Other/" + fileName));
		BufferedOutputStream bos = 
				new BufferedOutputStream(resp.getOutputStream());
		
		int c =0;
		while((c = bis.read())!= -1) {
			bos.write(c);
		}
		
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}