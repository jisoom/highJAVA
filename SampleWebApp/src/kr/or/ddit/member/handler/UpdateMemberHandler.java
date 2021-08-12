package kr.or.ddit.member.handler;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

public class UpdateMemberHandler implements CommandHandler {
	
	private static final String VIEW_PAGE 
				= "/WEB-INF/views/member/updateForm.jsp";
		
	private static final Logger LOGGER = 
			Logger.getLogger(UpdateMemberHandler.class); 
	
	private IMemberService memService = MemberServiceImpl.getInstance();
	
	private IAtchFileService fileService =
					AtchFileServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return false; // forward
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return true; // redirect
		}
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			
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
			
			// 3. request객체에 회원정보 저장
			req.setAttribute("memVO", memVO);
			
			return VIEW_PAGE;
			
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			
			// 기존의 첨부파일 아이디 정보 가져오기
			long atchFileId = req.getParameter("atchFileId") == null ?
							-1 : Long.parseLong(req.getParameter("atchFileId"));
			
			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setAtchFileId(atchFileId);
			
			// 멀티파트 처리된 래퍼객체가 맞는지 확인..
			if(FileUploadRequestWrapper.hasWrapper(req)) {
				
				Map<String, Object> fileItemMap = 
						((FileUploadRequestWrapper)req).getFileItemMap();
				
				LOGGER.info("파일 아이템 사이즈 : " + fileItemMap.size());
				
				if(fileItemMap.size() > 0) { // 파일이 존재하면...
					atchFileVO = fileService.saveAtchFileList(fileItemMap);
				}
			}
			
			
			// 1. 요청파라미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemTel(memTel);
			mv.setMemAddr(memAddr);
			mv.setAtchFileId(atchFileVO.getAtchFileId());
			
			// 3. 회원정보 수정하기
			int cnt = memService.updateMember(mv);
			
			String msg = "";
			if( cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			String redirecURL = req.getContextPath() 
					+ "/member/list.do?msg=" + URLEncoder.encode(msg, "utf-8");
			
			return redirecURL;
		}
		
		return null;
	}

}
