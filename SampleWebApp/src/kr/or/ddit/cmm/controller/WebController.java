package kr.or.ddit.cmm.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import kr.or.ddit.cmm.handler.CommandHandler;
import kr.or.ddit.cmm.handler.NullHandler;

/**
 * 사용자 요청을 받아서 처리하는 대표 컨트롤러 구현하기
 * @author SEM
 *
 */
public class WebController extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(WebController.class);
	
	
	// 매핑정보 저장(key: 요청URL, value: 핸들러 객체)
	private Map<String, CommandHandler> cmmHandlerMap = 
							new HashMap<String, CommandHandler>();
	/**
	 * 설정파일을 참고하여 요청URL별 대응되는 힌들러 객체를 생성하여 맵에 등록한다.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// 설정파일 경로 정보 가져오기
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties prop = new Properties();
		FileReader fr;
		try {
			
			fr = new FileReader(config.getServletContext()
							.getRealPath(configFilePath));
			
			prop.load(fr);
			
			fr.close();
			
		}catch(IOException ex){
			ex.printStackTrace();
			throw new ServletException(ex);
		}
		
		for(Object key : prop.keySet()) {
			
			String reqURL = (String) key;
			
			try {
				Class<?> klass = Class.forName(prop.getProperty(reqURL));
				
				CommandHandler handler = (CommandHandler)klass.newInstance();
				
				cmmHandlerMap.put(reqURL, handler); // 핸들러 객체 등록
				
			}catch(Exception ex) {
				ex.printStackTrace();
				throw new ServletException();
			}
		}
		
		// 등록된 전체 핸들러 정보 조회하기
		Set<Map.Entry<String, CommandHandler>> entrySet = 
				cmmHandlerMap.entrySet();
		for(Map.Entry<String, CommandHandler> entry : entrySet) {
			logger.info(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	
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
	private void process(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		
		String reqURI = req.getRequestURI(); //사용자가 요청한 URI정보 가져옴
		
		
		if(reqURI.indexOf(req.getContextPath()) == 0) { //컨텍스트경로 자름
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		
		CommandHandler handler = cmmHandlerMap.get(reqURI);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		if(logger.isInfoEnabled()) { //logger가 info로 설정되어 있으면
			logger.info("요청URL : " + reqURI);
			logger.info("핸들러객체 : " + handler); 
		}
		
		String viewPage = ""; // 뷰화면 정보 (URL)
		try {
			viewPage = handler.process(req, resp); // 핸들러 처리
		}catch(Throwable e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		logger.info("viewPage 정보 : " + viewPage); 
		
		// VIEW 화면 처리하기
		if(viewPage != null) { // 뷰페이지가 존재하면...
			if(handler.isRedirect(req)) { // 리다이렉트 처리가 필요하다면...
				resp.sendRedirect(viewPage);
			}else { //포워드 처리 해야한다면...
				RequestDispatcher dispatcher = 
						req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
	}
}
