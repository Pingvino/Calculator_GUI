import java.util.StringTokenizer;


public class CalLogic {
	private StringTokenizer t;
	CalLogic(String str){
		this.t = new StringTokenizer(str, "+×-÷", true);
	}
	public int result(){
		int buffer = 0;
		// String buffer = new String();
		while (t.hasMoreTokens()) {
			String temp = t.nextToken();
			if (temp.equals("+")) {
				buffer = buffer + Integer.parseInt(t.nextToken());
			} else if (temp.equals("-")) {
				buffer = buffer - Integer.parseInt(t.nextToken());
			} else if (temp.equals("×")) {
				buffer = buffer * Integer.parseInt(t.nextToken());
			} else if (temp.equals("÷")) {
				buffer = buffer / Integer.parseInt(t.nextToken());
			} else {
				buffer = Integer.parseInt(temp);
			}
//		사칙연산 구현, 우선순위 없음
		}
		return buffer;
	}
}
