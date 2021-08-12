package kr.or.ddit.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Filter를 이용한 한글 인코딩 처리 예제
 * @author PC-19
 *
 */

public class CustomCharterEncoding implements Filter{

	private String encoding; //인코딩 정보
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("인코딩 설정 정보 : " + this.encoding);
		req.setCharacterEncoding(this.encoding);
		resp.setCharacterEncoding(this.encoding);
		
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		if(config.getInitParameter("encoding") == null) {
			//기본 인코딩을 utf-8로 설정 (기본인코딩 : ISO-8859-1)
			this.encoding = "utf-8";
		}else {
			this.encoding = config.getInitParameter("encoding");
		}
	}
}
