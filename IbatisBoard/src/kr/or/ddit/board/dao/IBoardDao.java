package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param bv DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return 성공 : 1, 실패 : 0 반환
	 * @throws SQLException
	 */
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * 해당 번호의 게시글이 존재하는지 여부를 알아내는 메서드
	 * @param smc SqlMapClient 객체
	 * @param num 검색할 게시글 번호
	 * @return 해당 게시글이 있으면 true, 없으면 false
	 * @throws SQLException
	 */
	public boolean checkBoard(SqlMapClient smc, int num) throws SQLException;
	/**
	 * 하나의 게시글을 DB에 update하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param bv update할 게시글 정보가 들어있는 BoardVO 객체
	 * @return 성공 :1, 실패 : 0 반환
	 * @throws SQLException
	 */
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * JDBC_BOARD 테이블 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @param smc SqlMapClient 객체
	 * @return BoardVO 객체를 담고있는 List 객체
	 * @throws SQLException
	 */
	public List<BoardVO> displayBoardAll(SqlMapClient smc) throws SQLException;
	
	/**
	 * 게시글 상세조회 하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param num 상세조회할 게시글 번호
	 * @return num에 해당하는 상세조회 게시글을 담고있는 BoardVO 객체
	 * @throws SQLException
	 */
	public BoardVO selectDetail(SqlMapClient smc, int num) throws SQLException;
	
	/**
	 * 게시글 삭제하기 위한 메서드
	 * @param smc SqlMapClient 객체
	 * @param num 삭제할 게시글 번호
	 * @return 성공 : 1, 실패 : 0
	 * @throws SQLException
	 */
	public int deleteBoard(SqlMapClient smc, int num) throws SQLException;
	
	public List<BoardVO> getSearchBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
}
