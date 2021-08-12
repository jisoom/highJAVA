package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelService implements Serializable{
	private Scanner sc;
	private Map<String, Hotel> hotelMap;
	
	
	public HotelService() {
		sc = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new HotelService().open();
	}

	public void open() {
		System.out.println("=====================================================");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("=====================================================");
		
		fileInput();
		
		while(true) {
			System.out.println();
			System.out.println("*****************************************************");
			System.out.println("어떤 업무를 하시겠습니까 ? ");
			System.out.println("1.체크인 2.체크아웃 3.객실조회 4.업무종료");
			System.out.println("*****************************************************");
			System.out.print("업무 선택 >>>");
			int input = sc.nextInt();
			switch(input) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomCon();
				break;
			case 4:
				fileOutput();
				System.out.println("*****************************************************");
				System.out.println("업무가 종료되었습니다.");
				System.out.println("*****************************************************");
				System.exit(0);
				
			}
					
		}
		
	}

	private void fileOutput() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			
			fos = new FileOutputStream("d:/D_Other/hotel.bin");
			oos = new ObjectOutputStream(new BufferedOutputStream(fos));
			oos.writeObject(hotelMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
				System.out.println("파일 저장이 완료되었습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void fileInput() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/hotel.bin");
			ois = new ObjectInputStream(new BufferedInputStream(fis));
			Object data;
			while((data= ois.readObject()) != null) {
				hotelMap = (HashMap)data;
				Set<String> keySet = hotelMap.keySet();
				Iterator<String> it = keySet.iterator();
				System.out.println("---------------------객실 상태--------------------------");
				while(it.hasNext()) {
					String roomNo = it.next();
					Hotel h = hotelMap.get(roomNo);
					System.out.println(h);
				}
				System.out.println("-----------------------------------------------------");
			}
			ois.close();
		} catch (Exception e) {
/*			e.printStackTrace();*/
		}		
	}

	public void roomCon() {
		System.out.println("---------------------객실 상태 조회----------------------");
		Set<String>keySet = hotelMap.keySet();
		if(keySet.size() == 0) {
			System.out.println("이용중인 방이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String roomNo = it.next();
				Hotel h = hotelMap.get(roomNo);
				System.out.println(h);
			}
		}
		System.out.println("-----------------------------------------------------");
	}

	public void checkOut() {
		System.out.println();
		System.out.println("어떤 방을 체크아웃하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNo = sc.next();
		if(hotelMap.get(roomNo) == null) {
			System.out.println("체크인 한 사람이 없습니다.");
			return;
		}else {
			hotelMap.remove(roomNo);
			System.out.println("체크아웃 되었습니다.");
		}
		
	}

	public void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인하시겠습니까?");
		System.out.print("방번호 입력>>>");
		 String roomNo = sc.next();
		 sc.nextLine();
		 System.out.println("누구를 체크인 하시겠습니까?");
		 System.out.print("이름 입력>>>");
		 String name = sc.next();
		 
		 if(hotelMap.get(roomNo) != null) {
			 System.out.println("이미 다른 손님이 사용중입니다.");
			 return;
		 }
		 hotelMap.put(roomNo, new Hotel(roomNo, name));
		 System.out.println("체크인 되었습니다.");			 
	}		
}

class Hotel implements Serializable{
	private String roomNo;
	private String name;
	
	public Hotel(String roomNo, String name) {
		super();
		this.roomNo = roomNo;
		this.name = name;
	}
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Hotel [방 번호 : " + roomNo +  "\t" + "투숙객 이름 : " + name + "]";
	}
}