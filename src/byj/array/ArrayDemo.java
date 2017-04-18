package byj.array;

import java.util.Arrays;

import byj.util.U;

public class ArrayDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//*******01. Arrays.copyOf ���÷�*********
		int[] a = {2,3,4};

		int[] newa = Arrays.copyOf(a, 5);
		U.info("a:"+U.array2Str(a));
		U.info("newa:"+U.array2Str(newa));
		
		// a:{2,3,4}
		// newa:{2,3,4,0,0}
		
		
		//*******02. Arrays.copyOf ���÷�*********
		Arrays.fill(a, 555);
		U.info("a:"+U.array2Str(a));
		
		
		//*******03. Arrays.binarySearch ���÷�*********
		
		int[] a2 = { 3, 5, 2, 10, 1 };
		U.print("before sort a2:" + U.array2Str(a2));
		Arrays.sort(a2);
		U.print("after sort a2:" + U.array2Str(a2));

		int rtn = Arrays.binarySearch(a2, 5);
		U.print("rtn:" + rtn);
		rtn = Arrays.binarySearch(a2, 4);
		U.print("rtn:" + rtn);
			
		//��ӡ�����
		//before sort a2:{3,5,2,10,1}
		//after sort a2:{1,2,3,5,10}
		//rtn:3
		//rtn:-4
		
		//*******04. Arrays.toString ���÷�*********
		int[] a4 = {3,5,2,10,1};
		U.print("a4:"+Arrays.toString(a4));
		
		int[] a5 = null;
		U.print("a5:"+Arrays.toString(a5));
		//��ӡ�����
		//a4:[3, 5, 2, 10, 1]
		//a5:null
		
		
	}

}
