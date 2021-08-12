import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SLenComparator {
	public static void main(String[] args) {
		Comparator<String> comStr = (s1, s2) -> s1.length() - s2.length();
		List<String> list = new ArrayList<>();
		list.add("Robot");
		list.add("Lambda");
		list.add("Box");
		
		Collections.sort(list, comStr);
		
		for(String s : list) {
			System.out.println(s);
		}
	}
}
