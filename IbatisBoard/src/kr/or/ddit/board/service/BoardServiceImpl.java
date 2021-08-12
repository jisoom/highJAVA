package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class BoardServiceImpl implements IBoardService{
	
	private IBoardDao boardDao;
	
	private static IBoardService boardService;
	
	private SqlMapClient smc;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;
		
		try {
			cnt = boardDao.insertBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(int num) {
		boolean chk = false;
		
		try {
			chk = boardDao.checkBoard(smc, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = boardDao.updateBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> displayBoardAll() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			boardList = boardDao.displayBoardAll(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public BoardVO selectDetail(int num) {
		BoardVO bv = new BoardVO();
		
		try {
			bv = boardDao.selectDetail(smc, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bv;
	}

	@Override
	public int deleteBoard(int num) {
		int cnt = 0;
		
		try {
			cnt = boardDao.deleteBoard(smc, num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = boardDao.getSearchBoard(smc, bv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

}
