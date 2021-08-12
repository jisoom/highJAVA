package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.handler.CommandHandler;
import kr.or.ddit.cmm.vo.PagingVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class ListMemberHandler implements CommandHandler {
	
	private static final String VIEW_PAGE = "/WEB-INF/views/member/list.jsp";
	
	private IMemberService memService = MemberServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 1. 요청 페이지 번호 가져오기
		int pageNo = req.getParameter("pageNo") == null ? 
				1 : Integer.parseInt(req.getParameter("pageNo"));
		
		PagingVO pagingVO = new PagingVO();
		
		int totalCnt = memService.getTotalCnt();
		
		pagingVO.setTotalCount(totalCnt);
		pagingVO.setCurrentPageNo(pageNo);
		pagingVO.setCountPerPage(5);
		pagingVO.setPageSize(5);

		
		// 2. 회원정보 조회
		List<MemberVO> memList = memService.getAllMemberList(pagingVO);
		
		req.setAttribute("memList", memList);
		req.setAttribute("pagingVO", pagingVO);
		
		
		return VIEW_PAGE;
	}

}
