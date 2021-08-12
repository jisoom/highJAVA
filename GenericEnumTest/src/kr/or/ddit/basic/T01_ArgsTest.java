package kr.or.ddit.basic;

public class T01_ArgsTest {
	/*
	 * 가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다를 때 사용한다. (어떨 때는 두개였다가 어떨 때는 세개일 수도 있는 때 사용함)
	 * -가변형 인수는 메서드 안에서는 배열로 처리된다.
	 * -가변형 인수는 한가지 자료형만 사용할 수 있다.
	 */
	
	//파라미터와 아규먼트 차이점
	//파라미터 : 함수 혹은 메서드 정의에서 나열되는 변수 명
	//아규먼트 : 함수 혹은 메서드를 호출할 때, 전달 혹은 입력되는 실제 값

	
	//배열을 이용한 메서드
	//매개변수로 받은 정수들의 합계를 구하는 메서드
	//(이 정수들의개수는 상황에 따라 다르다.)
	
	public int sumArr(int[]data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수를 이용한 메서드
	public int sumArg(int...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}		
	
	//가변형 인수와 일반적인 인수를 같이 사용할 경우에는 
	//가변형 인수를 제일 뒤쪽에 배치해야 한다.
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "씨 점수 : " + sum;
	}
	
	public static void main(String[] args) {
		T01_ArgsTest at = new T01_ArgsTest();
		
		int[] nums = {100, 200, 300};
		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] {1, 2, 3, 4, 5}));
		System.out.println();
		
		System.out.println(at.sumArg(100, 200, 300));
		System.out.println(at.sumArg(1, 2, 3, 4, 5));
		System.out.println();
		
		System.out.println(at.sumArg2("홍길동", 1, 2, 3, 4, 5, 6, 7, 8));
	}
	

	
	
	
	
	
}