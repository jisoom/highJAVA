package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class T02_FileTest {
	public static void main(String[] args) {
		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");
		
		if(f1.exists()) {//존재하면 true, 존재하지 않으면 false
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");
			try {
				if(f1.createNewFile()) { //비어있는 파일 만들어줌 
					System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(f2.exists()) {
			System.out.println(f2.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("-------------------------------------------------------");
		
		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles(); //배열 타입의 파일 객체를 던져줌
		
		for(int i=0; i<files.length; i++) {
			System.out.print(files[i].getName() + "=>");
			if(files[i].isFile()) {
				System.out.println("파일");
			}else if(files[i].isDirectory()) {
				System.out.println("디렉토리");
			}
		}
		System.out.println("=========================================================");
		String[] strFiles = f3.list(); //파일 이름만 String 배열로 던져줌
		for(int i= 0; i <strFiles.length; i++) {
			System.out.println(strFiles[i]);
		}
		System.out.println("--------------------------------------------------------");
		System.out.println();
		//---------------------------------------------------------------------
		//출력할 디렉토리 정보를 갖는 File객체 생성
		File f4 = new File("D:\\D_Other");
		displayFileList(f4); //메서드 호출 (폴더에 있는 파일 목록 쭉 보여주게함)
	}

	/*
	 * 지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주는 메서드
	 * dir 조회할 폴더(디렉토리)
	 */
	private static void displayFileList(File dir) {
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리의 내용");
		
		//디렉토리 안의 모든 파일 목록을 가져온다.
		File[] files = dir.listFiles();
		
		//하위 디렉토리 정보를 저장할 List 객체생성(File배열의 인덱스 저장)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		//날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm"); //a : 오전,오후
		
		for(int i=0; i<files.length; i++) {
			String attr = ""; //파일의 속성(읽기, 쓰기, 히든, 디렉토리구분)
			
			String size = ""; //파일 용량
			if(files[i].isDirectory()) { //디렉토리인 경우에만 subDirList에 저장
				attr = "<DIR>";
				subDirList.add(i); //인덱스 정보를 List에 추가
			}else { //디렉토리가 아닌 경우에
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : " "; //읽을 수 있는 상태인지
				attr += files[i].canWrite() ? "W" : " "; //쓸 수 있는 상태인지 (R이 있으면 옆에 W 누적함)
				attr += files[i].isHidden() ? "H" : " "; //숨긴파일인지 (누적)
			}
			System.out.printf("%s %-5s %12s %s\n", //사이즈만큼 5글자(-5:좌측정렬) 12글자 사이즈만큼 
					sdf.format(new Date(files[i].lastModified())), //마지막 수정일자 %s
					attr, size, files[i].getName()); //attr:%5s size:%12s 파일이름:%s
		}
		int dirCount = subDirList.size(); //하위 폴더 개수
		int fileCount = files.length - dirCount; //파일 수
		
		System.out.println(fileCount + "개의 파일, " + dirCount + "개의 디렉토리");
		System.out.println();
		
		for(int i=0; i<subDirList.size(); i++) {
			//하위 폴더의 내용들도 출력하기 위해
			//현재의 메서드를 재귀호출하여 처리한다. (내가 나를 호출하고호출함)
			displayFileList(files[subDirList.get(i)]); //이거로 인해 디렉토리안의 디렉토리를 다 볼 수 있는 것임
		}
	}
}