package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest02 {
	public static void main(String[] args) {
		//람다식을 사용하지 않았을 경우
		LambdaTestInterface1 lam1 = new LambdaTestInterface1() {
			
			@Override
			public void test() {
				System.out.println("안녕하세요");
				System.out.println("익명 구현 객체 방식입니다.");
			}
		};
		lam1.test(); //메서드 호출
		
		LambdaTestInterface1 lam2 = 
				() -> {System.out.println("반가워요\n람다식으로 처리하는 방식입니다.");
						System.out.println("ㅋㅋ");
				};
				
		lam2.test(); //메서드 호출
		
		
		System.out.println("-------------------------------------------------");
		
		/*
		 * 람다식의 작성 방법
		 * 기본 형식) (자료형이름 매개변수명, ...) -> {실행문들;...}
		 * 
		 * 1)매개 변수의 '자료형 이름'은 생략할 수 있다.
		 * 
			 * 예) (int a) -> {System.out.println(a);}
			 * 		   (a) -> {System.out.println(a);}
		 * 
		 * 2)매개변수가 1개일 경우에는 괄호'()'를 생략할 수 있다.
		 * (단, '자료형이름'을 지정할 경우에는 괄호를 생략할 수 없다.)
		 * 
		 	* 예) a -> {System.out.println(a);}
		 * 
		 * 3)'실행문'이 1개일 경우에는 '{}'를 생략할 수 있다.
		 * (이 때 문장의 끝을 나타내는세미콜론(;)도 생략한다.)
		 * 
		 	* 예) a -> System.out.println(a)
		 * 
		 * 4)매개변수가 하나도 없으면 괄호'()'를 생략할 수 없다.
		 * 
		 	* 예) () -> System.out.println("안녕")
		 *  
		 * 5)반환 값이 있을 경우에는 return 명령을 사용한다.
		 * 
		 	* 예) (a, b) -> { return a + b;}
		 	* 	  (a, b) -> return a + b
		 * 
		 * 6)실행문에 return문만 있을 경우 return명령과 '{}'를 생략할 수 있다.
		 * 
		 	* 예) (a, b) -> a + b
		 */
		
		LambdaTestInterface2 lam3 = 
				(int z) -> {
					int result = z + 100;
					System.out.println("result = " + result);
		};
		lam3.test(30);
		
		LambdaTestInterface2 lam4 =
				z -> {
					int result = z + 300;
					System.out.println("result = " + result);
		};
		lam4.test(60);
		
		LambdaTestInterface2 lam5 = 
				z -> System.out.println("result = " + (z + 500)); //세미콜론 생략 못하는 이유 : println 문장이랑 구분이 안되서
		lam5.test(90);
		
		System.out.println("---------------------------------------------------");
		
		LambdaTestInterface3 lam6 =
				(int x, int y) -> {
					int r = x + y;
					return r;
				};
		int k = lam6.test(20, 50);
		System.out.println("k = " + k);
				
		LambdaTestInterface3 lam7 =
				(x, y) -> {
					return x + y;
				};
		k = lam7.test(80, 50);
		System.out.println("k = " + k);
			
		LambdaTestInterface3 lam8 =
				(x, y) -> x + y;
		k = lam8.test(100, 200);
		System.out.println("k = " + k);
				
		LambdaTestInterface3 lam9 =
				(x, y) -> {return x > y ? x :y;};
		k = lam9.test(100, 200);
		System.out.println("k = " +k);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.forEach( x -> System.out.println(x));
		
	}
}