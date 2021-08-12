package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSort {

	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("20170601", "강지수", 100, 100, 100));
		studentList.add(new Student("20210602", "홍길동", 40, 50, 60));
		studentList.add(new Student("20160603", "성춘향", 50, 60, 70));
		studentList.add(new Student("20150604", "강감찬", 80, 90, 100));
		studentList.add(new Student("20200605", "이순신", 100, 80, 90));
		
		//등수 구하기
		for (int i = 0; i < studentList.size(); i++) {
			int rank = 1;
			for (int j = 0; j < studentList.size(); j++) {
				if(studentList.get(i).getSum()< studentList.get(j).getSum()) {
					rank++;
				}
			}
			studentList.get(i).setRank(rank);
		}
		
		System.out.println("정렬전");
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("-------------------------------------------------------------------------------------");
		Collections.sort(studentList);
		System.out.println("학번으로 오름차순 정렬 후");
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------------------------------------------------------");
		Collections.sort(studentList, new sortSumDescStuId());
		System.out.println("총점으로 오름차순 정렬, 동일하면 학번으로 내림차순 정렬");
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("--------------------------------------------------------------------------------------");

	}

}

class Student implements Comparable<Student>{
	private String stuId, name;
	private int kor, math, eng, sum, rank;
	
	public Student(String stuId, String name, int kor, int math, int eng) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.sum = kor + math + eng;
	}
	

	public String getStuId() {
		return stuId;
	}


	public void setStuId(String stuId) {
		this.stuId = stuId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	@Override
	public int compareTo(Student s) {
		return this.getStuId().compareTo(s.getStuId());
	}


	@Override
	public String toString() {
		return "Stu [stuId=" + stuId + ", name=" + name + ", kor=" + kor + ", math=" + math + ", eng=" + eng + ", sum="
				+ sum + ", rank=" + rank + "]";
	}
	
	
}

class sortSumDescStuId implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
//		if(stu1.getSum()> stu2.getSum()) {
//			return -1;
//		}else if(stu1.getSum() == stu2.getSum()) {
//			return stu1.getStuId().compareTo(stu2.getStuId()) *-1;
//		}else {
//		return 1;
//		}
		
		if(stu1.getSum() == stu2.getSum()) {
			return stu1.getStuId().compareTo(stu2.getStuId()) *-1;
		}else {
//			return Integer.compare(stu1.getSum(), stu2.getSum()) *-1;
			return new Integer(stu1.getSum()).compareTo(stu2.getSum())*-1;
		}
	}
	
}