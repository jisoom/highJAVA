package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.handler.CommandHandler;
import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class ViewMemberHandler implements CommandHandler {
	
	private static final String VIEW_PAGE = "/WEB-INF/views/member/select.jsp";
	
	private IMemberService memService = MemberServiceImpl.getInstance();
	
	private IAtchFileService fileService = AtchFileServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false; //foward이기 때문에
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			
		String memId = req.getParameter("memId");
		
		// 1. 회원정보 조회
		MemberVO memVO = memService.getMember(memId);
		
		// 2. 첨부파일 정보 조회
		if(memVO.getAtchFileId() > 0) { // 첨부파일이 존재하면...존재하지 않으면 -1이 기본값이기때문에
			// 2-1. 첨부파일 정보 조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(memVO.getAtchFileId());
			
			List<AtchFileVO> atchFileList = fileService.getAtchFileList(fileVO);
			
			req.setAttribute("atchFileList", atchFileList);
		}
		
		
		// 3. 결과 정보 담기
		req.setAttribute("memVO", memVO);
		
		return VIEW_PAGE;
	}

}
