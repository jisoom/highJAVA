package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	public static void main(String[] args) {
		
		//공유 객체에서 나타나는 문제점 
		//임계영역(문제가 발생할 수 있는 영역) - critical section
		//스레드가 많아질 수록 서로 들어가서 작업을 하면 순서가 차례대로가 아닌 뒤죽박죽이 됨 ==> 동기화 작업 꼭 필요!!
		
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번 스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
	}
}


//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 하는 방법1 : 메서드 자체에 동기화 설정하기 (synchronized => add()메서드는 동기화 처리 됨)
//	synchronized public void add() {
	public void add() {
		//동기화 하는 방법2 : 동기화 블럭으로 설정하기 (동기화 범위를 조정할 수 있음)
		synchronized (this) {
			
		for(int i=0; i < 1000000000; i++) {} //동기화 처리 전까지 시간벌기용
		
		int n = sum;
		n += 10; //10증가
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + "합계: " + sum);
		
		}
	}
	
	public int getSum() {
		return sum;
	}
}

//작업을 수행하는 스레드
class WorkerThread extends Thread {
	ShareObject sObj;
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	@Override
	public void run() {
		for(int i=1; i <=10; i++) {
			sObj.add(); //add 10번 호출
		}
	}
	
}