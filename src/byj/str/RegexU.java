package byj.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import byj.util.U;

public class RegexU {

	public static void main(String[] args) {
		//1、通过正则切割字符 计算 切割后的字符串个数
		int counter = getSpliterCounter(" (2,3,22),(-1,5,15),(-1,4,10)", REGEX_SPLITTER);
		U.info(" ),(   counter:" + counter);

		//2、 通过正则 解析  (m1,n1,o1),(m2,n2,o2) 
		readBracketRegex("(-1,0,1) , ( -3, 2, 5  ), (3, 5, 8)");
	}
	
	private final static String REGEX_SPACE = "[\\s]*";
	private final static String REGEX_NUMBER = "[-]?[0-9]+"; // 包括负数符号
	private final static String REGEX_COMMA = "[\\,]{1}";
	private final static String REGEX_LEFT_BRACKET = "[\\(]{1}";
	private final static String REGEX_RIGHT_BRACKET = "[\\)]{1}";

	// ) , (
	private final static String REGEX_SPLITTER = REGEX_RIGHT_BRACKET + REGEX_SPACE + REGEX_COMMA + REGEX_SPACE
			+ REGEX_LEFT_BRACKET;

	// ( 1 , 1, 1 )
	private final static String REGEX = REGEX_LEFT_BRACKET + REGEX_SPACE + "(" + REGEX_NUMBER + ")" + REGEX_SPACE
			+ REGEX_COMMA + REGEX_SPACE + "(" + REGEX_NUMBER + ")" + REGEX_SPACE + REGEX_COMMA + REGEX_SPACE + "("
			+ REGEX_NUMBER + ")" + REGEX_SPACE + REGEX_RIGHT_BRACKET;
	
	
	public static int getSpliterCounter(String itemsStr,String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(itemsStr);
		int counter = 0;
		while (m.find()) {
			counter++;
		}

		return counter;
	}
	
	public static void readBracketRegex(String input)  {
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(input);

		int index = 0;
		while (m.find()) {
			String group = m.group();
			//U.info("readOperations->group:  " + group);

			try {
				int dependsOn = Integer.parseInt(m.group(1).trim());
				int position = Integer.parseInt(m.group(2).trim());
				int delta = Integer.parseInt(m.group(3).trim());

				U.info("readOperations->dependsOn:"+dependsOn+"; position:"+position+"; delta:"+delta);
				index++;
			} catch (NumberFormatException e) {
				U.err("readOperations->解析异常，直接返回");
				// 解析异常，直接返回
			}
		}

	}

}
