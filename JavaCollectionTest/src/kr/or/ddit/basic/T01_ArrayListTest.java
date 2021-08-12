package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class T01_ArrayListTest {

	public static void main(String[] args) {
		//Default Capacity = 10
		List list1 = new ArrayList<>(); //ArrayList를 쓰던 Vector를 쓰던 리스트타입의 인터페이스기 때문에 에러가 나지 않음
		//제너릭 해주지 않았으므로 Object 타입임 
		
		//add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa"); //String
		list1.add("bbb");
		list1.add(111); // new Integer(111); //Integer
		list1.add('k'); //character
		list1.add(true); //boolean
		list1.add(12.34); // double (뒤에 d 생략됨)
		
		//size() ==> 데이터 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 = > " + list1);
		
		//get으로 데이터 꺼내오기
		System.out.println("1번째 데이터 자료 : " + list1.get(1));
		
		//데이터 끼워 넣기도 같다.
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1); //0번 자리에 zzz가 추가되고, 한칸씩 뒤로 밀려남
		
		//데이터 변경하기
		String temp = (String) list1.set(0, "YYY"); //list1은 Object 타입이므로 String으로 형변환 해준 후 값 변경해줌  
		System.out.println("temp => " + temp); //set은 반환할 때 기존에 있던 값을 반환
		System.out.println("list1 => " + list1);
		
		//삭제하기도 같다.
		list1.remove(0);
		System.out.println("삭제 후 : " + list1);
		
		list1.remove("bbb");
		System.out.println("bbb 삭제 후 : " + list1);
		
		list1.remove(new Integer(111)); //111만 쓰면 인덱스로 생각을 해서 에러남
		System.out.println("111 삭제 후 : " + list1);
		System.out.println("===============================================");
//		list1.remove(new Double(12.34)); //12.34 삭제 해줄 때 new Double 사용해줌
//		System.out.println(list1);
		
		//list1에 들어있는 것 출력해주기
//		for(Object o : list1) {
//			System.out.println(o);
//		}
		
		//제너릭을 지정하여 선언할 수 있다. 제너릭 : 타입을 일반화함
		List<String> list2 = new ArrayList<>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i) );			
		}
		System.out.println();
		
		//향상된 for문 사용해서 출력하기
		for(String s : list2) {
			System.out.println(s);
		}
		System.out.println("------------------------------------------------------------");
		
		//contains(비교객체); => 리스트에 '비교객체'가 있으면
		//                     true, 없으면 false가 리턴됨.
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		
		//indexOf(비교객체)
		// => 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index값을 반환한다. 없으면 -1 반환함.
		System.out.println("DDD의 index 값 : " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index 값 : " + list2.indexOf("ZZZ"));

		//toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환한다. 기본적으로 Object배열 반환함.
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		
		// 리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
		// 제너릭 타입의 0개 짜리 배열을 생성해서 매개변수로 넣어준다.
		// => 배열의 크기가 리스트 크기보다 작으면 리스트의 크기에 맞는
		//    배열을 생성한다.
		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 개수 : " + strArr2.length);
		
		
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
		}

		//지울 때 맨 처음 인덱스부터 지우면 지워지면서 앞으로 밀리기 때문에 제대로 안지워짐
		for (int i = 0; i < list2.size(); i++) {
			list2.remove(i);
		}
		System.out.println(list2.size());
		
		//지울 때 마지막 인덱스부터 지워야 깔끔하게 다 지워짐
		for (int i = list2.size()-1; i >= 0 ; i--) {
			list2.remove(i);
		}
		System.out.println(list2.size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}