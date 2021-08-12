package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * Service 객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아 온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 보통 Dao의 메서드와 같은 구조를 갖는다.
 * @author PC-02
 *
 */
public interface IBoardService {

	/**
	 * BoardVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param bv DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return 성공 : 1, 실패 : 0 반환
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 해당 번호의 게시글이 존재하는지 여부를 알아내는 메서드
	 * @param num 검색할 게시글 번호
	 * @return 해당 게시글이 있으면 true, 없으면 false
	 */
	public boolean checkBoard(int num);
	
	/**
	 * 하나의 게시글을 DB에 update하는 메서드
	 * @param 수정할 게시글 정보
	 * @return 성공 :1, 실패 : 0 반환
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 전체 게시글 목록을 조회하기 위한 메서드
	 * @return 전체 게시글 목록
	 */
	public List<BoardVO> displayBoardAll();
	
	/**
	 * 게시글 상세조회 하는 메서드
	 * @param num 상세조회할 게시글 번호
	 * @return num에 해당하는 상세조회 게시글을 담고있는 BoardVO 객체
	 */
	public BoardVO selectDetail(int num);
	
	/**
	 * 게시글 삭제하기 위한 메서드
	 * @param num 삭제할 게시글 번호
	 * @return 성공 : 1, 실패 : 0
	 */
	public int deleteBoard(int num);
}
