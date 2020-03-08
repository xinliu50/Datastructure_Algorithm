import java.util.*;
public class today {

	public static void main(String[]args) {
		String s = "1B 2C, 2D 4D";
		printList(turn(s));
		
	}
	public static void print(int[][]a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print("[");
			for(int j = 0; j < a[0].length; j ++) {
				System.out.print(a[i][j]+",");
			}
			System.out.println("],");
		}
	}
	public static List<List<int[]>> turn(String art) {
		art = art.replaceAll(" ", "");
		String[]split = art.split(",");
		var list = new ArrayList<List<int[]>>();
		var ls1 = new ArrayList<int[]>();
		var ls2 = new ArrayList<int[]>();
		int first = 0, second = 0;
		int i = 0;
		while(i < art.length()){
			var ls = new ArrayList<int[]>();
			var c = art.charAt(i);
			if(Character.isDigit(c)) {
				first = Character.getNumericValue(c);
				first -=1;
				i++;
			}
			else {
				second = Character.getNumericValue(c-17);
				i++;
			}
			ls.add(new int[] {first,second});
			list.add(ls);
		}
		return list;
	}
	public static void printList(List<List<int[]>> ls) {
		for(int i = 0; i < ls.size(); i ++) {
			for(int j = 0; j < ls.get(i).size(); j ++) {
				System.out.println(ls.get(i).get(j).toString());
			}
		}
	}
//	public static void turn(String art) {
//		art = art.replaceAll(" ", "");
//		String[]split = art.split(",");
//		
//		System.out.println(split[0].toString());
//		System.out.println(split[1].toString());
//	}
}
