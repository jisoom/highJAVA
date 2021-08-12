package kr.or.ddit.member.handler;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.cmm.filter.FileUploadRequestWrapper;
import kr.or.ddit.cmm.handler.CommandHandler;
import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class InsertMemberHandler implements CommandHandler {
	
	private static final Logger LOGGER = 
			Logger.getLogger(InsertMemberHandler.class); 
	
	private static final String VIEW_PAGE 
						= "/WEB-INF/views/member/insertForm.jsp";
	private IMemberService memService = 
				MemberServiceImpl.getInstance();
	
	private IAtchFileService fileService =
			  AtchFileServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false; // forward
		}else if(req.getMethod().equals("POST")) {
			return true; // redirect
		}
		
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else if(req.getMethod().equals("POST")) {
			
			AtchFileVO atchFileVO = new AtchFileVO();
			
			// 멀티파트 처리된 래퍼객체가 맞는지 확인..
			if(FileUploadRequestWrapper.hasWrapper(req)) {
				
				Map<String, Object> fileItemMap = 
						((FileUploadRequestWrapper)req).getFileItemMap();
				
				LOGGER.info("파일 아이템 사이즈 : " + fileItemMap.size());
				
				if(fileItemMap.size() > 0) { // 파일이 존재하면...
					atchFileVO = fileService.saveAtchFileList(fileItemMap);
				}
			}
			
			// 1.요청 파라미터정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemTel(memTel);
			mv.setMemAddr(memAddr);
			mv.setAtchFileId(atchFileVO.getAtchFileId()); // 첨부파일 ID 저장
			
			// 3. 회원등록
			int cnt = memService.insertMember(mv);
			
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
		
		return null;
	}

}
