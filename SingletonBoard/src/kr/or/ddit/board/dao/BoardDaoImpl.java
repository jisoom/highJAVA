package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;

public class BoardDaoImpl implements IBoardDao{
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IBoardDao boardDao;
	
	//싱글톤 생성자를 private으로 만듦
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {
		
		String sql = "INSERT INTO JDBC_BOARD "
				+ "VALUES(BOARD_SEQ.NEXTVAL, ? ,? , TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getTitle());
		pstmt.setString(2, bv.getWriter());
		pstmt.setString(3, bv.getContent());
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public boolean checkBoard(Connection conn, int num) throws SQLException {
		boolean check = false;
		
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
	
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
	
		return check;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		
		String sql = "UPDATE JDBC_BOARD "
				+ "SET BOARD_TITLE = ?, BOARD_CONTENT = ? "
				+ "WHERE BOARD_NO = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getTitle());
		pstmt.setString(2, bv.getContent());
		pstmt.setInt(3, bv.getBoardNo());
		
		int cnt = pstmt.executeUpdate();
	
		JDBCUtil.disConnect(null, stmt, pstmt, rs);

		return cnt;
	}

	@Override
	public List<BoardVO> displayBoardAll(Connection conn) throws SQLException {
		List<BoardVO> boardList = new ArrayList<>();
		
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
				+ "TO_CHAR(BOARD_DATE,'YYYY-MM-DD') AS BOARD_DATE "
				+ "FROM JDBC_BOARD";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			BoardVO bv = new BoardVO();
			
			bv.setBoardNo(rs.getInt("BOARD_NO"));
			bv.setTitle(rs.getString("BOARD_TITLE"));
			bv.setWriter(rs.getString("BOARD_WRITER"));
			bv.setDate(rs.getString("BOARD_DATE"));
			
			boardList.add(bv);
		}
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
		
		return boardList;
	}

	@Override
	public BoardVO selectDetail(Connection conn, int num) throws SQLException {
		
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
				+ "TO_CHAR(BOARD_DATE,'YYYY-MM-DD') AS BOARD_DATE, BOARD_CONTENT "
				+ "FROM JDBC_BOARD "
				+ "WHERE BOARD_NO = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		rs = pstmt.executeQuery();
		
		BoardVO bv = null;
		
		if(rs.next()) {
			bv = new BoardVO();
			bv.setBoardNo(rs.getInt("BOARD_NO"));
			bv.setTitle(rs.getString("BOARD_TITLE"));
			bv.setWriter(rs.getString("BOARD_WRITER"));
			bv.setContent(rs.getString("BOARD_CONTENT"));
			bv.setDate(rs.getString("BOARD_DATE"));
		}
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
		
		return bv;
	}

	@Override
	public int deleteBoard(Connection conn, int num) throws SQLException {
		
		String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		int cnt = pstmt.executeUpdate();
		
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
	
		return cnt;
	}

}
