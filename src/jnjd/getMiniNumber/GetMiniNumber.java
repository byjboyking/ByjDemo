package jnjd.getMiniNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import byj.util.U;


/**
 * ������һ�����������飬���������������ų�һ������������ų���������������С��һ����
 * 
 * ��������1��
 * {2,1}
 * �������1��
 * 12
 * 
 * ��������2��
 * {32,321}
 * �������2��
 * 32132
 * 
 * ��������3��
 * {4589,101,41425,9999}
 * �������3��
 * 1014142545899999
 * 
 *
 */
public class GetMiniNumber {

	/**
	 * ������������ų���С����
	 * @param positiveArray ����������
	 * @return �ųɵ���С����, �쳣����-1
	 */
	public static long getMiniNumber(int[] positiveArray){
		if(positiveArray==null || positiveArray.length==0){
			return -1;
		}
		
		List<Number> list = new ArrayList<Number>();
		for(int i=0;i<positiveArray.length;i++){
			int item = positiveArray[i];
			list.add(new Number(item));
		}
		
		Collections.sort(list);
		//�����ʾ���� {1,20,201,203,2033} 
		
		int[] indexArr = new int[positiveArray.length];
		Arrays.fill(indexArr, -1);
		
		int index = 0;
		String str  ="";
		getMin( index,  list,  indexArr,str);
		
		long result = -1;
		try{
			 result = Long.parseLong(minNum);
		}catch(Exception ex){
			U.err("getMiniNumber->ex:"+ex);
		}
		
		//reset minNum
		minNum="";
		return result;
	}
	

	private static String minNum = "";
	
	private static void getMin(int index, List<Number> orderList, int[] indexArr,String str){
		if(index>=orderList.size()){
			return;
		}
		
		//reset indexArr
		for(int i=index;i<indexArr.length;i++){
			indexArr[i] = -1;
		}
		
		List<Integer> indexList = getMinIndexList( orderList,  indexArr);
		if(indexList.size()==0){
			return;
		}
		
		for(int i=0;i<indexList.size();i++){
			int indexItem = indexList.get(i);
			String item = orderList.get(indexItem).getNumber();
			String newStr = str+ item;
			
			if(index  ==orderList.size()-1){
				if(minNum.isEmpty()){
					minNum = newStr;
				}else{
					if(isNum1Lower(newStr,minNum)){
						minNum = newStr;
					}
				}
			}
			
			indexArr[index] = indexItem;
			getMin(index+1, orderList,  indexArr,newStr);
		}
	}
	
	//ȷʵС������С�� 12,123  �����������false��
	private static boolean isNum1Lower(String num1,String num2){
		
		int minLen = num1.length()>num2.length()?num2.length():num1.length();
		for(int i=0;i<minLen;i++){
			char c1 = num1.charAt(i);
			char c2 = num2.charAt(i);
			if(c1<c2){
				return true;
			}else if(c1>c2){
				return false;
			}else{
				continue;
			}
		}
		return false;
	}
	
	private static List<Integer> getMinIndexList(List<Number> orderList, int[] indexArr){
		List<Integer> list = new ArrayList<Integer>();
		
		String firstItem = "";
		for(int i=0;i<orderList.size();i++){
			if(isExist(i,indexArr)){
				continue;
			}
			
			String item = String.valueOf(orderList.get(i).number);
			if(firstItem.isEmpty()){
				firstItem = item;
				list.add(i);
				continue;
			}
			
			if(item.startsWith(firstItem)){
				list.add(i);
				continue;
			}
		}
		
		return list;
	} 
	
	private static boolean isExist(int value, int[] arr){
		for(int i=0;i<arr.length;i++){
			if(arr[i]==value){
				return true;
			}
		}
		return false;
	}
	

	public static class Number implements Comparable{
		public int number;
		
		public Number(int number) {
			this.number=number;
		}
		
		@Override
		public String toString() {
			return "number:"+number;
		}
		
		public String getNumber(){
			return String.valueOf(this.number);
		}

		@Override
		public int compareTo(Object o) {
			Number s = (Number) o;
			if(s.number==this.number){
				return 0;
			}
			
			String s1 = String.valueOf(this.number);
			String s2 = String.valueOf(s.number);
			int minLen = s1.length()>=s2.length()?s2.length():s1.length();
			for(int i=0;i<minLen;i++){
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(i);
				if(c1<c2){
					return -1;
				}else if(c1>c2){
					return 1;
				}else{
					continue;
				}
			}
			//ǰn��������ȫ��ͬ
			return s1.length()<s2.length()?-1:1;
		}
	}
}
