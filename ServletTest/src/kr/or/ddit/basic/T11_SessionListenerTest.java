package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/T11_SessionListenerTest")  // <- 사용하면 web.xml에 등록하지 않아도 바로 실행가능
public class T11_SessionListenerTest extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpSession 객체 생성 및 소멸시키기
		HttpSession session = req.getSession();
		
		session.setAttribute("ATTR1", "속성1"); // 추가
		session.setAttribute("ATTR1", "속성11"); // 변경
		session.setAttribute("ATTR2", "속성2"); // 추가
		
		session.removeAttribute("ATTR1"); // 제거
		
		
		//Http 세션 영역내에 HttpSessionBindingListener를 구현한 객체가 바인딩되면 호출됨
		MySessionBindingListener bindingListener = new MySessionBindingListener();

		session.setAttribute("obj", bindingListener);
		session.removeAttribute("obj");
		
		session.invalidate(); // 세션 제거
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}