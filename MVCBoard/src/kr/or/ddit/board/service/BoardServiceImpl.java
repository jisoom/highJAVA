package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

public class BoardServiceImpl implements IBoardService{
	
	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public int insertBoard(BoardVO bv) {
		Connection conn = JDBCUtil3.getConnection();
		
		int cnt = 0;
		
		try {
			cnt = boardDao.insertBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(int num) {
		boolean chk = false;
		
		Connection conn = JDBCUtil.getConnection();
		
		try {
			chk = boardDao.checkBoard(conn, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return chk;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = boardDao.updateBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			boardList = boardDao.displayBoardAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return boardList;
	}

	@Override
	public BoardVO selectDetail(int num) {
		BoardVO bv = new BoardVO();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			bv = boardDao.selectDetail(conn, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return bv;
	}

	@Override
	public int deleteBoard(int num) {
		int cnt = 0;
		
		Connection conn = JDBCUtil.getConnection();
		
		try {
			cnt = boardDao.deleteBoard(conn, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return cnt;
	}

}
