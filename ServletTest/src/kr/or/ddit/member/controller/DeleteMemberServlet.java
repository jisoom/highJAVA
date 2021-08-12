package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class DeleteMemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		
		//2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//3. 회원정보 삭제하기
		int cnt = memService.deleteMember(memId);
		
		String msg ="";
		
		if(cnt >0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		//4. 목록조회 화면으로 이동 (redirect 사용)
/*		String redirectUrl = req.getContextPath() + "/member/list";
		resp.sendRedirect(redirectUrl);*/
		resp.sendRedirect(req.getContextPath() + "/member/list?msg=" + URLEncoder.encode(msg, "UTF-8"));
		
		//redirect를 사용하면 요청을 2번해서 delete하고나서 url이 list로 변함 (이게 더 나음)
		//forward를 사용하면 delete하고나서 url이 여전히 delete임 
		//==> 새로고침을 하게되면 url이 계속 delete가 됨
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}