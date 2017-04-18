package jnjd.StringSplit;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {
	
	/**
	 * ���������ַ������������ΪN,�ַ�������С��100�����밴����8���ÿ��
	 * �ַ�����������µ��ַ������顣
	 * ���Ȳ���8���������ַ������ں��油����0�����ַ���������
	 * ���磺
	 * ���룺
	 * abc
	 * 123456789
	 * �����
	 * abc00000
	 * 12345678
	 * 90000000
	 * @param input
	 * @return
	 */
	public static String[] splitString(String[] input){
		if(input ==null){
			return null;
		}
		
		List<String> list = new ArrayList<String>();
		for(int i=0;i<input.length;i++){
			String item = input[i];
			if(item==null || item.length()==0){
				continue;
			}
			
			if(item.length()>=100){
				return null;
			}
			
			int remain = item.length()%8;
			if(remain!=0){
				for(int j=0;j<8-remain;j++){
					item+="0";
				}
			}
			
			int size = item.length()/8;
			for(int j=0;j<size;j++){
				String subItem = item.substring(j*8,j*8+8);
				list.add(subItem);
			}
		}
		String[] strings = new String[list.size()];
		list.toArray(strings);
		return strings;
	}

}
