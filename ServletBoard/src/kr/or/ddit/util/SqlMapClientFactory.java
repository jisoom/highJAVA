package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {

	private static SqlMapClient smc;
	
	public static SqlMapClient getInstance() {
		if(smc == null) {
			
			try {
				//xml 문서 읽기
				Charset charset = Charset.forName("UTF-8"); //인코딩 설정
				Resources.setCharset(charset); // 한글 깨지는 거 방지하기 위함
				
				Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
				
				smc = SqlMapClientBuilder.buildSqlMapClient(rd);
				
				rd.close();
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		return smc;
	}
}
