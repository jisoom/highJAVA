package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07_ServletContextTest extends HttpServlet {
	
	/* 
	 * 서블릿 컨텍스트 객체
	 * 
	 *  1. 서블릿 프로그램이 컨테이너와 정보를 주고 받기 위한 메소드를 제공
	 *  2. 웹 애플리케이션 당 1개씩 생성됨
	 *  3. 서블릿이 생성되고 초기화 메소드(init) 실행 시 ServletConfig 객체를 통해 얻을 수 있음
	 *     @Override
	 *     public void init(ServletConfig config) throws ... {...}
	 *  
	 *  애플리케이션을 대표하는 객체!
	 *  애플리케이션 전체에 대한 정보, 애플리케이션 멤버들이 모두 공유해야 하는 정보를 저장
	 *  (그 외의 정보는 가능한 한 HttpServletRequest에 저장)
	 * 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext ctx = req.getServletContext();
		
														//자주 사용하는 메소드
		System.out.println("서블릿 컨텍스트의 기본 경로 : " + ctx.getContextPath());
		System.out.println("서버 정보 : " + ctx.getServerInfo());
		System.out.println("서블릿 API의 메이저 버전 정보 : " + ctx.getMajorVersion());
		System.out.println("서블릿 API의 마이너 버전 정보 : " + ctx.getMinorVersion());
						  //배포 기술자 = (주로) web.xml
						  //display-name
		System.out.println("배포 기술자에 기술된 컨텍스트 명 : " + ctx.getServletContextName());
						  //WebContent에 있는 파일, 디렉토리
		System.out.println("리소스 경로 목록 : " + ctx.getResourcePaths("/"));
		System.out.println("모든 등록된 서블릿 목록 맵 : " + ctx.getServletRegistrations().toString());
		System.out.println("파일에 대한 MIME 타입 정보 : " + ctx.getMimeType("abc.mp3"));
													   //자주 사용하는 메소드
		System.out.println("파일 시스템 상의 실제 경로 : " + ctx.getRealPath("/"));
		System.out.println("리소스의 URL 정보 : " + ctx.getResource("/index.html"));
		
		//속성 값 설정
		ctx.setAttribute("attr1", "속성1");
		
		//속성 값 변경
		ctx.setAttribute("attr1", "속성2");
		
		//속성 값 가져오기
		System.out.println("attr1의 속성 값 : " + ctx.getAttribute("attr1"));
		
		//속성 값 지우기
		ctx.removeAttribute("attr1");
		
		//로깅 작업하기
		ctx.log("서블릿 컨텍스트를 이용한 로깅 작업 중입니다.");
		
		//포워딩 처리
		//"/T06_ServletSessionTest"에 req, resp을 전달
		//doPost(..)에서 doGet(..)을 호출하는 것과 유사한 기능
		//URL에 변화 X
		ctx.getRequestDispatcher("/T06_ServletSessionTest").forward(req, resp);
		
		//req에도 존재
		//req.getRequestDispatcher("/T06_ServletSessionTest").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}