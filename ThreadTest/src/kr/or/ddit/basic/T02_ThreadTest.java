package kr.or.ddit.basic;

public class T02_ThreadTest {
	
	public static void main(String[] args) {
		//멀티 스레드 프로그램
		
		//방법1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후
		//       이 인스턴스의 start()메서드를 호출한다.
		//자바는 다중상속이 안됨(다른 클래스를 상속받았을 때 Thread를 상속받지 못함)
		MyThread1 th1 = new MyThread1();
		th1.start(); //스레드가 종료되면 콘솔창에 <terminated>라고 뜸
		//start()로 시작하지 않고 run()으로 시작하게 되면  결국 main 혼자 run()을 실행하는 결과가 됨 (하나가 끝나고 다음꺼가 실행됨)
		
		
		//방법2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 
		//       이 인스턴스를 Thread 객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		//       이때 생성된 Thread 객체의 인스턴스의 start메서드 호출한다.
		//main, Thread1, Thread2 3개의 스레드 사용
		//다중 상속하기 위해서 필요함
		MyThread2 r1 = new MyThread2(); //러너블 객체
		Thread th2 = new Thread(r1);
		th2.start();

		
		//방법3 : 익명클래스를 이용하는 방법
		//       Runnable 인터페이스를 구현한 익명 클래스를 Thread 인스턴스를
		//       생성할 때 매개변수로 넘겨준다.
		//클래스를 만들지 않고 사용하는 이유 : 일시적으로 한번만 할거기 때문에 클래스를 만들지 않음
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<=200; i++) {
					System.out.print("@");
					try {
						//Thread.sleep(시간) ==> 주어진 시간동안 작업을 잠시 멈춘다.
						//시간은 밀리세컨드 단위를 사용한다.
						//즉, 1000은 1초를 의미한다.
						Thread.sleep(100); // MyThread1가 잠들었다가 0.1초 뒤에 깨어나서 for문 찍음
					}catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				
			}
		});
		th3.start();
			
	}
	
}

class MyThread1 extends Thread {
	@Override
	public void run() { //run()메서드에 스레드가 처리해야 될 작업을 넣어줌
		for(int i=0; i<=200; i++) {
			System.out.print("*");
			try {
				//Thread.sleep(시간) ==> 주어진 시간동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용한다.
				//즉, 1000은 1초를 의미한다.
				Thread.sleep(100); // MyThread1가 잠들었다가 0.1초 뒤에 깨어나서 for문 찍음
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i=0; i<=200; i++) {
			System.out.print("$");
			try {
				//Thread.sleep(시간) ==> 주어진 시간동안 작업을 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용한다.
				//즉, 1000은 1초를 의미한다.
				Thread.sleep(100); // MyThread1가 잠들었다가 0.1초 뒤에 깨어나서 for문 찍음
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
}