package kr.or.ddit.basic;

class Util2 {
	//Number와 Number를 extends한 타입만 사용가능
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		return Double.compare(v1, v2);
	}
}

/*
 * 제한된 타입 파라미터(Bounded Parameter) 예제
 * 제한된 타입 파라미터 : 하나의 특정한 타입을 어떤 범위에 있는 것만 허용하고 싶을 때 사용
 */
public class T04_GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.<Integer>compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.<Number>compare(3.14, 3);
		System.out.println(result2);
		
//		Util2.compare("C", "홍길동"); 문자열이라서 Number에서 제공해주지 않기 때문에 에러뜸
	}

	
}