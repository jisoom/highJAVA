package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;

public class BoardDaoImpl implements IBoardDao{

	
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
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard" , bv);
		
		if(obj == null) { //insert 성공하면 null 반환
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(SqlMapClient smc, int num) throws SQLException {
		
		boolean check = false;
		
		int count = (int) smc.queryForObject("board.checkBoard", num);
		
		if(count > 0) {
			check = true; //게시물이 있을 때 true
		}
	
		return check;
	}

	@Override
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = smc.update("board.updateBoard", bv);
		
		return cnt;
	}

	@Override
	public List<BoardVO> displayBoardAll(SqlMapClient smc) throws SQLException {
		
		List<BoardVO> boardList = smc.queryForList("board.displayBoardAll");
		
		return boardList;
	}

	@Override
	public BoardVO selectDetail(SqlMapClient smc, int num) throws SQLException {
		
		BoardVO bv = (BoardVO) smc.queryForObject("board.selectDetail", num);
		
		return bv;
	}

	@Override
	public int deleteBoard(SqlMapClient smc, int num) throws SQLException {
		
		int cnt = smc.delete("board.deleteBoard", num);
	
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.getSearchBoard", bv);
		return boardList;
	}

}
