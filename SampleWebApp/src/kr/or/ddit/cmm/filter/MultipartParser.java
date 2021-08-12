package kr.or.ddit.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

public class MultipartParser implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		try {
			
			// 요청래퍼 객체 생성
			FileUploadRequestWrapper reqWrapper =
				new FileUploadRequestWrapper((HttpServletRequest)req);
			
			// 래퍼객체 적용
			fc.doFilter(reqWrapper, resp);
			
		}catch(FileUploadException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
