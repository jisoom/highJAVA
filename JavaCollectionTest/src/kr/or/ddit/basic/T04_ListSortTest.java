package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04_ListSortTest {

	public static void main(String[] args) {
		
		List<Member> memList = new ArrayList<Member>(); //new ArrayList<>();랑 동일함
		memList.add(new Member(1,"홍길동", "010-111-1111"));
		memList.add(new Member(5,"변학도", "010-111-2222"));
		memList.add(new Member(9,"성춘향", "010-111-3333"));
		memList.add(new Member(3,"이순신", "010-111-4444"));
		memList.add(new Member(6,"강감찬", "010-111-5555"));
		memList.add(new Member(2,"일지매", "010-111-6666"));
		
		System.out.println("정렬전");
		
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		
		Collections.sort(memList); //정렬하기
		
		System.out.println("이름의 오름차순으로 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		
		
		//외부 정렬 기준을 이용한 정렬하기
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순으로 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
	}
}

class Member implements Comparable<Member>{
	//getter,setter 하는 이유
	//한 오브젝트의 내부속성을 외부에서 알지 못하게 하고, 접근이 불가능하게 하여 객체지향의 목적인 `정보은닉` 과 `객체의 무결성 보장`을 위함 
	//필드를 private로 만들어 외부의 접근을 제한한 후,
	//Setter를 사용해 전달받은 값을 내부에서 가공해 필드에 넣어주는 방식을 사용하고 
	//필드 값을 가져올 때도, Getter를 사용해 본 필드의 값을 숨긴 채 내부에서 가공된 값을 꺼낼 수 있습니다.

	private int num; //번호
	private String name; //이름
	private String tel; //전화번호
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	//이름을 기준으로 오름차순 정렬이 되도록 설정한다.
	//만약 this.getName() => 홍길동이면 Member mem.getName() => 변학도 
	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName()); //*-1 하면 내림차순
	}

	//toString() : 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
}

/*
 * 정렬 기준의 외부 선언을 위해서는 Comparator 인터페이스를 구현하면 된다.
 * Member의 번호 (num)의 내림차순으로 정렬하기
 */
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		if(mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if(mem1.getNum() == mem2.getNum()){
			return 0;
		}else {
			return 1;
		}
		*/
		//Wrapper 클래스에서 제공하는 메서드를 이용하는 방법1
		//래퍼 클래스(Wrapper class) : 8개의 기본 타입에 해당하는 데이터를 객체로 포장해 주는 클래스
		//return Integer.compare(mem1.getNum(), mem2.getNum()) *-1;
		
		//Wrapper 클래스에서 제공하는 메서드를 이용하는 방법2
		//Wrapper 클래스에서는 compareTo를 제공하기 때문에 
		//앞의 mem.getNum을 Integer타입으로 객체 생성해서 Wrapper클래스로 만들어줌
		//compareTo를 사용함 (뒤의 mem2.getNum()은 자동으로 Integer타입이 됨)
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) *-1;
	}
	
}