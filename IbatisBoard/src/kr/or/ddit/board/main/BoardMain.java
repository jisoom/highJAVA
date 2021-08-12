package kr.or.ddit.board.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardMain {
	
	private Scanner scan = new Scanner(System.in);
	
	private IBoardService boardService;
	
	public BoardMain() {
		boardService = BoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();
	}
	
	//게시판 메뉴
	private void displayMenu() {
		System.out.println();
		System.out.println("*************************");
		System.out.println(" \t메뉴");
		System.out.println("*************************");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 상세 조회");
		System.out.println("3. 게시글 검색");
		System.out.println("0. 프로그램 종료");
		System.out.println("*************************");
	}
	
	//프로그램 시작 메서드
	private void start() {
		
		int choice;
		do {
			displayBoardAll(); //게시판 전체 목록 보여줌
			displayMenu(); //메뉴 보여줌
			System.out.print("메뉴 선택>>>");
			choice = scan.nextInt();
			switch(choice) {
			case 1 : //게시글 작성
				insertBoard();
				break;
			case 2: //게시글 상세 조회
				selectDetail();
				break;
			case 3: //게시글 검색
				searchBoard();
				break;
			case 0: //프로그램 종료
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			default :
				System.out.println("번호를 잘못 입력하였습니다.다시 입력하세요.");
				break;
			}
			
		}while(choice != 0);
	}
	
	private void searchBoard() {
		
		scan.nextLine(); //입력버퍼 비우기
		System.out.println();
		System.out.println("검색할 게시글 정보를 입력하세요.");

		System.out.print("제목>> ");
		String title = scan.nextLine().trim();
		
		System.out.print("작성자 >> ");
		String writer = scan.nextLine().trim();
		
		System.out.print("내용 >> ");
		String content = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setWriter(writer);
		bv.setContent(content);
		
		
		List<BoardVO> boardList = boardService.getSearchBoard(bv);
		if(boardList.size() == 0) {
			System.out.println("----------------------------------------------------------");
			System.out.println("검색한 게시글 정보가 존재하지 않습니다.");
		}else {
			//입력한 정보로 검색한 정보 출력
			System.out.println();
			System.out.println("----------------------------------------------------------");
			System.out.println(" 번호\t제  목\t작 성 자\t내  용");
			System.out.println("----------------------------------------------------------");
			for(BoardVO bv2 : boardList) {
				System.out.println(bv2.getBoardNo() + "\t" + bv2.getTitle() + "\t"
						+ bv2.getWriter() + "\t" + bv2.getContent());
			}
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("검색 작업 끝.");
	}

	//상세 조회하기
	private void selectDetail() {
		System.out.print("상세 조회할 게시글 번호를 입력하세요");
		int num = scan.nextInt(); //조회할 게시글 번호

		boolean flag = true;
		
		while(flag) {
			
			BoardVO bv = boardService.selectDetail(num);
			
		if(bv != null) {
			System.out.println("------------------------------------------------------");
			System.out.println("번호\t제목\t작성자\t작성일");
			System.out.println("------------------------------------------------------");
			System.out.println(bv.getBoardNo() +"\t" + bv.getTitle() + "\t" + bv.getWriter() + "\t" + bv.getDate());
			System.out.println("------------------------------------------------------");
			System.out.println("내용 : " + bv.getContent());
			System.out.println("------------------------------------------------------");
			
			System.out.println("1.수정 2.삭제 0.메인");
			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				updateBoard(num); //수정
				flag = true;
				break;
			case 2:
				deleteBoard(num); //삭제
				flag =false;
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("번호를 잘못 입력하였습니다. 다시 입력해 주세요.");
				break;
			}
		}else {
			System.out.println("해당 게시글이 존재하지 않습니다.");
			flag= false;
		}
		}

		
		
		
	}
	
	//삭제하기
	private void deleteBoard(int num) {
		
		int cnt = boardService.deleteBoard(num);
		
		if(cnt > 0) {
			System.out.println(num + "번 게시글 삭제 완료");
		}else {
			System.out.println(num + "번 게시글 삭제 실패");
		}
		
	}

	//수정하기
	private void updateBoard(int num) {
		boolean chk = false;
		
		do {
			chk = checkBoard(num); //게시글 있는지 체크하기
			if(chk == false) {
				System.out.println("해당 게시글이 존재하지 않습니다.");
				return;
			}
		}while(chk == false); // chk가 true(게시글이 존재할 때 나가야 됨)
		
		System.out.print("수정할 제목 입력>>>");
		String title = scan.next();
		scan.nextLine(); //입력버퍼 비우기
		System.out.print("수정할 내용 입력>>>");
		String content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setContent(content);
		bv.setBoardNo(num);
		
		int cnt = boardService.updateBoard(bv);
			
		if(cnt >0) {
			System.out.println(num + "번 게시글 수정 완료!");
		}else {
			System.out.println(num + "번 게시글 수정 실패...");
		}
		
	}
	
	//게시글 있는지 체크하기
	private boolean checkBoard(int num) {
		boolean check = false;
		
		check = boardService.checkBoard(num);
		
		return check;
	}

	//게시글 작성하기
	private void insertBoard() {
		System.out.print("작성자의 이름을 입력해주세요");
		String writer = scan.next(); //작성자
		System.out.print("제목을 입력해주세요");
		String title = scan.next(); //제목
		scan.nextLine(); //입력 버퍼 비우기
		System.out.print("내용을 입력해주세요");
		String content = scan.nextLine(); //내용
		
		BoardVO bv = new BoardVO();
		bv.setWriter(writer);
		bv.setTitle(title);
		bv.setContent(content);
		
		int cnt = boardService.insertBoard(bv); 
			
		if(cnt > 0) {
			System.out.println("게시글 등록 완료!");
		}else {
			System.out.println("게시글 등록 실패...");
		}
		
	}



	//게시판 전체 목록
	private void displayBoardAll() {
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.displayBoardAll();
		
		for(BoardVO bv : boardList) {
			System.out.println(bv.getBoardNo() +"\t" + bv.getTitle() + "\t" + bv.getWriter() + "\t" + bv.getDate());			
		}
		System.out.println("------------------------------------------------------");
			
	}
}