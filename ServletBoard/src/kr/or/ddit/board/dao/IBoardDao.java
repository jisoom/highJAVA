package kr.or.ddit.board.dao;


import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	/**
	 * 게시글 등록
	 * @param smc
	 * @param bv
	 * @return
	 * @throws SQLException
	 */
	public int insert(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * 게시글 수정
	 * @param smc
	 * @param bv
	 * @return
	 * @throws SQLException
	 */
	public int update(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * 게시글 삭제
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public int delete(SqlMapClient smc, int boardNo) throws SQLException;
	
	/**
	 * 게시글 전체 조회
	 * @param smc
	 * @return
	 * @throws SQLException
	 */
	public List<BoardVO> boardListAll(SqlMapClient smc) throws SQLException;
	
	/**
	 * 게시글 상세조회
	 * @param smc
	 * @param boardNo
	 * @return
	 * @throws SQLException
	 */
	public BoardVO boardSelect(SqlMapClient smc, int boardNo) throws SQLException; 
	
	/**
	 * 게시글 검색
	 * @param smc
	 * @param bv
	 * @return
	 * @throws SQLException
	 */
	public List<BoardVO> searchBoard(SqlMapClient smc, BoardVO bv) throws SQLException; 
}
