package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

public class DeleteMemberHandler implements CommandHandler {
	
	private IMemberService memService = MemberServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true; // 리다이렉트
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		
		
		
		// 3. 회원정보 삭제하기
		int cnt = memService.deleteMember(memId);
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		String redirectURL = req.getContextPath() 
				+ "/member/list.do?msg=" + URLEncoder.encode(msg, "utf-8");
		
		return redirectURL;
	}

}
