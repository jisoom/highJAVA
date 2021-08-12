package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class SelectAllMemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 서비스 객체 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//2. 회원 정보 조회
		List<MemberVO> memList = memService.getAllMemberList();
		
		req.setAttribute("memList", memList);
		
		//3. 뷰페이지로 전달 (포워딩 작업...(화면 내가 안그리고 넘겨줌 니가해..))
		RequestDispatcher dispatcher = 
				req.getRequestDispatcher("/member/list.jsp"); //디스패처 만듦
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}