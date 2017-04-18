package jnjd.StringSplit;

import java.util.ArrayList;
import java.util.List;

public class StringSplit {
	
	/**
	 * 连续输入字符串（输入次数为N,字符串长度小于100），请按长度8拆分每个
	 * 字符串后输出到新的字符串数组。
	 * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
	 * 例如：
	 * 输入：
	 * abc
	 * 123456789
	 * 输出：
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
