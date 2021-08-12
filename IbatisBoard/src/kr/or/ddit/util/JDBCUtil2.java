package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/*
 * db.properties 파일의 내용으로 DB정보를 설정하는 방법
 * 방법1) Properties 객체 이용하기
 */

public class JDBCUtil2 {
	
	static Properties prop; //Properties객체변수 선언
	static {
		
		prop = new Properties(); //객체 생성
		
		try {	
			FileInputStream fis = new FileInputStream("res/db.properties");
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
		}catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	//정보가 바뀌면 db.properties만 바꿔주면 정보가 변경됨
	/**
	 * 자원 반납
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
			
		}catch (SQLException ex) {
			System.out.println("DB연결 실패!!!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) try {rs.close();}catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();}catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
		if(conn != null) try {conn.close();}catch(SQLException ex) {}
	}
}
