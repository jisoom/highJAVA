package kr.or.ddit.upload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Oreilly의 MultipartRequest를 이용한 파일 업로드 예제
 * (생성자 호출과 동시에 파일이 생성되기 때문에 선택적인 파일 생성 처리 불가)
 */
@WebServlet("/upload1")
public class UploadServlet1 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일 업로드는 get방식 사용 불가능 (post만 사용함)
		//MultipartRequest(request, 저장 경로 [, 최대 허용 크기, 인코딩 캐릭터 셋, 동일한 파일 명 보호 여부])
		String encoding = "UTF-8";
		int maxFileSize = 5 * 1024 * 1024;
		
		//객체만 생성하면 업로드는 끝난 것~!
		//동일한 파일 명 보호 여부 : 같은 파일을 여러 번 업로드할 경우 덮어쓰기 하지 않기 위해 파일 명 저장 규칙을 만드는 것
		//ex) 같은 tulip.jpg를 여러 번 업로드 할 경우 내부적으로 저장할 때 업로드 시간(규칙)으로 저장한 뒤 tulip.jpg와 매핑
		MultipartRequest mr = new MultipartRequest(req, "d:/D_Other/", maxFileSize, encoding, new DefaultFileRenamePolicy());
		
		//***********************************************************
		//화면 페이지
		//저장할 경로 ex)name.zip  name2.zip  name3.zip
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.println("업로드 완료 : " + mr.getFile("fname"));
	}
}