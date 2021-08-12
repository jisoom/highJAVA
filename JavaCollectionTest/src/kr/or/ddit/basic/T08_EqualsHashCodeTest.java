package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T08_EqualsHashCodeTest {

	/*
	 * 해시함수는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
	 * 해시 함수에 의해 얻어지는 값은 해시 값, 해시 코드, 해시 체크섬 또는 간단하게 해시라고 한다.
	 * 
	 * HashSet, HashMap, HashTable과 같은 객체들을 사용할 경우
	 * 객체가 서로 같은지를 비교하기 위해 equals()메서드와 hashCode()메서드를 호출한다.
	 * 그래서 객체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야한다.
	 * 
	 * -equals()메서드는 두 객체의 내용(값)이 같은지 비교하는 메서드이고,
	 * -hashCode()메서드는 두 객체가 같은 객체인지 비교하는 메서드로  사용된다.
	 * 
	 * -equals()메서드와 hashCode()메서드에 관련된 규칙
	 * 1. 두 객체가 같으면 반드시 같은 hashCode를 가져야 한다.
	 * 2. 두 객체가 같으면 equals()메서드를 호출했을 때 true를 반환해야한다.
	 *    즉, 객체 a, b가 같다면 a.equals(b)와 b.equals(a)둘다 true이어야 한다.
	 * 3. 두 객체의 hashCode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
	 *    하지만, 두 객체가 같으면 반드시 hashCode가 같아야한다.
	 * 4. equals()메서드를 override하면 반드시 hashCode()메서드도 override해야 한다.
	 * 5. hashCode()는 기본적으로 Heap메모리에 있는 각 객체에 대한 메모리 주소 매핑 정보를 기반으로 한 정수값을 반환한다.
	 *    그러므로 클래스에서 hashCode메서드를 override하지 않으면 절대로 두 객체가 같은 것으로 간주될 수 없다.
	 *    
	 * hashCode()메서드에 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashCode 값을 만들어 낼 수 있다.
	 * 그래서 객체가 같지 않더라도 hashCode가 같을 수 있다.
	 */
	
	public static void main(String[] args) {
		
		Person p1 = new Person(1," 홍길동");
		Person p2 = new Person(1," 홍길동"); //p1과 p2를 같다고 놓고 싶음 ==> Object에서 가져온 equals를 오버라이드 해줘야함
		Person p3 = new Person(1," 이순신");
		
		//equals()가 false 나온 이유 : 내가 구현한게 아니고 Object에서 같은 객체인지 확인 한 것이기 때문에
		//필드만 같고 다른 객체로 되어 false가 나옴 
		//p1.equals(p1) 을 하면 객체가 동일하기 때문에 true나옴
		System.out.println("p1.equals(p2) : " +p1.equals(p2));
		//p1과 p2는 객체자체가 다르기 때문에 false가 나옴 p2 = p1라고 되있으면 p2가 결국 p1객체이기 때문에 true가 나옴
		System.out.println("p1 == p2 : " + (p1 == p2));
		
		/*
		hashCode 오버라이드 하기 전에 실행 해봄
		p1과 p2를 같은 애로 만들었는데 중복되서 들어가는지 확인하기 위해 실행 해봄 add ==> true
		들어간 이유 : equals는 오버라이드 했지만 hashCode는 오버라이드 하지 않았기 때문에 hashCode는 항상 다르게 나오므로
		다른 객체로 인식되서 중복되었음에도 불구하고 데이터가 들어감
		
		hashCode 오버라이드 후 ==> true 지만 중복된 것은 들어가지 않아서 p1과 p2가 동일하기 때문에 해시코드가 같으므로 하나만 들어감(중복 허용하지 않음)
		*/
		Set<Person> set = new HashSet<>();
		set.add(p1);
		set.add(p2);
		
		System.out.println("p1, p2 등록 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId()+ ":" + p.getName());
		}
		
		System.out.println("add(p3) 성공 여부 : " + set.add(p3));
		
		System.out.println("add(p3) 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId()+ ":" + p.getName());
		}
		
		System.out.println("remove(p2) 성공 여부 : " + set.remove(p2));
		
		System.out.println("remove(p2) 후 데이터");
		for(Person p : set) {
			System.out.println(p.getId()+ ":" + p.getName());
		}
		
		
	}
	
}

class Person {
	private int id; 
	private String name;
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) //this => p1
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) //getClass() : 현재 객체가 만들어질 때 사용한 클래스 정보
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	


	
}