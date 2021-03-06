package kr.or.ddit.board.vo;

public class BoardVO {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Object boardDate;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Object getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Object boardDate) {
		this.boardDate = boardDate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardDate=" + boardDate + "]";
	}
	
	
}
