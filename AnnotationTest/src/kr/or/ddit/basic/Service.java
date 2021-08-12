package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation // @PrintAnnotation()
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="%") //value인 경우 ==> (%) 만 써도 value임을 명시함(단, value하나만 쓸때는 가능하지만 다른 타입과 같이 쓰는거면 명시해줘야함)
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
