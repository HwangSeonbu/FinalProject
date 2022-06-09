package kr.or.ddit.java;

public class JavaCodeTest {

	public static void main(String[] args) {
		
		String[] arr;
		double[] answerFlArr;
		String arrstr = "5,4,6,4,5,1,5,2,5,4,8,5";
		
		arr = arrstr.split(",");
		for(int i = 0; i< arr.length; i++) {
			System.out.print(arr[i]+"\t");
		}
		System.out.println(arr.length);
		System.out.println(arr.toString());
		System.out.println(arr);
	}
}
