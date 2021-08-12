package kr.or.ddit.cmm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자 요청을 받아서 처리하는 대표 컨트롤러 구현하기
 * @author SEM
 *
 */

public class WebControllerWithoutCommandPattern extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	/**
	 * 요청처리 메서드
	 * @param req
	 * @param resp
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp) {
		
		String reqURI = req.getRequestURI();
		
		
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		System.out.println("요청URL: " + reqURI);
		
		if(reqURI.equals("/member/list.do")) {
			// 목록 조회기능 처리
		}else if(reqURI.equals("/member/insert.do")) { // 등록처리
			if(req.getMethod().equals("GET")) {
				// 등록하기 위한 폼페이지 이동
			}else if(req.getMethod().equals("POST")) {
				// 실제 등록처리
			}
		}else if(reqURI.equals("/member/update.do")) { // 수정처리
			if(req.getMethod().equals("GET")) {
				// 수정하기 위한 폼페이지 이동
			}else if(req.getMethod().equals("POST")) {
				// 실제 수정처리
			}
		}
		
	/**
	     Command 패턴에 대하여...
	     
	     정의 => 사용자 요청에 대한 실제 처리 기능을 커맨드 객체로 캡슐화하여 처리하는 패턴
	     
	    Command : 사용자 요청을 캡슐화한 객체(실제 처리 기능을 구현한 객체)
	    Invoker : 사용자 요청에 대응되는 적당한 커맨드 객체를 찾아 실행 해 주는 역할을 하는 객체
	    
	    장점: 요청을 수행하는 객체(Invoker)로부터 실제 수행처리 기능(Command)을 분리함으로써,
	             새로운 기능을 추가하는 데 보다 수월하다. (유지보수가 쉬워진다.)
	    
	*/
		
		
		
		
		
		
		
		
		
	}
}
