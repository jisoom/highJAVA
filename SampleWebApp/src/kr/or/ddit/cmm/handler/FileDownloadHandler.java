package kr.or.ddit.cmm.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.AtchFileVO;

public class FileDownloadHandler implements CommandHandler {
	
	private IAtchFileService fileService = 
			AtchFileServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		long fileId = req.getParameter("fileId") != null ?
			Long.parseLong(req.getParameter("fileId") ) : -1;
		int fileSn = req.getParameter("fileSn") != null ?
			Integer.parseInt(req.getParameter("fileSn")) : 1;
			
		// 파일 정보 조회
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setAtchFileId(fileId);
		atchFileVO.setFileSn(fileSn);
		atchFileVO = fileService.getAtchFileDetail(atchFileVO);
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기.
		resp.setContentType("application/octet-stream");
		String fileName = URLEncoder
				.encode(atchFileVO.getOrignlFileNm(), "UTF-8"); //한글로 들어가면 깨질수 있어서 인코딩 처리해줌
		// +문자 공백으로 바꿔주기
		fileName.replaceAll("\\+", "%20");
		resp.setHeader("Content-Disposition", 
						"attachment; filename=\"" + fileName + "\""); //attachment를 붙여야 다운로드할 수 있음
		
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(atchFileVO.getFileStreCours()));
		BufferedOutputStream bos = 
				new BufferedOutputStream(resp.getOutputStream());
		
		int c = 0;
		while((c = bis.read()) != -1) {
			bos.write(c);
		}
		
		bis.close();
		bos.close();
		
		return null; //뷰페이지 있으면 뷰페이지 리턴해줌
	}

}
