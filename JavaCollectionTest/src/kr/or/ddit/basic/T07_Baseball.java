package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import util.ScanUtil;

public class T07_Baseball {

	public static void main(String[] args) {

		Set<Integer> intRnd = new HashSet<>();
		while(intRnd.size() < 3) { // Set의 데이터가 3개가 될 때 까지 반복한다.
			int num = (int)(Math.random()*9 + 1);
			intRnd.add(num);
		}
		System.out.println(intRnd);
		
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		int count = 0;
		while(true) {
			System.out.print("1~9까지의 숫자 중 중복되지 않게 3자리를 입력해주세요>");
			int input = ScanUtil.nextInt();
			int i3 = input % 10;
			input /= 10;
			int i2 = input % 10;
			input /= 10;
			int i1 = input % 10;
			
			int strike = 0;
			int ball =0;
			int out = 0;
			
			if(intRndList.get(0)==i1) strike++;
			if(intRndList.get(1)==i2) strike++;
			if(intRndList.get(2)==i3) strike++;
			
			if(intRndList.get(0) == i2 || intRndList.get(0) == i3) ball++;
			if(intRndList.get(1) == i1 || intRndList.get(1) == i3) ball++;
			if(intRndList.get(2) == i1 || intRndList.get(2) == i2) ball++;
			
			out = 3 - strike - ball;
			
			System.out.println(++count+"차 시도(" + i1 + i2+ i3+ ") :"
					+ strike + "S" + ball + "B" + out + "O");
			System.out.println("----------------------------");
			if(strike ==3){
				System.out.println(count+"번만에 맞췄군요");
				break;
			}
		}
		
		
		

	}

}