package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
/*
 * wait()메서드 => 동기화 영역에서 락을 풀고Wait-Set영역(공유 객체별로 존재함)으로 이동시킨다.
 * notify() 또는 notifyAll()메서드 => Wait-Set영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
 * (notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 깨운다.)
 * notify는 랜덤으로 하나를 깨우기 때문에 깨우지 않아도 될 애를 깨울 수 있음 ==> notifyAll로 전체를 다깨움
 * 
 * =>wait()과 notify(), notifyAll()메서드는 동기화 영역에서만 실행 할 수 있고, Object클래스에서 제공하는 메서드이다.
 */
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

//공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업중...");
		
		notify(); //대기실에 있는 놈을 깨움 (대기실은 객체별로 있음)
		
		try {
			wait(); //나는 대기실로 이동하고 락을 해제함
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업중...");
		
		notify();
		
		try {
			wait();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

//WorkObject의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료."); 
		//ThreadA가 다 돌고 종료 되서 ThreadB는 깨워줄 사람이 없어서 계속 대기상태에 있음
	}
	
}

//WorkObject의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료.");
	}
	
}