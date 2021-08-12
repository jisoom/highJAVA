package kr.or.ddit.basic;

/*
 *제너릭 클래스를 만드는 방법
 *
 * 형식)
 * class 클래스명<제너릭타입글자> {
 * 		제너릭타입 글자 변수명; // 변수 선언에 제너릭을 사용할 경우
 * 		...
 * 		제너릭타입글자 메서드명() {//반환값이 있는 메서드에서 사용
 * 			
 * 			return 값;
 * 		}
 * 		...
 * }
 * 
 * --제너릭 타입 글자--
 * T => Type
 * K => Key
 * V => Value
 * E => Element(자료구조에 들어가는 요소들을 나타낼 때 사용)
 */

public class T02_GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		//Object 타입이라 반환할 때 형변환 해줌
		String rtnNg1 = (String) ng1.getVal(); //rtn : return
		System.out.println("문자열 반환값 trnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환 값 irtnNg2 => " + irtnNg2);
		System.out.println();
		
		//제너릭은 사용할 때 타입을 알려주기 때문에 형변환 안해도됨
		MyGeneric<String> mg1= new MyGeneric<String>(); //T로 선언했던 타입을 사용할 때 무슨 타입을 사용할지 정확히 알려줘야함
		MyGeneric<Integer> mg2= new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : " + rtnNg1);
		System.out.println("제너릭 정수형 반환값 : " + irtnNg2);
		
	}
}

class NonGenericClass {
	private Object val; //모든 타입 받을 수 있음

	public Object getVal() {
		return val;
	}
                                                                                                                                                                                                         
	public void setVal(Object val) {
		this.val = val;
	}
	
	
}

class MyGeneric<T> { //Type을 의미함(지금은 뭘 쓸지 모르니까 알려줄 수 없고 나중에 사용할 때 알려주겠다는 뜻)
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}

//	@Override
//	public String toString() {
//		return "MyGeneric [val=" + val + "]";
//	}  //toString() 해주면 mg1을 출력할 수 있음 이런 형태로 출력됨
	
	
}