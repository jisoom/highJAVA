package kr.or.ddit.basic;

import java.util.Arrays;

public class RacingHorse {
	
	public static int currentRank = 0;
	
	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
				new Horse("1번말"),
				new Horse("2번말"),
				new Horse("3번말"),
				new Horse("4번말"),
				new Horse("5번말"),
				new Horse("6번말"),
				new Horse("7번말"),
				new Horse("8번말"),
				new Horse("9번말"),
				new Horse("10번말")
		};
		
		RacingState state = new RacingState(horses);
		
		for(Horse h : horses) {
			h.start(); //말 10마리 시작
		}
		state.start(); //상태 표시 시작
		
		for(Horse h : horses) {
			try {
				h.join(); //말 종료될 때 까지 기다림
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			state.join(); //상태 표시 종료 될 때까지 기다림
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("경기 끝 ....");
		System.out.println();
		//경기 종료 후 등수순으로 정렬
		Arrays.sort(horses);
		
		System.out.println("경기 결과");
		for(Horse h : horses) {
			System.out.println(h);
		}
		
	}
}

//말
class Horse extends Thread implements Comparable<Horse> {
	private String horseName; //말의 이름
	private int rank; //등수
	private int location; //위치
	
	//생성자
	public Horse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return rank + "등은 " + horseName + "입니다.";
	}
	
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			try {
				Thread.sleep((int)(Math.random()*400)); //랜덤으로 멈춰줌
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			location = i;
		}//for문 종료
		rank = ++RacingHorse.currentRank;
	}

	//랭크 오름차순 정렬
	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.rank, h.getRank());
	}
	
}

//레이싱 상태 표시
class RacingState extends Thread {
	private Horse[] horses;

	//생성자
	public RacingState(Horse[] horses) {
		this.horses = horses;
	}
	@Override
	public void run() {
		while(true) {
			if(RacingHorse.currentRank == horses.length) { //경기가 종료되면...
				break;
			}
			for(int i=1; i<=3; i++) { //3줄씩 띄어줌
				System.out.println("...");
			}
			for(int i=0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j=1; j<=50; j++) {
					if(horses[i].getLocation() == j) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(200); //0.2초씩 멈춰줌
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}