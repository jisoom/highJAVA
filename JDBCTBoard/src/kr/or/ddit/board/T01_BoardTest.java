package kr.or.ddit.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

public class T01_BoardTest {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		T01_BoardTest board = new T01_BoardTest();
		board.start();
	}
	
	//시작
	private void start() {
		
		int choice;
		do {
			displayBoardAll(); //게시판 전체 목록 보여줌
			displayMenu(); //메뉴 보여줌
			System.out.print("메뉴 선택>>>");
			choice = scan.nextInt();
			switch(choice) {
			case 1 : //게시글 작성
				insertBoard();
				break;
			case 2: //게시글 상세 조회
				selectDetail();
				break;
			case 0: //프로그램 종료
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			default :
				System.out.println("번호를 잘못 입력하였습니다.다시 입력하세요.");
				break;
			}
			
		}while(choice != 0);
	}
	
	//상세 조회하기
	private void selectDetail() {
		System.out.print("상세 조회할 게시글 번호를 입력하세요");
		int num = scan.nextInt(); //조회할 게시글 번호
		
		boolean flag = true;
		
		while(flag) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
					+ "TO_CHAR(BOARD_DATE,'YYYY-MM-DD') AS BOARD_DATE, BOARD_CONTENT "
					+ "FROM JDBC_BOARD "
					+ "WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("------------------------------------------------------");
				System.out.println("번호\t제목\t작성자\t작성일");
				System.out.println("------------------------------------------------------");
				int boardNo = rs.getInt("BOARD_NO");
				String title = rs.getString("BOARD_TITLE");
				String writer = rs.getString("BOARD_WRITER");
				String date = rs.getString("BOARD_DATE");
				String content = rs.getString("BOARD_CONTENT");
				System.out.println(boardNo +"\t" + title + "\t" + writer + "\t" + date);
				System.out.println("------------------------------------------------------");
				System.out.println("내용 : " + content);
				
				System.out.println("1.수정 2.삭제 0.메인");
				int choice = scan.nextInt();
				switch (choice) {
				case 1:
					updateBoard(num); //수정
					flag = true;
					break;
				case 2:
					deleteBoard(num); //삭제
					flag = false;
					break;
				case 0:
					flag = false;
					break;
				default:
					System.out.println("번호를 잘못 입력하였습니다. 다시 입력해 주세요.");
					break;
				}
			}else {
				System.out.println("------------------------------------------------------");
				System.out.println("게시글이 없습니다.");
				flag = false;
			}
			System.out.println("------------------------------------------------------");
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("상세 조회 실패...");
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		

		}
		
		
	}
	
	//삭제하기
	private void deleteBoard(int num) {
		try {
			conn = JDBCUtil.getConnection();
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(num + "번 게시글 삭제 완료");
			}else {
				System.out.println(num + "번 게시글 삭제 실패");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	//수정하기
	private void updateBoard(int num) {
		boolean chk = false;
		do {
			chk = checkBoard(num); //게시글 있는지 체크하기
			if(chk == false) {
				System.out.println("해당 게시글이 존재하지 않습니다.");
				return;
			}
		}while(chk == false); // chk가 true(게시글이 존재할 때 나가야 됨)
		
		System.out.print("수정할 제목 입력>>>");
		String title = scan.next();
		scan.nextLine(); //입력버퍼 비우기
		System.out.print("수정할 내용 입력>>>");
		String content = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "UPDATE JDBC_BOARD "
					+ "SET BOARD_TITLE = ?, BOARD_CONTENT = ? "
					+ "WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println(num + "번 게시글 수정 완료!");
			}else {
				System.out.println(num + "번 게시글 수정 실패...");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}
	
	//게시글 있는지 체크하기
	private boolean checkBoard(int num) {
		boolean check = false;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT COUNT(*) AS CNT FROM JDBC_BOARD WHERE BOARD_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			
			if(rs.next()) {
				count = rs.getInt("CNT");
			}
			if(count >0) {
				check = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			check = false;
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		return check;
	}

	//게시글 작성하기
	private void insertBoard() {
		System.out.print("작성자의 이름을 입력해주세요");
		String writer = scan.next(); //작성자
		System.out.print("제목을 입력해주세요");
		String title = scan.next(); //제목
		scan.nextLine(); //입력 버퍼 비우기
		System.out.print("내용을 입력해주세요");
		String content = scan.nextLine(); //내용
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO JDBC_BOARD "
					+ "VALUES(BOARD_SEQ.NEXTVAL, ? ,? , TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 등록 완료!");
			}else {
				System.out.println("게시글 등록 실패...");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	//게시판 메뉴
	private void displayMenu() {
		System.out.println();
		System.out.println("*************************");
		System.out.println(" \t메뉴");
		System.out.println("*************************");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 상세 조회");
		System.out.println("0. 프로그램 종료");
		System.out.println("*************************");
	}

	//게시판 전체 목록
	private void displayBoardAll() {
		System.out.println();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
					+ "TO_CHAR(BOARD_DATE,'YYYY-MM-DD') AS BOARD_DATE "
					+ "FROM JDBC_BOARD "
					+ "ORDER BY BOARD_NO";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("------------------------------------------------------");
			System.out.println("번호\t제목\t작성자\t작성일");
			System.out.println("------------------------------------------------------");
			
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String title = rs.getString("BOARD_TITLE");
				String writer = rs.getString("BOARD_WRITER");
				String date = rs.getString("BOARD_DATE");
				System.out.println(boardNo +"\t" + title + "\t" + writer + "\t" + date);
			}
			System.out.println("------------------------------------------------------");
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("게시글 목록 가져오기 실패...");
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
}