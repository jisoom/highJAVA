package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일 업로드 예제
 * get 방식은 정의가 되어 있지 않아 405 오류가 날 수 있음
 */
@WebServlet("/upload2")
public class UploadServlet2 extends HttpServlet {
	
	private static final String UPLOAD_DIRECTORY = "upload_files";
	//메모리 임계 크기 (정한 수를 넘어가면 레파지토리 위치에 임시 파일로 저장됨)
	//메모리 사용에 제약 (제한 : 3MB)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	//파일 1개 당 최대 크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	//요청 파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//멀티 파트인 경우 getParameter()로 input 정보 가져오기 불가능
		System.out.println("전송자 : " + req.getParameter("sender"));
		
		//멀티 파트인 경우(멀티파트 : http 프로토콜의 바디 부분에 데이터를 여러 부분으로 나눠서 보내는 것)
		if(ServletFileUpload.isMultipartContent(req)) {	
			//폼 필드 데이터 저장 용
			Map<String, String> formMap = new HashMap<>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//1) 파일 저장을 담당할 객체 준비
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir"))); //버쳐머신의 환경설정값
			System.out.println(System.getProperty("java.io.tmpdir")); // C:\Users\PC-19\AppData\Local\Temp\ (임시공간)
			
			// 2) 멀티 파트 데이터로부터 각 파트의 추출을 담당할 객체 준비
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			
			//웹 애플리케이션 루트 디렉토리 기준 업로드 경로 설정
			//getRealPath("") => ("")안에 아무것도 없으면 기본이라는 뜻
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				// 3) 멀티 파트 데이터를 읽기
	            // - 멀티 파트로부터 각 파트를 분리하여 fileFactory에게 전달한다.
	            // - fileFactory는 각 파트로부터 파라미터 이름과 값, 바이너리 데이터 등을
	            // 추출하여 FileItem 객체에 담는다.
	            // - FileItem 목록을 리턴한다.
				 
				//파싱시작 
				List<FileItem> formItems = upload.parseRequest(req); //.parseRequest 메서드를 사용하여 파싱시작
				
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) {
						//파일인 경우
						if(!item.isFormField()) {
							//파일 명만 추출
							String fileName = new File(item.getName()).getName();
														// '/' 사용 가능 ⓑ 하드코딩 => 구분자는 윈도우, 리눅스에 따라 달라짐
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							
							//업로드 파일 저장
							item.write(storeFile);
							
						//폼 필드인 경우
						} else {
							//폼 필드의 값이 한글인 경우 해당 문자열을 적절히 변환해 주어야 함
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for(Entry<String, String> entry : formMap.entrySet()) {
				System.out.println("파라미터 명 : " + entry.getKey());
				System.out.println("파라미터 값 : " + entry.getValue());
			}
			
			resp.setContentType("text/html");
			resp.getWriter().println("업로드 완료 됨");
		}
	}
}