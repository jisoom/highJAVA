package kr.or.ddit.board.service;

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
	
	private static IBoardService boardService; // 자기 자신의 타입을 담을 변수 선언
	
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
	public int insert(BoardVO bv) {

		int cnt = 0;
		
		try {
			
			cnt = boardDao.insert(smc, bv);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int update(BoardVO bv) {
		int cnt = 0;

		try {
			
			cnt = boardDao.update(smc, bv);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int delete(int boardNo) {
		
		int cnt = 0;

	try {	
			cnt = boardDao.delete(smc, boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> boardList() {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
				boardList = boardDao.boardListAll(smc);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return boardList;
	}

	@Override
	public BoardVO boardSelect(int boardNo) {
		
		BoardVO board = new BoardVO();
	
		try {
			
			board = boardDao.boardSelect(smc, boardNo);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return board;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();

		try {
	
			boardList = boardDao.searchBoard(smc, bv);
	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return boardList;
	}

}
