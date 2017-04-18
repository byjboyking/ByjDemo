package byj.str;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import byj.util.U;
public class RegexDemo {
	public static void main(String[] args) {
		patternSample1();
	}
	private static void patternSample1() {
		//Java 对正则表达式的处理集中在以下两个类：
		//java.util.regex.Pattern 模式类：一个正则表达式经编译后的表现模式，用来表示一个编译过的正则表达式。
		//java.util.regex.Matcher 匹配类：用模式匹配一个字符串所表达的抽象结果。一个 Matcher 对象是一个状态机器，
		//它依据 Pattern 对象作为匹配模式对字符串展开匹配检查。
		
		//------------示例列表--------------
		// 1. 句点符号 .
		// 2. 方括号符号 []
		// 3. "或"符号 |
		// 4. 表示匹配次数的符号 {5}
		// 5. “否”符号 ^
		// 6. 圆括号和空白符号
		// 7. 其它符号
		// 8. 解析ip地址
		// 9. html 解析（查询从 < 开始， 从 > 结束的 字符串）
		// 10. 反斜杠
		// 11. 验证邮箱地址
		// 12. 去除 html 标签
		// 13. 查找html中对应条件字符串
		// 14. 截取http://地址
		// 15. 替换指定{}中文字
		// 16. 查找以Java开头,任意结尾的字符串
		// 17. 以多条件分割字符串时（很重要!）
		// 18. 文字替换（首次出现字符）
		// 19. 文字替换（全部）
		// 20. 文字替换（置换字符） - (与 StringBuffer 结合使用！)

		// 21. 统计一个字符串中出现另外一个子字符串的次数  2017/1/1
		// 22. 一定规则的字符串解析和验证   (2 , 3 , 22 ) , ( -1 , 5 , -5 )      2017/1/1
		// 23. 一定规则的字符串解析和验证  "..." , "..."      20111016,  2017/1/1
		// 24. Pattern.matches() 基本示例
		// 25.  圆括号分组 
		// 26.  \d 示例
		// 27.  \w 示例
		// 28.  正则 切割字符串
		// 29.  使用Pattern的 split()函数来 切割字符串
		// 30. 通过正则进行replaceAll 操作。
		// 31. appendReplacement demo,  用处不太大。。
		// 32. 精准匹配次数demo  {3-5}
		// 33. 方括号 和 -  完成 范围过滤
		// 34. 大小写不敏感 Pattern.CASE_INSENSITIVE
		// 35. 使用正则表达式分割字符串
		// 36. 高级正则匹配 <name>Bill</name><salary>50000</salary>
		// 37. 将单词数字混合的字符串的单词部分大写,  感觉用处不太大
		// 38. 匹配 [...]  分组
		// 39. 正则综合运用（使用分组，解析信息以分组方式展现）
		
		
		// Java正则的功用还有很多，事实上只要是字符处理，就没有正则做不到的事情存在。
		// 当然，正则解释时较耗时间就是了

		// 匹配次数的符号
		// * 0次或多次   验证过！！  （0次，1次，n次）
		// + 1次或多次 验证过！！
		// ? 0次或1次 验证过！！
		// {n} 恰好n次 验证过！！
		// {n,m} 从n次到m次
		// {n,} 匹配n次以上（含n次）

		// 反斜杠规则:
		// \t 间隔 ('\u0009')
		// \d 数字 等价于[0-9] 验证过！！
		// \D 非数字 等价于[^0-9] 验证过！！
		// \s 空白符号 [\t\n\x0B\f\r]
		// \S 非空白符号 [^\t\n\x0B\f\r]
		// \w 单独字符 [a-zA-Z0-9]       验证过！！！
		// \W 非单独字符 [^a-zA-Z_0-9]
		// \f 换页符
		// \e Escape
		// \b 一个单词的边界
		// \B 一个非单词的边界
		// \G 前一个匹配的结束
		
		

		int order = 0;
		Pattern pattern = null;
		Matcher matcher = null;

		// 1. 句点符号 .
		// 句点符号匹配所有字符，包括空格、Tab字符甚至换行符：

		// 匹配 以“t”字母开头，以“n”字母结束的3个字符；
		pattern = Pattern.compile("t.n");
		matcher = pattern.matcher("tan Ten  tin, t#n,,,tpn## txxn");
		order = 0;
		System.out.println("1) 句点符号   . 匹配");
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}
		
		
		//输出
		//1) 句点符号   . 匹配
		//序号1:   tan
		//序号2:   tin
		//序号3:   t#n
		//序号4:   tpn

		System.out.println("-------------华丽的分界线---------------");

		// 2. 方括号符号 []
		// 只有方括号里面指定的字符才参与匹配,方括号之内你只能匹配单个字符：

		System.out.println("2) 方括号符号  [] 匹配");
		pattern = Pattern.compile("t[aeiou]n");
		matcher = pattern.matcher("tan Ten  tin, t#n,,,tpn## txxn");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		//输出
		//2) 方括号符号  [] 匹配
		//序号1:   tan
		//序号2:   tin
		
		System.out.println("-------------华丽的分界线---------------");

		// 3. "或"符号 |
		// 这里不能使用方扩号，因为方括号只允许匹配单个字符；这里必须使用圆括号()
		//圆括号的用法一： 进行分组， 并和 "|" 一起完成 "或" 匹配。
		
		//组是用括号划分的正则表达式，可以通过编号来引用组。组号从 0 开始，有几对小括号就表
		//示有几个组，并且组可以嵌套，组号为 0 的表示整个表达式，组号为 1 的表示第一个组，依此类推。
		//例如：A(B)C(D)E 正则式中有三组，组 0 是 ABCDE，组 1 是 B，组 2 是 D；
		// A((B)C)(D)E 正则式中有四组：组 0 是 ABCDE，组 1 是 BC，组 2 是 B；组 3 是 C，组 4 是 D。
		
		//组 可以方便后续 提取字符串。

		System.out.println("3) 或符号  | 匹配");
		pattern = Pattern.compile("t(a|e|i|o|oo)n");
		matcher = pattern
				.matcher("tan Ten  tin, t#n,,,tpn## txxn, toon, tcsn;");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		//输出
		//3) 或符号  | 匹配
		//序号1:   tan
		//序号2:   tin
		//序号3:   toon
		
		System.out.println("-------------华丽的分界线---------------");

		// 4. 表示匹配次数的符号 {5}

		// 搜索 美国的社会安全号码。这个号码的格式是999-99-9999
		// 解释： [0-9]{3} 匹配3次0-9 的数字

		System.out.println("4) 表示匹配次数的符号 - 美国的社会安全号码");
		
		//  -  前面是否加 转义字符\  ,  都不影响最终效果
		//pattern = Pattern.compile("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}"); //需要- 进行匹配
		pattern = Pattern.compile("[0-9]{3}-[0-9]{2}-[0-9]{4}"); //需要- 进行匹配
		
		//   pattern = Pattern.compile("[0-9]{3}-?[0-9]{2}-?[0-9]{4}"); // 有无 - 都进行匹配
		matcher = pattern
				.matcher("123-44-5788;  1333-44-5555; 123-55-88;123457899; 123-448888");
		
		// matcher =pattern.matcher("123-44-5788-22-3333;  1333-44-5555; 123-55-88");
		// //只会匹配 123-44-5788

		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		//输出
		//4) 表示匹配次数的符号 - 美国的社会安全号码
		//序号1:   123-44-5788
		//序号2:   333-44-5555
		
		System.out.println("-------------华丽的分界线---------------");

		// 美国汽车牌照的一种格式四个数字加上二个字母
		// 典型的美国汽车牌照号码，如8836KV

		System.out.println("4) 表示匹配次数的符号 - 美国汽车牌照");
		pattern = Pattern.compile("[0-9]{4}[A-Z]{2}");
		matcher = pattern.matcher("8834KV;3333MC; 33334V;");

		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		//输出
		//4) 表示匹配次数的符号 - 美国汽车牌照
		//序号1:   8834KV
		//序号2:   3333MC
		
		System.out.println("-------------华丽的分界线---------------");

		// 5. “否”符号 ^

		// “^”符号称为“否”符号。如果用在方括号内，“^”表示不想要匹配的字符
		System.out.println("5) “否”符号  ^");
		// 匹配所有单词，但以“X”字母开头的单词除外
		//pattern = Pattern.compile("[^X][a-z]+");
		//matcher = pattern.matcher("Xa;   AA;aabbcc; dd;");
		
		pattern = Pattern.compile("[^XY][a-z]+");
		matcher = pattern.matcher("Xa;   AA; aabbcc; dd; Ycc;  Yc;  Ze;  ");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		//输出
		//5) “否”符号  ^
		//序号1:    aabbcc
		//序号2:    dd
		//序号3:   cc
		//序号4:   Ze
		
		
		System.out.println("-------------华丽的分界线---------------");

		// 6. 圆括号和空白符号

		System.out.println("6) 圆括号和空白符号");
		// pattern = Pattern.compile("[a-z]+ +[0-9]{1,2}, *[0-9]{4}");
		// pattern = Pattern.compile("[A-z]+ +[0-9]{1,2}, *[0-9]{4}");
		// //可以匹配大写或小写的字母
		pattern = Pattern.compile("([A-z]+) +[0-9]{1,2}, *[0-9]{4}"); // 月份值进行了
																		// 分组；
		// matcher = pattern.matcher("June 26, 1951;   July 27, 1979");
		matcher = pattern.matcher("June 26, 1951;   July 27, 1979");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		System.out.println("-------------华丽的分界线---------------");

		// 7. 其它符号

		System.out.println("7) 其它符号");
		// pattern = Pattern.compile("[0-9]{3}"); //3个数字
		// pattern = Pattern.compile("[^0-9]{3}"); //3个非数字
		// pattern = Pattern.compile("[A-z0-9]{3}"); //3个 字母数字
		// pattern = Pattern.compile("[A-Z0-9]{3}"); //3个 大写字母或数字
		pattern = Pattern.compile("[^A-Z0-9]{3}"); // 3个 非大写字母或数字
		matcher = pattern.matcher("JBB 2C6, 1951;   July 27, 1979");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		System.out.println("-------------华丽的分界线---------------");

		// 8. 解析ip地址
		System.out.println("8. 解析ip 地址");
		pattern = Pattern
				.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}"); // 1~3个数字
																			// 一个分段
		matcher = pattern.matcher("192.168.0.1; 33.55.66.77  192,168.33,2 ");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}
		
		//输出
		//序号1:   192.168.0.1
		//序号2:   33.55.66.77

		System.out.println("-------------华丽的分界线---------------");

		// 9. html 解析（查询从 < 开始， 从 > 结束的 字符串）
		System.out.println("9. html 解析");

		// 正则样式的解释：
		// '<' + 0或若干个空格 + font + 0或若干个空格 + 非'>'的若干个字符 + 0或若干个空格 + '>'
		pattern = Pattern.compile("< *font *([^>]*) *>");
		matcher = pattern
				.matcher("<font face=\"Aria\" size=\"2\" color=\"red\"> ");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		System.out.println("-------------华丽的分界线---------------");

		// 10. 反斜杠

		System.out.println("10. 反斜杠");

		// pattern = Pattern.compile("[0-9]{3}");
		// pattern = Pattern.compile("[\\d]{3}"); //等价

		// pattern = Pattern.compile("[^0-9]{3}");
		// pattern = Pattern.compile("[\\D]{3}"); //等价

		// pattern = Pattern.compile("[a-zA-Z_0-9]{3}");
		// pattern = Pattern.compile("[\\w]{3}"); //等价

		// pattern = Pattern.compile("[^a-zA-Z_0-9]{3}");
		pattern = Pattern.compile("[\\W]{3}"); // 等价

		matcher = pattern.matcher("fff 333 444 5555 #%4 ,34f");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		System.out.println("-------------华丽的分界线---------------");

		// 11. 验证邮箱地址
		System.out.println("11. 验证邮箱地址");
		String mail = "cepon.li-n_e@yahoo.com.cn";

		// 至少1个字母数字.-_ + @ + ( 至少1个字母数字-_ + . )
		// 不区分大小写
		// TODO: \\. \\- 的具体含义
		pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",
				Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(mail);
		boolean isMail = matcher.matches();
		System.out.println("email:  " + mail + "      是邮箱吗:" + isMail);

		System.out.println("-------------华丽的分界线---------------");

		// 12. 去除 html 标签
		System.out.println("12. 去除 html 标签");
		// Pattern.DOTALL(?s) ：在这种模式下，表达式'.'可以匹配任意字符，包括表示一行的结束符。
		// 默认情况下，表达式'.'不匹配行的结束符。

		// < + 任意字符
		pattern = Pattern.compile("<.+?>", Pattern.DOTALL); // 查找 <x>
															// 的格式；（x至少为1个字符）
		// pattern = Pattern.compile("<.+>", Pattern.DOTALL); //注意 与 "<.+?>"
		// 的不同！！
		matcher = pattern.matcher("<a href=\"index.html\">主页</a>");

		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("序号" + order + ":   " + matchStr);
		}

		String html1 = matcher.replaceAll("");
		System.out.println(html1);

		System.out.println("-------------华丽的分界线---------------");

		// 13. 查找html中对应条件字符串
		System.out.println("13. 查找html中对应条件字符串");
		pattern = Pattern.compile("href=\"(.+?)\""); // 注意这种写法： (.+?)
		matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
		if (matcher.find()) {
			System.out.println("href= 的内容为：" + matcher.group(1));
		}

		System.out.println("-------------华丽的分界线---------------");

		// 14. 截取http://地址
		System.out.println(" 14. 截取http://地址");
		// http:// 或 https:// + 匹配至少1个的字母 . - _
		pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
		// matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
		matcher = pattern.matcher("dsdsds<http://dsds//gfgf.-_*fdfd>fdf");
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			buffer.append(matcher.group());
			buffer.append("\r\n");
			System.out.println(buffer.toString());
		}

		System.out.println("-------------华丽的分界线---------------");

		// 15. 替换指定{}中文字
		System.out.println(" 15. 替换指定{}中文字");
		String str15 = "Java目前的发展史是由{0}年-{1}年";
		String[][] object = { new String[] { "\\{0\\}", "1995" },
				new String[] { "\\{1\\}", "2007" } };
		System.out.println(replace(str15, object));

		System.out.println("-------------华丽的分界线---------------");

		// 16. 查找以Java开头,任意结尾的字符串
		System.out.println(" 16. 查找以Java开头,任意结尾的字符串");
		// 查找以Java开头,任意结尾的字符串
		pattern = Pattern.compile("^Java.*");
		matcher = pattern.matcher("Java不是人");
		boolean b = matcher.matches();
		// 当条件满足时，将返回true，否则返回false
		System.out.println(b);

		System.out.println("-------------华丽的分界线---------------");

		// 17. 以多条件分割字符串时（很重要!）
		System.out.println(" 17. 以多条件分割字符串时（很重要!）");
		// 构造切割符号
		// 1个或多个 , 空格 | 作为切割符
		pattern = Pattern.compile("[, |]+");
		String[] strs = pattern
				.split("Java Hello World  Java,Hello,,World|Sun");
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				System.out.println(strs[i]);
			}
		}

		System.out.println("-------------华丽的分界线---------------");

		// 18. 文字替换（首次出现字符）
		System.out.println(" 18. 文字替换（首次出现字符）");
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		// 替换第一个符合正则的数据
		System.out.println(matcher.replaceFirst("Java"));

		System.out.println("-------------华丽的分界线---------------");

		// 19. 文字替换（全部）
		System.out.println(" 19. 文字替换（全部）");
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		// 替换第一个符合正则的数据
		System.out.println(matcher.replaceAll("Java"));

		System.out.println("-------------华丽的分界线---------------");

		// 20. 文字替换（置换字符） - (与 StringBuffer 结合使用！)
		System.out.println(" 20. 文字替换（置换字符） - (与 StringBuffer 结合使用！)");
		pattern = Pattern.compile("正则表达式");
		matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());

		System.out.println("-------------华丽的分界线---------------");
		
		
		// 21. 统计一个字符串中出现另外一个子字符串的次数
		System.out.println(" 21. 统计一个字符串中出现另外一个子字符串的次数");
		int leftBracketNum  = countSubStrNum(" (  2  , 3 , 22 ) , ( -1 , 5 , -5 ) ", "[\\(]{1}");
		int rightBracketNum = countSubStrNum(" (  2  , 3 , 22 ) , ( -1 , 5 , -5 ) ", "[\\(]{1}");
		
		// 22. 一定规则的字符串解析和验证   (2 , 3 , 22 ) , ( -1 , 5 , -5 )
		System.out.println("  22. 一定规则的字符串解析和验证   (2 , 3 , 22 ) , ( -1 , 5 , -5 )");
		// src：    (2 , 3 , 22 ) , ( -1 , 5 , -5 )
		//解析结果：   (2 , 3 , 22 )      ( -1 , 5 , -5 )
		testparseStrWithRegex01();
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 23. 一定规则的字符串解析和验证  "..." , "..."
		System.out.println("  23. 一定规则的字符串解析和验证  \"...\" , \"...\"");
		testparseStrWithRegex02();
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 24. Pattern.matches() 基本示例
		System.out.println("24. Pattern.matches() 基本示例");
		//注意， 如果对 输入字符串做任何修改， 返回就是false了。
		String data24 = "java";
		boolean result24 = Pattern.matches("java",data24); // matches(String regex， CharSequence input)
		System.out.println(result24); //true
	
		System.out.println("-------------华丽的分界线---------------");
		
		
		// 25.  圆括号分组 
		System.out.println("25.  圆括号分组 ");
		//模式是”m(o+)n”，它表示 mn 中间的 o 可以重复一次或多次，
		//因此 moon，mon，mooon 能匹配成功，而 mono 在n 后多了一个 o，和模式匹配不上.
		//注: +表示一次或多次；?表示 0 次或一次；*表示 0 次或多次
				
		String[] dataArr25 = { "moon", "mon", "moon", "mono" };
		for (String str1 : dataArr25) {
			String patternStr = "m(o+)n";
			boolean result = Pattern.matches(patternStr, str1);
			if (result) {
				System.out.println("字符串" + str1 + "匹配模式" + patternStr + "成功");
			} else {
				System.out.println("字符串" + str1 + "匹配模式" + patternStr + "失败");
			}
		}
		
		//输出
		//字符串moon匹配模式m(o+)n成功
		//字符串mon匹配模式m(o+)n成功
		//字符串moon匹配模式m(o+)n成功
		//字符串mono匹配模式m(o+)n失败
		System.out.println("-------------华丽的分界线---------------");
		
		// 26.  \d 示例
		System.out.println(" 26.  \\d 示例");
		String[] dataArr26 = { "1", "10", "101", "1010", "100+" };
		for (String str26 : dataArr26) {
			String patternStr = "\\d+";   
			//String patternStr = "[0-9]+";  等价
			boolean result = Pattern.matches(patternStr, str26);
			if (result) {
				System.out.println("字符串" + str26 + "匹配模式" + patternStr + "成功");
			} else {
				System.out.println("字符串" + str26 + "匹配模式" + patternStr + "失败");
			}
		}
		
		//输出
		//字符串1匹配模式\d+成功
		//字符串10匹配模式\d+成功
		//字符串101匹配模式\d+成功
		//字符串1010匹配模式\d+成功
		//字符串100+匹配模式\d+失败
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 27.  \w 示例
		System.out.println("  27.  \\w 示例");
		String[] dataArr27 = { "a100", "b20", "Ac30", "df10000", "gh0t","11333" };
		for (String str : dataArr27) {
			String patternStr = "\\w+\\d+";
			//String patternStr = "[a-zA-Z0-9]+[0-9]+";   //等价
			boolean result = Pattern.matches(patternStr, str);
			if (result) {
				System.out.println("字符串" + str + "匹配模式" + patternStr + "成功");
			} else {
				System.out.println("字符串" + str + "匹配模式" + patternStr + "失败");
			}
		}
		
		//输出
		//字符串a100匹配模式\w+\d+成功
		//字符串b20匹配模式\w+\d+成功
		//字符串Ac30匹配模式\w+\d+成功
		//字符串df10000匹配模式\w+\d+成功
		//字符串gh0t匹配模式\w+\d+失败
		//字符串11333匹配模式\w+\d+成功
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 28.  正则 切割字符串
		System.out.println("  28.  正则 切割字符串");
		String str28 = "薪水,,职位 姓名;年龄	性别";
		String[] dataArr28 = str28.split("[,\\s;]");   //      \\s   表示空白字符（space， TAB 等）
		for (String strTmp : dataArr28) {
			System.out.println(strTmp);
		}
		//输出
		//薪水
        //
		//职位
		//姓名
		//年龄
		//性别
		System.out.println("-------------华丽的分界线---------------");
		
		// 29.  使用Pattern的 split()函数来 切割字符串
		System.out.println(" 29.  使用Pattern的 split()函数来 切割字符串");
		String str29 = "2007年12月11日";
		Pattern p29 = Pattern.compile("[年月日]");
		String[] dataArr29 = p29.split(str29);
		for (String strTmp : dataArr29) {
			System.out.println(strTmp);
		}
		
		//输出
		//2007
		//12
		//11
		System.out.println("-------------华丽的分界线---------------");
		
		
		// 30. 通过正则进行replaceAll 操作。
		System.out.println(" 30. 通过正则进行replaceAll 操作。");
		//模式“(\d+)(元|人民币|RMB)”按括号分成了两组，第一组\d+匹配单个或多个数字，第二组匹配元，人民
		//币，RMB 中的任意一个，替换部分表示第一个组匹配的部分不变($1 表示前面的第一组)，其余组替换成￥.
		String str = "10元 1000人民币 10000元 100000RMB";
		String str1 = str.replaceAll("(\\d+)(元|人民币|RMB)","$1￥");
		System.out.println(str1);
		
		String str2 = str.replaceAll("(\\d+)(元|人民币|RMB)", "￥$2");
		System.out.println(str2);
		
		//输出
		//10￥ 1000￥ 10000￥ 100000￥
		//￥元 ￥人民币 ￥元 ￥RMB
		System.out.println("-------------华丽的分界线---------------");
		
		
		// 31. appendReplacement demo,  用处不太大。。
		System.out.println("  31. appendReplacement demo,  用处不太大。。");
		Pattern p31 = Pattern.compile("m(o+)n", Pattern.CASE_INSENSITIVE);
		// 用Pattern类的matcher()方法生成一个Matcher对象
		Matcher m31 = p31.matcher("moon mooon Mon mooooon Mooon");
		StringBuffer sb31 = new StringBuffer();
		// 使用find()方法查找第一个匹配的对象
		boolean result31 = m31.find();
		// 使用循环找出模式匹配的内容替换之，再将内容加到sb里
		while (result31) {
			// appendReplacement(StringBuffer sb ，String replacement)
			// 实现非终端添加和替换步骤
			m31.appendReplacement(sb31, "moon12BYJ");
			result31 = m31.find();
		}
		// 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
		m31.appendTail(sb31);
		System.out.println("替换后内容是" + sb31.toString());
		
		//输出
		//替换后内容是moon12BYJ moon12BYJ moon12BYJ moon12BYJ moon12BYJ
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 32. 精准匹配次数demo  {3-5}
		System.out.println("  32. 精准匹配次数demo  {3-5}");
		
		//用{}来指定精确指定出现的次数，
		//X{2,5}表示 X 最少出现 2 次，最多出现 5 次;
		//X{2,}表示 X 最少出现 2 次，多则不限;
		//X{5}表示 X 只精确的出现 5 次
		String[] dataArr32 = { "google", "gooogle", "gooooogle", "goooooogle", "ggle" };
		for (String str32 : dataArr32) {
			String patternStr32 = "g(o{2,5})gle";
			boolean result32 = Pattern.matches(patternStr32, str32);
			if (result32) {
				System.out.println("字符串" + str32 + "匹配模式" + patternStr32 + "成功");
			} else {
				System.out.println("字符串" + str32 + "匹配模式" + patternStr32 + "失败");
			}
		}
		
		//输出
		//字符串google匹配模式g(o{2,5})gle成功
		//字符串gooogle匹配模式g(o{2,5})gle成功
		//字符串gooooogle匹配模式g(o{2,5})gle成功
		//字符串goooooogle匹配模式g(o{2,5})gle失败
		//字符串ggle匹配模式g(o{2,5})gle失败
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 33. 方括号 和 -  完成 范围过滤
		System.out.println("  33. 方括号 和 -  完成 范围过滤");
		// -表示从..到…，如[a-c]等同于[abc]
		String[] dataArr33 = { "Tan", "Tbn", "Tcn", "Ton", "Twn" };
		for (String str33 : dataArr33) {
			String regex33 = "T[a-c]n";
			boolean result33 = Pattern.matches(regex33, str33);
			if (result33) {
				System.out.println("字符串" + str33 + "匹配模式" + regex33 + "成功");
			} else {
				System.out.println("字符串" + str33 + "匹配模式" + regex33 + "失败");
			}
		}
		
		//输出
		//字符串Tan匹配模式T[a-c]n成功
		//字符串Tbn匹配模式T[a-c]n成功
		//字符串Tcn匹配模式T[a-c]n成功
		//字符串Ton匹配模式T[a-c]n失败
		//字符串Twn匹配模式T[a-c]n失败
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 34. 大小写不敏感 Pattern.CASE_INSENSITIVE
		System.out.println("  34. 大小写不敏感 Pattern.CASE_INSENSITIVE");
		String patternStr34 = "ab";
		Pattern pattern34 = Pattern.compile(patternStr34, Pattern.CASE_INSENSITIVE);
		String[] dataArr34 = { "ab", "Ab", "AB" ,"aB1"};
		for (String str34 : dataArr34) {
			Matcher matcher34 = pattern34.matcher(str34);
			if (matcher34.find()) {
				System.out.println("字符串" + str34 + "匹配模式" + patternStr34 + "成功");
			}
		}
		
		//输出
		//字符串ab匹配模式ab成功
		//字符串Ab匹配模式ab成功
		//字符串AB匹配模式ab成功
		//字符串aB1匹配模式ab成功
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 35. 使用正则表达式分割字符串
		System.out.println("  35. 使用正则表达式分割字符串");
		String input35 = "职务=GM 薪水=50000 , 姓名=职业经理人 ; 性别=男 年龄=45 ";
		//注意这里要把复杂的模式写在前面，否则简单模式会先匹配上
		//三种切割模式：     空格,空格   空格;空格  空格    
		String patternStr35 = "(\\s*,\\s*)|(\\s*;\\s*)|(\\s+)";
		Pattern pattern35 = Pattern.compile(patternStr35);
		String[] dataArr35 = pattern35.split(input35);
		for (String str35 : dataArr35) {
			System.out.println(str35);
		}
		
		//输出
		//职务=GM
		//薪水=50000
		//姓名=职业经理人
		//性别=男
		//年龄=45
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 36. 高级正则匹配 <name>Bill</name><salary>50000</salary>
		System.out.println(" 36. 高级正则匹配 <name>Bill</name><salary>50000</salary>");
		
		//  \w 单独字符 [a-zA-Z0-9]      
		//  \1 对应第一个小括号括起来的 group1
		String regex36 = "<(\\w+)>(\\w+)</\\1>";
		Pattern pattern36 = Pattern.compile(regex36);
		String input36 = "<name>Bill</name><salary>50000</salary><title>GM</title><age>33</ag>";
		Matcher matcher36 = pattern36.matcher(input36);
		while (matcher36.find()) {
			System.out.println("match item str:"+matcher36.group()+"       name:"+matcher36.group(1)+ "  value:"+matcher36.group(2));
		}
		
		//输出
		//match item str:<name>Bill</name>       name:name  value:Bill
		//match item str:<salary>50000</salary>       name:salary  value:50000
		//match item str:<title>GM</title>       name:title  value:GM
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 37. 将单词数字混合的字符串的单词部分大写,  感觉用处不太大
		System.out.println(" 37. 将单词数字混合的字符串的单词部分大写,  感觉用处不太大");
		String regex37 = "([a-zA-Z]+[0-9]+)";
		Pattern pattern37 = Pattern.compile(regex37);
		String input37 = "age45 salary500000 50000 title";
		Matcher matcher37 = pattern37.matcher(input37);
		StringBuffer sb37 = new StringBuffer();
		while (matcher37.find()) {
			String replacement37 = matcher37.group(1).toUpperCase();
			matcher37.appendReplacement(sb37, replacement37);
		}
		matcher37.appendTail(sb37);
		System.out.println("替换完的字串为" + sb37.toString());
		
		//输出
		//替换完的字串为AGE45 SALARY500000 50000 title
		
		System.out.println("-------------华丽的分界线---------------");
		
		
		// 38. 匹配 [...]  分组
		System.out.println(" 38. 匹配 [...]  分组");
		Pattern p38 = Pattern.compile("\\[([^\\]]+)\\]");
		//      \\[                 ([^\\]]+)                          \\]
		//   匹配一个[        分组：至少匹配一个非]      匹配一个]
		Matcher m38 = p38.matcher(" [2]  []  [1,3] []]  [[]");
		while(m38.find()){
			String str38 = m38.group();
			System.out.println(str38);
		}
		
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 39. 正则综合运用（使用分组，解析信息以分组方式展现）
		System.out.println(" 39. 正则综合运用（使用分组，解析信息以分组方式展现）");
		String logEntry = "172.26.155.241 - - [26/Feb/2001:10:56:03 -0500] \"GET /IsAlive.htm HTTP/1.0\" 200 15 ";
		final String IP_SEGMENT_NUM_REGEX = "[0-9]{1,3}";
		final String IP_SEGMENT_SEPARATOR_REGEX  = "\\.";
		final String IP_REGEX =   IP_SEGMENT_NUM_REGEX+IP_SEGMENT_SEPARATOR_REGEX+
												IP_SEGMENT_NUM_REGEX+IP_SEGMENT_SEPARATOR_REGEX+
												IP_SEGMENT_NUM_REGEX+IP_SEGMENT_SEPARATOR_REGEX+
												IP_SEGMENT_NUM_REGEX;
		
		//注意是  [0-9]{1,3}    而不是  [0-9]{1-3}
		//Exception in thread "main" java.util.regex.PatternSyntaxException: Unclosed counted closure near index 8
		//([0-9]{1-3}\.[0-9]{1-3}\.[0-9]{1-3}\.[0-9]{1-3})\s-\s-\s\[([^\]]+)\]
		
		final String LEFT_SQUARE_BRACKET_REGEX = "\\[";
		final String RIGHT_SQUARE_BRACKET_REGEX = "\\]";
		final String NOT_RIGHT_SQUARE_BRACKET_AT_LEAST_ONE_REGEX = "[^\\]]+";
		String regexp39 = "("+IP_REGEX+")"+
									"\\s-\\s-\\s"+
									LEFT_SQUARE_BRACKET_REGEX+
									"("+NOT_RIGHT_SQUARE_BRACKET_AT_LEAST_ONE_REGEX+")"+
									RIGHT_SQUARE_BRACKET_REGEX;
		
		Pattern p39 = Pattern.compile(regexp39);
		Matcher m39 = p39.matcher(logEntry);
		while(m39.find()){
			System.out.println("group1:  "+m39.group(1)+"   ; group2:   "+m39.group(2));
		}
		
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 40. html解析 （分两层进行正则解析， 非常不错的例子)
		System.out.println("40. html解析 （分两层进行正则解析， 非常不错的例子)");
		String input40  = "<font face =\"Arial,Serif\" size=  \"+2\" color=\"re d\">";
		System.out.println("input40:   "+input40);
		
		//这种方式不对，要先把 左尖括号，右尖括号 及 中间的东西 匹配出来， 再逐步匹配里面的东西
		//Pattern p40 = Pattern.compile("([\\w]+)=\"([\\w]+)\"");
		//Matcher m40 = p40.matcher(input40);
		//while(m40.find()){
		//	System.out.println("name:  "+m40.group(1) +"    value:  "+m40.group(2));
		// }
		
		Pattern p40 = Pattern.compile("<\\s*font\\s+([^>]*)\\s*>");
		//             <                 \\s*        font             \\s+                   ([^>]*)                 \\s*             >
		//          左尖括号        若干空格  特定字符串    至少一个空格    任意个非右尖括号  若干空格    右尖括号
		Matcher m40 = p40.matcher(input40);
		while(m40.find()){
			
			String input40Sub = m40.group(1) ;
			System.out.println("group(1):  "+input40Sub);
			
			Pattern p40sub = Pattern.compile("([a-zA-Z]+)\\s*=\\s*\"([^\"]+)\"");
			//         ([a-zA-Z]+)        \\s*          =              \\s*          \"              ([^\"]+)                  \"
			//       1个或多个字母     若干空格  特定字符    若干空格   双引号      1或多个非双引号     双引号   
			System.out.println("子循环");
			Matcher m40sub = p40sub.matcher(input40Sub);
			while(m40sub.find()){
				
				System.out.println("group(1):  "+m40sub.group(1)+"    group(2):   "+m40sub.group(2));
			}
		}
		System.out.println("-------------华丽的分界线---------------");
		
		// 41. 通过正则匹配http链接
		System.out.println("41. 通过正则匹配http链接");
		String link41="<a href = \"http://widgets.acme.com/interface.html#How_To_Trade\">";
		//String targetLink41="<a href = \"http://newserver.acme.com/interface.html#How_To_Trade\">";
		System.out.println("link41:    "+link41);
		
		Pattern p41 = Pattern.compile("<\\s*a\\s+href\\s*=\\s*\"http://(widgets.acme.com)/interface.html#How_To_Trade\"\\s*>\\s*");
		Matcher m41 = p41.matcher(link41);
		
		StringBuffer sb41 = new StringBuffer();
		while(m41.find()){
			System.out.println("find:   "+m41.group());
			System.out.println("group(1):  "+m41.group(1));
		}
		System.out.println(sb41.toString());
		System.out.println("-------------华丽的分界线---------------");
		
		// 42. 通过正则更新字符串
		System.out.println("42. 通过正则更新字符串");
		
		//被替换关键字的的数据源
		Map<String,String> tokens42 = new HashMap<String,String>();
		tokens42.put("cat", "Garfield");
		tokens42.put("beverage", "coffee");
		
		String template42 = "${cat} really needs some ${beverage}.";
		//生成匹配模式的正则表达式
		//String patternString42 = "\\$\\{(" + StringUtils.join(tokens42.keySet(), "|") + ")\\}";
		String patternString42 = "\\$\\{(cat|beverage)\\}";
		
		Pattern pattern42 = Pattern.compile(patternString42);
		Matcher matcher42 = pattern42.matcher(template42);

		//两个方法：appendReplacement, appendTail
		StringBuffer sb42 = new StringBuffer();
		while(matcher42.find()) {
			System.out.println("group(0):  "+matcher42.group(0));
			System.out.println("group(1):  "+matcher42.group(1));
		    matcher42.appendReplacement(sb42, tokens42.get(matcher42.group(1)));
		}
		matcher42.appendTail(sb42);

		//out: Garfield really needs some coffee.
		//注意：最终是把整个个 group(0) 替换了。
		System.out.println(sb42.toString());
		
		
		System.out.println("-------------华丽的分界线---------------");
		
		// 43. appendReplacement&appendTail demo（javadoc）
		System.out.println(" 43. appendReplacement&appendTail demo（javadoc）");
		
		 Pattern p43 = Pattern.compile("cat");
		 Matcher m43 = p43.matcher("one cat two cats in the yard");
		 StringBuffer sb43 = new StringBuffer();
		 while (m43.find()) {
		     m43.appendReplacement(sb43, "dog");
		     System.out.println("m43.group():  "+m43.group());
		 }
		 m43.appendTail(sb43);
		 System.out.println(sb43.toString());
		 
		 System.out.println("-------------华丽的分界线---------------");
		 
		 //正则中的数量词有 Greedy (贪婪)、Reluctant(懒惰)和 Possessive(强占)三种. 
		 
		 // 44. 数量词- 贪婪(Greedy) 示例
		 System.out.println("44. 数量词- 贪婪(Greedy) 示例");
		 //Greedy  数量词
		 //X?  X，一次或一次也没有
		 //X*  X，零次或多次
		 //X+  X，一次或多次
		 //X{n}  X，恰好 n 次
		 //X{n,}  X，至少 n 次
		 //X{n,m}  X，至少 n 次，但是不超过 m 次
		 //Greedy 是最常用的，它的匹配方式是先把整个字符串吞下，然后匹配整个字符串，如果不匹配，就
		 //从右端吐出一个字符，再进行匹配，直到找到匹配或把整个字符串吐完为止
		 
		Matcher m44 = Pattern.compile("a.*b").matcher("a====b=========b=====");
		while (m44.find()) {
			System.out.println(m44.group());
		}
		 
		 
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 45. 数量词- Reluctant(懒惰)  示例
		 System.out.println("45. 数量词- Reluctant(懒惰)  示例");
		 //勉强的，不情愿的。
		 //Reluctant  数量词
		 //X??  X，一次或一次也没有
		 //X*?  X，零次或多次
		 //X+?  X，一次或多次
		 //X{n}?  X，恰好 n 次
		 //X{n,}?  X，至少 n 次
		 //X{n,m}?  X，至少 n 次，但是不超过 m 次
		 //Reluctant 正好和 Greedy 相反，它先从最小匹配开始，先从左端吞入一个字符，然后进行匹配，若不
		 //匹配就再吞入一个字符，直到找到匹配或将整个字符串吞入为止。
		 //因为总是从最小匹配开始，故称 懒惰
		Matcher m45 = Pattern.compile("a.*?b").matcher("a====b=========b=====");
		while (m45.find()) {
			System.out.println(m45.group());
		}
		 
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 46. 数量词- Possessive(强占) 示例
		 System.out.println("46. 数量词- Possessive(强占) 示例");
		 //Possessive  数量词
		 //X?+  X，一次或一次也没有
		 //X*+  X，零次或多次
		 //X++  X，一次或多次
		 //X{n}+  X，恰好 n 次
		 //X{n,}+  X，至少 n 次
		 //X{n,m}+  X，至少 n 次，但是不超过 m 次
		 //Possessive 和 Greedy 的匹配方式一样，先把整个字符串吞下，然后匹配整个字符串，如果匹配，就
		 //认为匹配，如果不匹配，就认为整个字符串不匹配， 它不会从右端吐出一个字符串再进行匹配，只进行一次
		 //因为贪婪但并不聪明，故称 强占
		 
		Matcher m46 = Pattern.compile("a.*+b").matcher("a====b=========b=====");
		while (m46.find()) {
			System.out.println(m46.group());
		}
		 
		//无任何输出
		 
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 47. 捕获组示例 (其实前面已经用到了很多次了)
		 //正则表达式中每个"()"内的部分算作一个捕获组，每个捕获组都有一个编号，从1,2...，编号0代表整个匹配到的内容。
		 System.out.println("47. 捕获组示例");
		String text47 = "<textarea rows=\"20\" cols=\"70\">nexus maven</textarea>";
		//String reg47 = "<textarea.*?>.*?</textarea>";
		//下面的正则表达式中共有四个捕获组：(<textarea.*?>)、(.*?)、(</textarea>)和整个匹配到的内容  
		String reg47 = "(<textarea.*?>)(.*?)(</textarea>)";   
		Pattern p47 = Pattern.compile(reg47);
		Matcher m47 = p47.matcher(text47);
		while (m47.find()) {
			System.out.println(m47.group(0)); // 整个匹配到的内容
			System.out.println(m47.group(1)); // (<textarea.*?>)
			System.out.println(m47.group(2)); // (.*?)
			System.out.println(m47.group(3)); // (</textarea>)
		}
		 System.out.println("-------------华丽的分界线---------------");
		 
		 
		 // 48. 非捕获组示例 (?:xxxx)
		 // 只分组而不捕获
		System.out.println("48. 非捕获组示例 ");
		String text48 = "<textarea rows=\"20\" cols=\"70\">nexus maven</textarea>";
		// String reg48 = "(<textarea.*?>)(.*?)(</textarea>)"; 全捕获组

		// 下面的正则表达式中共有二个捕获组：(.*?)和整个匹配到的内容，
		// 两个非捕获组:(?:</textarea>)和(?:<textarea.*?>)
		String reg48 = "(?:<textarea.*?>)(.*?)(?:</textarea>)";
		Pattern p48 = Pattern.compile(reg48);
		Matcher m48 = p48.matcher(text48);
		while (m48.find()) {
			System.out.println(m48.group(0)); // 整个匹配到的内容
			System.out.println(m48.group(1)); // (.*?)
			
			//System.out.println(m48.group(2));  //Exception ... No group 2
		}
			
		 System.out.println("-------------华丽的分界线---------------");
	
		 // 49. 以xx结束的用法  xx$
		 System.out.println("49. 以xx结束的用法  xx$");
		Pattern p49 = Pattern.compile("(\\d+)([￥$])$");  //最后一个 $ 表示以某一组字符结束，这里就是以 ￥$ 其中一个结束
		String str49 = "8899￥";
		Matcher m49 = p49.matcher(str49);
		if (m49.matches()) {
			System.out.println("货币金额: " + m49.group(1));
			System.out.println("货币种类: " + m49.group(2));
		}
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 50. 非捕获组示例2   非捕获组(?:)
		 System.out.println("50. 非捕获组示例2   非捕获组(?:)");
		Pattern p50 = Pattern.compile("(\\d+)(?:\\.?)(?:\\d+)([￥$])$");
		String str50 = "8899.56￥";
		Matcher m50 = p50.matcher(str50);
		if (m50.matches()) {
			System.out.println("货币:"+m50.group());
			System.out.println("货币金额: " + m50.group(1));
			System.out.println("货币种类: " + m50.group(2));
		}
		 
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 51. 非捕获组示例3 -    (?=X)  零宽度正先行断言
		 System.out.println(" 51. 非捕获组示例3 -    (?=X)  零宽度正先行断言");
		 //匹配这么一个字符串，它要满足：是两位字符（数字或字母），且后面紧跟着两个 a。
		Pattern p51 = Pattern.compile("[0-9a-z]{2}(?=aa)");
		String str51 = "12332aa438aaf";
		Matcher m51 = p51.matcher(str51);
		while (m51.find()) {
			System.out.println(m51.group());
		}
		
		//32aa 这个子串满足这个条件，所以可以匹配到，又因为 (?=) 的部分是不捕获的，所以输出的只是 32，不包括aa。
		//同理 38aa 也匹配这个正则，而输出仅是 38
		//再深入看一下:
		//当 str 第一次匹配成功输出 32 后，程序要继续向后查找是否还有匹配的其它子串。那么这时应该从 32aa 的后
		//一位开始向后查找，还是从 32 的后一位呢？也就是从索引 5 开始还是从 7 开始呢？有人可能想到是从 32aa 的下
		//一位开始往后找，因为 32aa 匹配了正则表达式，所以下一位当然是它的后面也就是从 4 开始。但实际上是从 32 的
		//后一位也就是第一个 a 开始往后找。原因还是 (?=) 是非捕获的。
		//查阅 API 文档是这么注释的：
		//(?=X) X, via zero- - width positive lookahead
		//可见 zero-width（零宽度）说的就是这个意思。
		
		//输出
		//32
		//38
		
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 52. 非捕获组示例4 -    (?=X)  零宽度正先行断言, 继续查找位置验证
		System.out.println("52. 非捕获组示例4 -    (?=X)  零宽度正先行断言, 继续查找位置验证");
		Pattern p52 = Pattern.compile("[0-9a-z]{2}(?=aa)");
		String str52 = "aaaaaaaa";
		Matcher m52 = p52.matcher(str52);
		while (m52.find()) {
			System.out.println(m52.group());
		}
		 
		//看一下它的输出： aa aa aa
		//分析一下：
		//这个字符串一共有 8 个 a。
		//第一次匹配比较容易找到，那就是前四个：aaaa ,当然第三和第四个 a 是不捕获的，所以输出是第一和第二个 a；
		//接着继续查找，这时是从第三个 a 开始，三到六，这 4 个 a 区配到了，所以输出第三和第四个 a；
		//接着继续查找，这时是从第五个 a 开始，五到八，这 4 个 a 区配到了，所以输出第五和第六个 a；
		//接着往后查找，这时是从第七个 a 开始，显然，第七和第八个 a,不满足正则表达式的匹配条件，查找结束。
		
		//输出
		//aa
		//aa
		//aa	
		
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 53. 非捕获组示例5 -    (?=X) 
		Pattern p53 = Pattern.compile("(?=hopeful)hope");
		String str53 = "hopeful";
		Matcher m53 = p53.matcher(str53);
		while (m53.find()) {
			System.out.println(m53.group());
		}
		 
		//正则表达式的意思是：是否能匹配 hopeful,如果能，则捕获 hopeful 中的 hope。当然继续向后查找匹配的子串，
		//是从 f 开始。
		
		 System.out.println("-------------华丽的分界线---------------");
		 
		 // 54. 非捕获组示例6 -    (?<=X) 
		Pattern p54 = Pattern.compile("(?<=aa)[0-9a-z]{2}");
		String str54 = "12332aa438aaf";
		Matcher m54 = p54.matcher(str54);
		while (m54.find()) {
			System.out.println(m54.group());
		}

		// 这个正则表达式的意思是：匹配这么一个字符串，它要满足：是两位字符（数字或字母），且前面紧跟的是两
		// 个字母 a 。

		// 输出
		// 43
		System.out.println("-------------华丽的分界线---------------");
		
		
		// 55. 匹配n次以上, {n,}
		String str55 = "abc-def cc----ff";
		String[] arr55 = str55.split("([^a-zA-Z\\-])|([\\-]{2,})");
		System.out.println("arr:"+U.array2Str(arr55)); //arr:{abc-def,cc,ff}
		
		str55 = "abc--def cc----ff";
		arr55 = str55.split("([^a-zA-Z\\-])|([\\-]{2,3})");
		System.out.println("arr:"+U.array2Str(arr55)); //arr:{abc,def,cc,-ff}
		
		
		// 56. 调试中。。。
		
		//这一句话，真心没有看懂是啥意思。。。
		//Pattern p56 = Pattern.compile("^\\s+|\\s+$");
		
		//Pattern p56 = Pattern.compile("\\s+");
		
		Pattern p56 = Pattern.compile("^[0-9]+");
		
		//Pattern p56 = Pattern.compile("[0-9]+");
		//final static Pattern ws2 = Pattern.compile("\\s+");
		
		String str56 = "cc125aa444yy";
		Matcher m56 = p56.matcher(str56);
		while (m56.find()) {
			System.out.println(m56.group());
		}
		
		
		//57. 将 \  替换为 /
		 String s1 = "huawei\\device\\TestCase002.java";
	        String  s2 = s1.replaceAll("\\\\","/");
	        U.err("s1:"+s1);
	        U.err("s2:"+s2);
		
	}
	
	
	
	
	
	private static void testparseStrWithRegex02() {
		//          ("aa;116,39","bb;116,39"):("cc;116,39","dd;116,39","ee;116,39")
		//   "..."  按照两个双引号中间的规则进行分割
		String s = "(\"海淀黄庄;116.3,39.97\",\"知春里;116.3,39.9\",\"知春路;116.3,39.9\"):(\"惠新西街南口;116.4,39.9\",\"和平西桥;116.4,39.9\",\"和平里北街;116.4,39.9\")";
		//正则匹配规则： 先找一个",   然后找可选个非"  ,  最后再找一个" 
		String regex = "[\"][^\"]*[\"]";
		//System.out.println(regex);
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);
		while (m.find()) {
			String ss = m.group();
			System.out.println(ss);
		}
	}
	
	public static void testparseStrWithRegex01(){
		String src="";
		List<String> list = new ArrayList<String>();
		boolean bRtn=false;
		
		//场景1：正常解析
		src = "  (2 , 3 , 22 ) , ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//场景2： 大括号中不是3个数字
		src = "  (2 , 3  ) , ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//场景3： 负数中间有空格
		src = "  (2 , 3 , 22 ) , ( - 1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//场景4：多余的括号
		src = "  (2 , 3 , (22 ) , ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//场景5：两组数字的 分割字符不对 ，正常应该是  ),(
		src = "  (2 , 3 , 22 ) ; ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		src = "  (2 , 3 , 22 ) ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//场景6： 有多余的字符
		src = "  (2 , 3 , 22 ),a( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		src = "  (2 , 3 , 22a ),( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
	}
	
	// src：    (2 , 3 , 22 ) , ( -1 , 5 , -5 )
	//解析结果：   (2 , 3 , 22 )      ( -1 , 5 , -5 )
	public static boolean parseStrWithRegex01(String src, List<String> list){
		list.clear();
		
		Pattern p1 = Pattern.compile("[\\(][ ]*[-]?[0-9]+[ ]*[,][ ]*[-]?[0-9]+[ ]*[,][ ]*[-]?[0-9]+[ ]*[\\)]");
		//             
		Matcher m1 = p1.matcher(src);
		while(m1.find()){
			String element = m1.group();
			list.add(element);
			System.out.println(element);
		}
		
		if(list.size()==0){
			U.err("parseStrWithRegex01->list.size()==0,  return false");
			return false;
		}

		int leftBracketNum  = countSubStrNum(src, "[\\(]{1}");
		int rightBracketNum = countSubStrNum(src, "[\\)]{1}");
		if(leftBracketNum!=rightBracketNum){
			U.err("parseStrWithRegex01->leftBracketNum!=rightBracketNum,  return false");
			return false;
		}
		
		if(list.size() != leftBracketNum){
			U.err("parseStrWithRegex01->list.size() != leftBracketNum,  return false");
			return false;
		}
		
		//   ),( 的数目 （支持空格字符）
		int leftCommaRightNum = countSubStrNum(src, "[\\)][ ]*[,][ ]*[\\(]");
		if(list.size() != (leftCommaRightNum+1)){
			U.err("parseStrWithRegex01->list.size() != (leftCommaRightNum+1),  return false; list.size:"+list.size()+" ;leftCommaRightNum:"+leftCommaRightNum);
			return false;
		}
		
		U.info("parseStrWithRegex01->list:"+list);
		return true;
	}
	
	public  static int countSubStrNum(String s, String regex){
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(s);
		int count=0;
		while(m1.find()){
			String element = m1.group();
			count++;
			System.out.println(element);
		}
		
		return count;
	}

	public static String replace(final String sourceString, Object[] object) {
		String temp = sourceString;
		for (int i = 0; i < object.length; i++) {
			String[] result = (String[]) object[i];
			Pattern pattern = Pattern.compile(result[0]);
			Matcher matcher = pattern.matcher(temp);
			temp = matcher.replaceAll(result[1]);
		}
		return temp;
	}
}

