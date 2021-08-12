package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T05_WildCardTest {

	/*
	 * 와일드 카드 : 어떤 타입인지 모를 때 사용
	 * <? extends T> : 와일드 카드의 상한 제한. T와 그 자손들만 가능
	 * <? super T> : 와일드 카드의 하한 제한. T와 와 그 조상들만 가능
	 * <?> : 모든 타입이 가능 <? extends Object>와 동일
	 */
	
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>(); //과일 상자
		FruitBox<Apple> appleBox = new FruitBox<>(); //사과 상자
		FruitBox<? extends Fruit> fruitBox2 = new FruitBox<Fruit>();
		FruitBox<?> fruitBox3 = new FruitBox<Fruit>();
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
//		appleBox.add(new Grape()); //타입이 Apple이 아니라 담을 수 없음
		
		Juicer.makeJuice(fruitBox);
//		Juicer.makeJuice(appleBox); //제너릭을 통해서 에러를 해결할 수 있음
	}
}

class Juicer {
//		static <>void makeJuice(FruitBox<Fruit> box) {
		//제너릭 타입 객체를 파라미터에 사용시 문제점 발생함.
		//제너릭 타입이 다른 것만으로는 오버로딩이 성립하지 않음
		// => 컴파일 후 제거됨. => 메서드 중복 정의
//		static <T extends Fruit>void makeJuice(FruitBox<Fruit> box) {
		//방법 1) 제너릭 메서드(제한된 타입 파라미터)로 선언함 
		static void makeJuice(FruitBox<? extends Fruit> box) {
		//방법2) 와일드 카드를 이용하여 제너릭 타입의 객체 참조

		
		
		String fruitListStr = ""; //과일 목록
		int cnt = 0;
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		System.out.println(fruitListStr + " => 쥬스 완성!");
	}
}

/*
 * 과일
 */
class Fruit {
	private String name; //과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일(" + name + ")";
	}
	
	
}

class Apple extends Fruit {
	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit {
	public Grape() {
		super("포도");
	}
}

/*
 * 과일 상자
 */
class FruitBox<T> {
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<T>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
	
	
}