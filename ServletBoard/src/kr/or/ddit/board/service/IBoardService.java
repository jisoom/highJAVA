package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	/**
	 * 게시글 등록
	 * @param bv
	 * @return
	 */
	public int insert(BoardVO bv); 
	
	/**
	 * 게시글 수정
	 * @param bv
	 * @return
	 */
	public int update(BoardVO bv);
	
	/**
	 * 게시글 삭제
	 * @param boardNo
	 * @return
	 */
	public int delete(int boardNo);
	
	/**
	 * 게시글 전체 조회
	 * @return
	 */
	public List<BoardVO> boardList();
	
	/**
	 * 게시글 상세 조회
	 * @param boardNo
	 * @return
	 */
	public BoardVO boardSelect(int boardNo);
	
	/**
	 * 게시글 검색
	 * @param bv
	 * @return
	 */
	public List<BoardVO> searchBoard(BoardVO bv);
}
