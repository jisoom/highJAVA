package kr.or.ddit.basic;

public class T08_ThreadPriority {
	public static void main(String[] args) {
		
		//Thread 6개
		Thread th1 = new ThreadTest1();
		Thread th2 = new ThreadTest1();
		Thread th3 = new ThreadTest1();
		Thread th4 = new ThreadTest1();
		Thread th5 = new ThreadTest1();
		
		Thread th6 = new ThreadTest2();
		
		//우선순위는 start()메서드를 호출하기 전에 설정해야 한다. (기본값 : 5  Thread.NORM_PRIORITY)
		th1.setPriority(Thread.MIN_PRIORITY); //1
		th2.setPriority(1);
		th3.setPriority(1);
		th4.setPriority(1);
		th5.setPriority(1);
		th6.setPriority(10); //Thread.MAX_PRIORITY 와 같음
		
		System.out.println("th1의 우선 순위 : " + th1.getPriority());
		System.out.println("th2의 우선 순위 : " + th2.getPriority());
		System.out.println("th3의 우선 순위 : " + th3.getPriority());
		System.out.println("th4의 우선 순위 : " + th4.getPriority());
		System.out.println("th5의 우선 순위 : " + th5.getPriority());
		System.out.println("th6의 우선 순위 : " + th6.getPriority());
		
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		
		try { 
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			th5.join();
			th6.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
		
	}
}

//대문자를 출력하는 메서드
class ThreadTest1 extends Thread {
	@Override
	public void run() {
		for(char ch ='A'; ch <='Z'; ch++) {
			System.out.println(ch);
		}
		//아무것도 하지 않는 반복문(시간 때우기 용)
		for(long i =1; i<=1000000000L; i++) {		
			//Thread.sleep(100)과의 차이 : cpu가 for문은 일시정지하는 것을 작업하고 있는것이고
			//sleep은 cpu가 자고 있다가 깨서 작업하는 것
		}
	}
}


//소문자를 출력하는 메서드
class ThreadTest2 extends Thread {
	@Override
	public void run() {
		for(char ch ='a'; ch <='z'; ch++) {
			System.out.println(ch);
		}
		//아무것도 하지 않는 반복문(시간 때우기 용)
		for(long i =1; i<=1000000000L; i++) {
		}
	}
}