package kr.or.ddit.board.main;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class boardMain {

	private Scanner scan = new Scanner(System.in); 
	SimpleDateFormat format = new SimpleDateFormat("YY-MM-dd");
	
	private IBoardService boardService;
	
	public boardMain() {
		boardService = BoardServiceImpl.getInstance();
	}

	public void boardListAll(){

		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("번  호\t제  목\t작성자\t작성일 ");
		System.out.println("-------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.boardList();
		
		for(BoardVO bv : boardList) {
			
			System.out.println(bv.getBoardNo() + "\t"+ bv.getBoardTitle()+ "\t" + bv.getBoardWriter()+ "\t" + format.format(bv.getBoardDate()));

		}
		System.out.println("-------------------------------------------------------");
		System.out.println("1.상세조회\t2.등  록 \t3.검 색 \t 4.종  료");
		System.out.print("입력번호 >>");
	}
	
	public void start(){
		int input;
		do{
			boardListAll();
			input = scan.nextInt();
			switch(input){
				case 1 : 
					boardSelect();
					break;
				case 2 :  
					insert();
					break;
				case 3 :  
					search();
					break;
				case 4 : 
					System.out.println("종료되었습니다");
					System.exit(0);
					break;
				default :
					System.out.println("번호를 다시 입력하세요.");
			}
		}while(input!=4);
	}
	private void search() {
		scan.nextLine();
		System.out.println();
		System.out.println("검색할 조건을 입력하세요.");
		
		System.out.print("작성자>>");  
		String writer= scan.nextLine();
		
		
		BoardVO bv = new BoardVO();	
		bv.setBoardWriter(writer);
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("번  호\t제  목\t작성자\t작성일 ");
		System.out.println("-------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.searchBoard(bv);
		
		for(BoardVO bv2: boardList) {
			System.out.println(bv2.getBoardNo() + "\t"+ bv2.getBoardTitle()+ "\t" + bv2.getBoardWriter()+ "\t" + format.format(bv2.getBoardDate()));
		}
		start();
	
	}

	private void boardSelect() {
		
		System.out.print("상세조회할 번호를 입력하세요 >>");
		int boardNo = scan.nextInt();
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println("번  호\t제  목\t내  용\t작성자\t작성일 ");
		System.out.println("-------------------------------------------------------");
		
		BoardVO bv2 = boardService.boardSelect(boardNo);
		
		System.out.println("번호 : " + bv2.getBoardNo());
		System.out.println("제목 : " + bv2.getBoardTitle());
		System.out.println("내용 : " + bv2.getBoardContent());
		System.out.println("작성자 : " + bv2.getBoardWriter());
		System.out.println("날짜 : " + format.format(bv2.getBoardDate()));
		System.out.println("-------------------------------------------------------");	
		System.out.println("1. 수정\t2. 삭제\t 3. 목록");
			
		int input;
		do{
			input = scan.nextInt();
			switch(input) {
				case 1 :  
					update(boardNo);
					break;
				case 2 :  
					delete(boardNo);
					break;
				case 3 : 
					start();
					break;
				default :
					System.out.println("번호를 다시 입력해주세요.");
			}
		}while(input != 3);
	}

	private void delete(int boardNo) {
	
		int cnt = boardService.delete(boardNo);
		
		if(cnt > 0) {
			System.out.println("게시글 삭제 성공");
		}else {
			System.out.println("게시글 삭제 실패");
		}
		start();
	}

	private void update(int boardNo) {
		
		System.out.print("제  목 >>");
		String title = scan.next();

		scan.nextLine();
		
		System.out.print("내  용 >>");
		String content = scan.next();
		
		scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(title);
		bv.setBoardContent(content);
		bv.setBoardNo(boardNo);
		
		int cnt = boardService.update(bv);
			
		if(cnt > 0) {
			System.out.println("게시글 수정 완료");
		}else {
			System.out.println("게시글 수정 실패");
		}
		start();
	}

	private void insert() {
		
		System.out.print("제목 >> ");
		String title = scan.next(); 
		
		scan.nextLine();  
		
		System.out.print("내용 >> ");  
		String content = scan.nextLine();
		
		System.out.print("작성자 >> ");
		String writer = scan.next();

		BoardVO bv = new BoardVO();
		bv.setBoardTitle(title);
		bv.setBoardContent(content);
		bv.setBoardWriter(writer);
		
		int cnt = boardService.insert(bv);
			
		if(cnt > 0) { 
			System.out.println("게시글 등록 성공");
		}else {
			System.out.println("게시글 등록 실패");
		}
	}
	
	public static void main(String[] args) {
		boardMain board = new boardMain();
		board.start();
	}
}
