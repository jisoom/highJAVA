package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.ScanUtil;

public class Lotto {

	public static void main(String[] args) {
		while (true) {
			System.out.println("==================================================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------------------------------");
			System.out.println("1.Lotto 구입");
			System.out.println("2.프로그램 종료");
			System.out.println("==================================================");
			System.out.print("메뉴 선택 :");
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				System.exit(0);
			}
		}
	}

	public static void buyLotto() {
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 :");
		int money = ScanUtil.nextInt();
		if (money >= 1000) {
			int count = money / 1000;
			int change = money % 1000;
			System.out.println("행운의 로또 번호는 아래와 같습니다.");

			for (int i = 0; i < count; i++) {
				Set<Integer> intRnd = new HashSet<>();

				while (intRnd.size() < 6) {
					int num = (int) (Math.random() * 45 + 1);
					intRnd.add(num);
				}
				
				List<Integer> intRndList = new ArrayList<>(intRnd);
				
				System.out.print("로또번호" + (i + 1) + " : ");
				System.out.print(intRndList.get(0));
				
				for (int j = 1; j < intRndList.size(); j++) {
					System.out.print("," + intRndList.get(j));
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + change + "원입니다.");
		}else {
			System.out.println("1000원 이상부터 이용 가능합니다.");
		}
	}


}