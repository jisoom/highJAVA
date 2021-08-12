package kr.or.ddit.basic;

import java.util.Arrays;

public class T06_WildCardTest {

	/*
	 * 모든 과정 수강정보 조회
	 * <?> => <? extends Object>
	 */
	public static void listCourseInfo(Course<?> course) {
		System.out.println(course.getName() + "수강생:" + Arrays.toString(course.getStudents()));
	}
	
	/*
	 * 학생 과정 수강정보 조회 <student와 HighStudent 올수 있음>
	 */
	public static void listStudentCourseInfo(Course<? extends Student> course) {
		System.out.println(course.getName() + "수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/*
	 * 직장인 과정 수강정보 조회<Worker와 Person>
	 */
	public static void listWorkerCourseInfo(Course<? super Worker> course) {
		System.out.println(course.getName() + "수강생 : " + Arrays.toString(course.getStudents()));
	}

	public static void main(String[] args) {
		Course<Person> personCourse = new Course<>("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCourse = new Course<>("직장인과정", 5);
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course<>("학생과정" , 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCourse = new Course<>("고등학생학생과정" , 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
		
		listCourseInfo(personCourse);
		listCourseInfo(workerCourse);
		listCourseInfo(studentCourse);
		listCourseInfo(highStudentCourse);
		System.out.println("--------------------------------------------------------------");
		
//		listStudentCourseInfo(personCourse);
//		listStudentCourseInfo(workerCourse);
		listStudentCourseInfo(studentCourse);
		listStudentCourseInfo(highStudentCourse);
		
		System.out.println("--------------------------------------------------------------");
		listWorkerCourseInfo(personCourse);
		listWorkerCourseInfo(workerCourse);
//		listWorkerCourseInfo(studentCourse);
//		listWorkerCourseInfo(highStudentCourse);
		
		
		
	
	}
}


class Person {
	String name; //이름

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "이름 : " +name;
	}
	
}

class Worker extends Person {
	public Worker(String name) {
		super(name);
	}
}

class Student extends Person {
	public Student(String name) {
		super(name);
	}
}

class HighStudent extends Student {
	public HighStudent(String name) {
		super(name);
	}
	
}

/*
 * 수강 과정
 */
class Course<T> {
	private String name; //과정명
	private T[] students; //수강생(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		//타입 파라미터로 배열을 생성시 오브젝트 배열을 생성 후, 타입 파라미터 배열로 
		//캐스팅 처리해야함
		
		//제너릭 배열 생성 실패 (new 연산자는 생성할 객체의 타입이 정확히 정의되어야 가능)
		//students = new T[capacity]; T가 명확하지 않기 때문에 만들어줄 수 없음
		students = (T[]) (new Object[capacity]); //오브젝트 타입으로  배열을 먼저 만들어주고 T타입으로 캐스팅해줘야함
	}

	public String getName() {
		return name;
	}

	public T[] getStudents() {
		return students;
	}
	
	/*
	 * 수강생 등록
	 */
	public void add(T t) {
		for (int i = 0; i < students.length; i++) {
			if(students[i] == null) { //아직 등록되지 않은(빈) 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
}