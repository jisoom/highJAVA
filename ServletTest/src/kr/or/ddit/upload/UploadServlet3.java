package kr.or.ddit.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, maxFileSize = 1024 * 1024 * 40, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/upload3")
public class UploadServlet3 extends HttpServlet {
	private static final String UPLOAD_DIRECTORY = "upload_files";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		resp.setContentType("text/html");
		
		try {
			String fileName = "";
			
			for(Part part : req.getParts()) { //여러개의 멀티파트 가져옴
				System.out.println(part.getHeader("content-disposition"));
				
				fileName = getFileName(part);
				if(fileName != null && !fileName.equals("")) { //파일이 있으면..
					//파일 저장
					part.write(uploadPath + File.separator + fileName);
					resp.getWriter().println(fileName + " 업로드 완료 됨");
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Part 인터페이스 사용 시 파라미터 값은 getParameter()로 얻어올 수 있음
		System.out.println("파라미터 값 : " + req.getParameter("sender"));
	}

	
	/**
	 * Part 객체를 파싱하여 파일 이름 추출
	 * @param part
	 * @return 파일 명 (파일 명이 존재하지 않으면 null)
	 */
	private String getFileName(Part part) {
		/*
		 * Content-Disposition 헤더
		 * 
		 *  1. response header에 사용되는 경우	ex) 파일 다운로드
		 *     
		 *     Content-Disposition: inline(default)
		 *     Content-Disposition: attachment							//파일 다운로드
		 *     Content-Disposition: attachment; filename="name.jpg"		//해당 이름으로 다운로드
		 * 
		 *  2. multipart body를 위한 헤더 정보로 사용되는 경우	ex) 파일 업로드
		 *     
		 *     Content-Disposition: form-data
		 *     Content-Disposition: form-data; name="fieldName"							//폼 데이터
		 *     Content-Disposition: form-data; name="fieldName"; filename="name.jpg"	//파일 데이터
		 */
		
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 1).trim().replace("\"", ""); //// \" ==> "를 뜻함 "name.jpg"를 name.jpg로 바꿈
			}
		}
		
		return null;	//filename이 없는 경우 (폼필드)
	}
}
