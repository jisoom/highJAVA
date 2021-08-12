package kr.or.ddit.basic;

public class T20_WaitNotifyTest {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}

//데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	//data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		if(data == null) {
			try {
				wait(); //data가 널이므로 대기실로 이동함
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null; //데이터를 꺼냈기 때문에 원래 데이터를 널로 해줌
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		notify();
		
		return returnData;
	}
	
	//data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) { 
			try {
				wait(); //값이 이미 있으므로 누가 가져가기 전까지는 세팅할 수 없으므로 대기실로 이동함
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		notify();
	}
}

//데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread"); //스레드에 있는 생성자를 호출함 super(); 디폴트 생성자 (쓰레드의 이름 명시적으로 부여해줌)
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			String data = "Data-" +i;
			System.out.println("dataBox.setData(" + data + ")호출");
			dataBox.setData(data);
		}
	}
}

//데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : " + data);
			
		}
	}
}