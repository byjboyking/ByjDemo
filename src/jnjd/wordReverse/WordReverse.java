package jnjd.wordReverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import byj.util.U;

/**
 * 
 * 单词倒排
 * 对字符串中的所有单词进行倒排。
 * 说明：每个单词是以26个大写或小写英文字母构成，”-”作为单词连接符使用时，
 * 视为单词的一部分，但在一个单词中只能连续一次，连续出现2个”-”以上时
 * 视为分隔符，如”aa-bb”中的”-”视为分割符，2个单词为”aa”和”bb”，
 * 非构成单词的字符均视为单词间隔符。
 * 要求倒排后的单词间隔符以空格表示，原字符串中相邻单词间有多个间隔符时，
 * 倒排转换后只允许出现一个空格间隔符。每个单词最长20个字母。
 * 
 * 示例：
 * 给定原始单词语句： I am a – student
 * 倒排后的单词语句：studuent a am I
 * 
 */
public class WordReverse {

	/**
	 * 功能：对字符串中的所有单词进行倒排。
	 * @param pInput 给定的单词串
	 * @param pOutput 倒排后的单词串
	 * @return 成功0，失败-1
	 */
	public int converse(String pInput,StringBuffer pOutput){
		if(pInput==null){
			return -1;
		}
		
		//非字母中杠  或 中杆2个及以上  作为分隔符
		String[] arr = pInput.split("([^a-zA-Z\\-])|([\\-]{2,})");
		List<String> list = new ArrayList<String>();
		
		if(arr ==null || arr.length==0){
			return -1;
		}
		
		for(int i=0;i<arr.length;i++){
			String item = arr[i];
			if(item.isEmpty()){
				continue;
			}
			if(item.length()>20){
				U.err("converse->item.length()>20, return -1;");
				return -1;
			}
			if(item.equals("-")){
				continue;
			}
			list.add(item);
		}
		
		if(list.size()==0){
			return -1;
		}
		
		Collections.reverse(list);
		for(int i=0;i<list.size();i++){
			if(i!=0){
				pOutput.append(" ");
			}
			pOutput.append(list.get(i));
		}
		return 0;
	}
}
