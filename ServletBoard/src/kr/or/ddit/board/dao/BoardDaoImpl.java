package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public int insert(SqlMapClient smc, BoardVO bv) throws SQLException {
	
			int cnt = 0;
			
			Object obj = smc.insert("board.insertBoard", bv);
			
			if(obj == null) {
				cnt = 1;
			}
		return cnt;
	}

	@Override
	public int update(SqlMapClient smc, BoardVO bv) throws SQLException {

			int cnt = smc.update("board.updateBoard", bv);
			
		return cnt;
	}

	@Override
	public int delete(SqlMapClient smc, int boardNo) throws SQLException {

			int cnt = smc.delete("board.deleteBoard", boardNo);
			

		return cnt;
	}

	@Override
	public List<BoardVO> boardListAll(SqlMapClient smc) throws SQLException {

		List<BoardVO> boardList = smc.queryForList("board.boardList");
		
		return boardList;
	}

	@Override 
	public BoardVO boardSelect(SqlMapClient smc, int boardNo) throws SQLException {

			BoardVO board = (BoardVO)smc.queryForObject("board.boardSelect", boardNo);
				
			
		return board;
	}

	@Override 
	public List<BoardVO> searchBoard(SqlMapClient smc, BoardVO bv) throws SQLException {

			List<BoardVO> boardList = smc.queryForList("board.searchBoard", bv);
		
		return boardList;
	}

	
	
}
