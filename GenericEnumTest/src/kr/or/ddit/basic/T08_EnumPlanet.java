package kr.or.ddit.basic;

public class T08_EnumPlanet {
	
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 
		목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		
		private int itg;
		
		Planet(int data){ //private이 기본성질임 ==>외부에서 마음대로 만들 수 없음
			this.itg = data;
		}

		public int getItg() {
			return itg;
		}
		
		
	}
	
	public static void main(String[] args) {
		
		for(Planet planet : Planet.values()) {
			System.out.println(planet.name()+"의 면적 : " + (double)(4 * 3.141592 * Math.pow(planet.getItg(), 2))+ "km^2");
		}

		
		
		
	}

}
