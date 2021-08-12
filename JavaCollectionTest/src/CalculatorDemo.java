
public class CalculatorDemo {
	public static <T> void calAndShow(Calculate<T> op, T n1, T n2) {
		T r = op.cal(n1, n2);
		System.out.println(r);
	}
	
	public static void main(String[] args) {
		Calculate<Integer> ci1 = (a, b) -> a + b;
		calAndShow(ci1, 3, 4);
		
		Calculate<Double> cd1 = (a, b) -> a + b;
		calAndShow(cd1, 2.5, 7.1);
		
		Calculate<Integer> ci2 = (a, b) -> a - b;
		calAndShow(ci2, 4, 2);
		
		Calculate<Double> cd2 = (a, b) -> a - b;
		calAndShow(cd2, 4.9, 3.2);
	}
}
