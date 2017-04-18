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
		//Java ��������ʽ�Ĵ����������������ࣺ
		//java.util.regex.Pattern ģʽ�ࣺһ��������ʽ�������ı���ģʽ��������ʾһ���������������ʽ��
		//java.util.regex.Matcher ƥ���ࣺ��ģʽƥ��һ���ַ��������ĳ�������һ�� Matcher ������һ��״̬������
		//������ Pattern ������Ϊƥ��ģʽ���ַ���չ��ƥ���顣
		
		//------------ʾ���б�--------------
		// 1. ������ .
		// 2. �����ŷ��� []
		// 3. "��"���� |
		// 4. ��ʾƥ������ķ��� {5}
		// 5. ���񡱷��� ^
		// 6. Բ���źͿհ׷���
		// 7. ��������
		// 8. ����ip��ַ
		// 9. html ��������ѯ�� < ��ʼ�� �� > ������ �ַ�����
		// 10. ��б��
		// 11. ��֤�����ַ
		// 12. ȥ�� html ��ǩ
		// 13. ����html�ж�Ӧ�����ַ���
		// 14. ��ȡhttp://��ַ
		// 15. �滻ָ��{}������
		// 16. ������Java��ͷ,�����β���ַ���
		// 17. �Զ������ָ��ַ���ʱ������Ҫ!��
		// 18. �����滻���״γ����ַ���
		// 19. �����滻��ȫ����
		// 20. �����滻���û��ַ��� - (�� StringBuffer ���ʹ�ã�)

		// 21. ͳ��һ���ַ����г�������һ�����ַ����Ĵ���  2017/1/1
		// 22. һ��������ַ�����������֤   (2 , 3 , 22 ) , ( -1 , 5 , -5 )      2017/1/1
		// 23. һ��������ַ�����������֤  "..." , "..."      20111016,  2017/1/1
		// 24. Pattern.matches() ����ʾ��
		// 25.  Բ���ŷ��� 
		// 26.  \d ʾ��
		// 27.  \w ʾ��
		// 28.  ���� �и��ַ���
		// 29.  ʹ��Pattern�� split()������ �и��ַ���
		// 30. ͨ���������replaceAll ������
		// 31. appendReplacement demo,  �ô���̫�󡣡�
		// 32. ��׼ƥ�����demo  {3-5}
		// 33. ������ �� -  ��� ��Χ����
		// 34. ��Сд������ Pattern.CASE_INSENSITIVE
		// 35. ʹ��������ʽ�ָ��ַ���
		// 36. �߼�����ƥ�� <name>Bill</name><salary>50000</salary>
		// 37. ���������ֻ�ϵ��ַ����ĵ��ʲ��ִ�д,  �о��ô���̫��
		// 38. ƥ�� [...]  ����
		// 39. �����ۺ����ã�ʹ�÷��飬������Ϣ�Է��鷽ʽչ�֣�
		
		
		// Java����Ĺ��û��кܶ࣬��ʵ��ֻҪ���ַ�������û��������������������ڡ�
		// ��Ȼ���������ʱ�Ϻ�ʱ�������

		// ƥ������ķ���
		// * 0�λ���   ��֤������  ��0�Σ�1�Σ�n�Σ�
		// + 1�λ��� ��֤������
		// ? 0�λ�1�� ��֤������
		// {n} ǡ��n�� ��֤������
		// {n,m} ��n�ε�m��
		// {n,} ƥ��n�����ϣ���n�Σ�

		// ��б�ܹ���:
		// \t ��� ('\u0009')
		// \d ���� �ȼ���[0-9] ��֤������
		// \D ������ �ȼ���[^0-9] ��֤������
		// \s �հ׷��� [\t\n\x0B\f\r]
		// \S �ǿհ׷��� [^\t\n\x0B\f\r]
		// \w �����ַ� [a-zA-Z0-9]       ��֤��������
		// \W �ǵ����ַ� [^a-zA-Z_0-9]
		// \f ��ҳ��
		// \e Escape
		// \b һ�����ʵı߽�
		// \B һ���ǵ��ʵı߽�
		// \G ǰһ��ƥ��Ľ���
		
		

		int order = 0;
		Pattern pattern = null;
		Matcher matcher = null;

		// 1. ������ .
		// ������ƥ�������ַ��������ո�Tab�ַ��������з���

		// ƥ�� �ԡ�t����ĸ��ͷ���ԡ�n����ĸ������3���ַ���
		pattern = Pattern.compile("t.n");
		matcher = pattern.matcher("tan Ten  tin, t#n,,,tpn## txxn");
		order = 0;
		System.out.println("1) ������   . ƥ��");
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}
		
		
		//���
		//1) ������   . ƥ��
		//���1:   tan
		//���2:   tin
		//���3:   t#n
		//���4:   tpn

		System.out.println("-------------�����ķֽ���---------------");

		// 2. �����ŷ��� []
		// ֻ�з���������ָ�����ַ��Ų���ƥ��,������֮����ֻ��ƥ�䵥���ַ���

		System.out.println("2) �����ŷ���  [] ƥ��");
		pattern = Pattern.compile("t[aeiou]n");
		matcher = pattern.matcher("tan Ten  tin, t#n,,,tpn## txxn");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		//���
		//2) �����ŷ���  [] ƥ��
		//���1:   tan
		//���2:   tin
		
		System.out.println("-------------�����ķֽ���---------------");

		// 3. "��"���� |
		// ���ﲻ��ʹ�÷����ţ���Ϊ������ֻ����ƥ�䵥���ַ����������ʹ��Բ����()
		//Բ���ŵ��÷�һ�� ���з��飬 ���� "|" һ����� "��" ƥ�䡣
		
		//���������Ż��ֵ�������ʽ������ͨ������������顣��Ŵ� 0 ��ʼ���м���С���žͱ�
		//ʾ�м����飬���������Ƕ�ף����Ϊ 0 �ı�ʾ�������ʽ�����Ϊ 1 �ı�ʾ��һ���飬�������ơ�
		//���磺A(B)C(D)E ����ʽ�������飬�� 0 �� ABCDE���� 1 �� B���� 2 �� D��
		// A((B)C)(D)E ����ʽ�������飺�� 0 �� ABCDE���� 1 �� BC���� 2 �� B���� 3 �� C���� 4 �� D��
		
		//�� ���Է������ ��ȡ�ַ�����

		System.out.println("3) �����  | ƥ��");
		pattern = Pattern.compile("t(a|e|i|o|oo)n");
		matcher = pattern
				.matcher("tan Ten  tin, t#n,,,tpn## txxn, toon, tcsn;");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		//���
		//3) �����  | ƥ��
		//���1:   tan
		//���2:   tin
		//���3:   toon
		
		System.out.println("-------------�����ķֽ���---------------");

		// 4. ��ʾƥ������ķ��� {5}

		// ���� ��������ᰲȫ���롣�������ĸ�ʽ��999-99-9999
		// ���ͣ� [0-9]{3} ƥ��3��0-9 ������

		System.out.println("4) ��ʾƥ������ķ��� - ��������ᰲȫ����");
		
		//  -  ǰ���Ƿ�� ת���ַ�\  ,  ����Ӱ������Ч��
		//pattern = Pattern.compile("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}"); //��Ҫ- ����ƥ��
		pattern = Pattern.compile("[0-9]{3}-[0-9]{2}-[0-9]{4}"); //��Ҫ- ����ƥ��
		
		//   pattern = Pattern.compile("[0-9]{3}-?[0-9]{2}-?[0-9]{4}"); // ���� - ������ƥ��
		matcher = pattern
				.matcher("123-44-5788;  1333-44-5555; 123-55-88;123457899; 123-448888");
		
		// matcher =pattern.matcher("123-44-5788-22-3333;  1333-44-5555; 123-55-88");
		// //ֻ��ƥ�� 123-44-5788

		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		//���
		//4) ��ʾƥ������ķ��� - ��������ᰲȫ����
		//���1:   123-44-5788
		//���2:   333-44-5555
		
		System.out.println("-------------�����ķֽ���---------------");

		// �����������յ�һ�ָ�ʽ�ĸ����ּ��϶�����ĸ
		// ���͵������������պ��룬��8836KV

		System.out.println("4) ��ʾƥ������ķ��� - ������������");
		pattern = Pattern.compile("[0-9]{4}[A-Z]{2}");
		matcher = pattern.matcher("8834KV;3333MC; 33334V;");

		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		//���
		//4) ��ʾƥ������ķ��� - ������������
		//���1:   8834KV
		//���2:   3333MC
		
		System.out.println("-------------�����ķֽ���---------------");

		// 5. ���񡱷��� ^

		// ��^�����ų�Ϊ���񡱷��š�������ڷ������ڣ���^����ʾ����Ҫƥ����ַ�
		System.out.println("5) ���񡱷���  ^");
		// ƥ�����е��ʣ����ԡ�X����ĸ��ͷ�ĵ��ʳ���
		//pattern = Pattern.compile("[^X][a-z]+");
		//matcher = pattern.matcher("Xa;   AA;aabbcc; dd;");
		
		pattern = Pattern.compile("[^XY][a-z]+");
		matcher = pattern.matcher("Xa;   AA; aabbcc; dd; Ycc;  Yc;  Ze;  ");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		//���
		//5) ���񡱷���  ^
		//���1:    aabbcc
		//���2:    dd
		//���3:   cc
		//���4:   Ze
		
		
		System.out.println("-------------�����ķֽ���---------------");

		// 6. Բ���źͿհ׷���

		System.out.println("6) Բ���źͿհ׷���");
		// pattern = Pattern.compile("[a-z]+ +[0-9]{1,2}, *[0-9]{4}");
		// pattern = Pattern.compile("[A-z]+ +[0-9]{1,2}, *[0-9]{4}");
		// //����ƥ���д��Сд����ĸ
		pattern = Pattern.compile("([A-z]+) +[0-9]{1,2}, *[0-9]{4}"); // �·�ֵ������
																		// ���飻
		// matcher = pattern.matcher("June 26, 1951;   July 27, 1979");
		matcher = pattern.matcher("June 26, 1951;   July 27, 1979");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 7. ��������

		System.out.println("7) ��������");
		// pattern = Pattern.compile("[0-9]{3}"); //3������
		// pattern = Pattern.compile("[^0-9]{3}"); //3��������
		// pattern = Pattern.compile("[A-z0-9]{3}"); //3�� ��ĸ����
		// pattern = Pattern.compile("[A-Z0-9]{3}"); //3�� ��д��ĸ������
		pattern = Pattern.compile("[^A-Z0-9]{3}"); // 3�� �Ǵ�д��ĸ������
		matcher = pattern.matcher("JBB 2C6, 1951;   July 27, 1979");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 8. ����ip��ַ
		System.out.println("8. ����ip ��ַ");
		pattern = Pattern
				.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}"); // 1~3������
																			// һ���ֶ�
		matcher = pattern.matcher("192.168.0.1; 33.55.66.77  192,168.33,2 ");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}
		
		//���
		//���1:   192.168.0.1
		//���2:   33.55.66.77

		System.out.println("-------------�����ķֽ���---------------");

		// 9. html ��������ѯ�� < ��ʼ�� �� > ������ �ַ�����
		System.out.println("9. html ����");

		// ������ʽ�Ľ��ͣ�
		// '<' + 0�����ɸ��ո� + font + 0�����ɸ��ո� + ��'>'�����ɸ��ַ� + 0�����ɸ��ո� + '>'
		pattern = Pattern.compile("< *font *([^>]*) *>");
		matcher = pattern
				.matcher("<font face=\"Aria\" size=\"2\" color=\"red\"> ");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 10. ��б��

		System.out.println("10. ��б��");

		// pattern = Pattern.compile("[0-9]{3}");
		// pattern = Pattern.compile("[\\d]{3}"); //�ȼ�

		// pattern = Pattern.compile("[^0-9]{3}");
		// pattern = Pattern.compile("[\\D]{3}"); //�ȼ�

		// pattern = Pattern.compile("[a-zA-Z_0-9]{3}");
		// pattern = Pattern.compile("[\\w]{3}"); //�ȼ�

		// pattern = Pattern.compile("[^a-zA-Z_0-9]{3}");
		pattern = Pattern.compile("[\\W]{3}"); // �ȼ�

		matcher = pattern.matcher("fff 333 444 5555 #%4 ,34f");
		order = 0;
		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 11. ��֤�����ַ
		System.out.println("11. ��֤�����ַ");
		String mail = "cepon.li-n_e@yahoo.com.cn";

		// ����1����ĸ����.-_ + @ + ( ����1����ĸ����-_ + . )
		// �����ִ�Сд
		// TODO: \\. \\- �ľ��庬��
		pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",
				Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(mail);
		boolean isMail = matcher.matches();
		System.out.println("email:  " + mail + "      ��������:" + isMail);

		System.out.println("-------------�����ķֽ���---------------");

		// 12. ȥ�� html ��ǩ
		System.out.println("12. ȥ�� html ��ǩ");
		// Pattern.DOTALL(?s) ��������ģʽ�£����ʽ'.'����ƥ�������ַ���������ʾһ�еĽ�������
		// Ĭ������£����ʽ'.'��ƥ���еĽ�������

		// < + �����ַ�
		pattern = Pattern.compile("<.+?>", Pattern.DOTALL); // ���� <x>
															// �ĸ�ʽ����x����Ϊ1���ַ���
		// pattern = Pattern.compile("<.+>", Pattern.DOTALL); //ע�� �� "<.+?>"
		// �Ĳ�ͬ����
		matcher = pattern.matcher("<a href=\"index.html\">��ҳ</a>");

		while (matcher.find()) {
			order++;
			String matchStr = matcher.group();
			System.out.println("���" + order + ":   " + matchStr);
		}

		String html1 = matcher.replaceAll("");
		System.out.println(html1);

		System.out.println("-------------�����ķֽ���---------------");

		// 13. ����html�ж�Ӧ�����ַ���
		System.out.println("13. ����html�ж�Ӧ�����ַ���");
		pattern = Pattern.compile("href=\"(.+?)\""); // ע������д���� (.+?)
		matcher = pattern.matcher("<a href=\"index.html\">��ҳ</a>");
		if (matcher.find()) {
			System.out.println("href= ������Ϊ��" + matcher.group(1));
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 14. ��ȡhttp://��ַ
		System.out.println(" 14. ��ȡhttp://��ַ");
		// http:// �� https:// + ƥ������1������ĸ . - _
		pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
		// matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
		matcher = pattern.matcher("dsdsds<http://dsds//gfgf.-_*fdfd>fdf");
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			buffer.append(matcher.group());
			buffer.append("\r\n");
			System.out.println(buffer.toString());
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 15. �滻ָ��{}������
		System.out.println(" 15. �滻ָ��{}������");
		String str15 = "JavaĿǰ�ķ�չʷ����{0}��-{1}��";
		String[][] object = { new String[] { "\\{0\\}", "1995" },
				new String[] { "\\{1\\}", "2007" } };
		System.out.println(replace(str15, object));

		System.out.println("-------------�����ķֽ���---------------");

		// 16. ������Java��ͷ,�����β���ַ���
		System.out.println(" 16. ������Java��ͷ,�����β���ַ���");
		// ������Java��ͷ,�����β���ַ���
		pattern = Pattern.compile("^Java.*");
		matcher = pattern.matcher("Java������");
		boolean b = matcher.matches();
		// ����������ʱ��������true�����򷵻�false
		System.out.println(b);

		System.out.println("-------------�����ķֽ���---------------");

		// 17. �Զ������ָ��ַ���ʱ������Ҫ!��
		System.out.println(" 17. �Զ������ָ��ַ���ʱ������Ҫ!��");
		// �����и����
		// 1������ , �ո� | ��Ϊ�и��
		pattern = Pattern.compile("[, |]+");
		String[] strs = pattern
				.split("Java Hello World  Java,Hello,,World|Sun");
		if (strs != null && strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				System.out.println(strs[i]);
			}
		}

		System.out.println("-------------�����ķֽ���---------------");

		// 18. �����滻���״γ����ַ���
		System.out.println(" 18. �����滻���״γ����ַ���");
		pattern = Pattern.compile("������ʽ");
		matcher = pattern.matcher("������ʽ Hello World,������ʽ Hello World");
		// �滻��һ���������������
		System.out.println(matcher.replaceFirst("Java"));

		System.out.println("-------------�����ķֽ���---------------");

		// 19. �����滻��ȫ����
		System.out.println(" 19. �����滻��ȫ����");
		pattern = Pattern.compile("������ʽ");
		matcher = pattern.matcher("������ʽ Hello World,������ʽ Hello World");
		// �滻��һ���������������
		System.out.println(matcher.replaceAll("Java"));

		System.out.println("-------------�����ķֽ���---------------");

		// 20. �����滻���û��ַ��� - (�� StringBuffer ���ʹ�ã�)
		System.out.println(" 20. �����滻���û��ַ��� - (�� StringBuffer ���ʹ�ã�)");
		pattern = Pattern.compile("������ʽ");
		matcher = pattern.matcher("������ʽ Hello World,������ʽ Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());

		System.out.println("-------------�����ķֽ���---------------");
		
		
		// 21. ͳ��һ���ַ����г�������һ�����ַ����Ĵ���
		System.out.println(" 21. ͳ��һ���ַ����г�������һ�����ַ����Ĵ���");
		int leftBracketNum  = countSubStrNum(" (  2  , 3 , 22 ) , ( -1 , 5 , -5 ) ", "[\\(]{1}");
		int rightBracketNum = countSubStrNum(" (  2  , 3 , 22 ) , ( -1 , 5 , -5 ) ", "[\\(]{1}");
		
		// 22. һ��������ַ�����������֤   (2 , 3 , 22 ) , ( -1 , 5 , -5 )
		System.out.println("  22. һ��������ַ�����������֤   (2 , 3 , 22 ) , ( -1 , 5 , -5 )");
		// src��    (2 , 3 , 22 ) , ( -1 , 5 , -5 )
		//���������   (2 , 3 , 22 )      ( -1 , 5 , -5 )
		testparseStrWithRegex01();
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 23. һ��������ַ�����������֤  "..." , "..."
		System.out.println("  23. һ��������ַ�����������֤  \"...\" , \"...\"");
		testparseStrWithRegex02();
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 24. Pattern.matches() ����ʾ��
		System.out.println("24. Pattern.matches() ����ʾ��");
		//ע�⣬ ����� �����ַ������κ��޸ģ� ���ؾ���false�ˡ�
		String data24 = "java";
		boolean result24 = Pattern.matches("java",data24); // matches(String regex�� CharSequence input)
		System.out.println(result24); //true
	
		System.out.println("-------------�����ķֽ���---------------");
		
		
		// 25.  Բ���ŷ��� 
		System.out.println("25.  Բ���ŷ��� ");
		//ģʽ�ǡ�m(o+)n��������ʾ mn �м�� o �����ظ�һ�λ��Σ�
		//��� moon��mon��mooon ��ƥ��ɹ����� mono ��n �����һ�� o����ģʽƥ�䲻��.
		//ע: +��ʾһ�λ��Σ�?��ʾ 0 �λ�һ�Σ�*��ʾ 0 �λ���
				
		String[] dataArr25 = { "moon", "mon", "moon", "mono" };
		for (String str1 : dataArr25) {
			String patternStr = "m(o+)n";
			boolean result = Pattern.matches(patternStr, str1);
			if (result) {
				System.out.println("�ַ���" + str1 + "ƥ��ģʽ" + patternStr + "�ɹ�");
			} else {
				System.out.println("�ַ���" + str1 + "ƥ��ģʽ" + patternStr + "ʧ��");
			}
		}
		
		//���
		//�ַ���moonƥ��ģʽm(o+)n�ɹ�
		//�ַ���monƥ��ģʽm(o+)n�ɹ�
		//�ַ���moonƥ��ģʽm(o+)n�ɹ�
		//�ַ���monoƥ��ģʽm(o+)nʧ��
		System.out.println("-------------�����ķֽ���---------------");
		
		// 26.  \d ʾ��
		System.out.println(" 26.  \\d ʾ��");
		String[] dataArr26 = { "1", "10", "101", "1010", "100+" };
		for (String str26 : dataArr26) {
			String patternStr = "\\d+";   
			//String patternStr = "[0-9]+";  �ȼ�
			boolean result = Pattern.matches(patternStr, str26);
			if (result) {
				System.out.println("�ַ���" + str26 + "ƥ��ģʽ" + patternStr + "�ɹ�");
			} else {
				System.out.println("�ַ���" + str26 + "ƥ��ģʽ" + patternStr + "ʧ��");
			}
		}
		
		//���
		//�ַ���1ƥ��ģʽ\d+�ɹ�
		//�ַ���10ƥ��ģʽ\d+�ɹ�
		//�ַ���101ƥ��ģʽ\d+�ɹ�
		//�ַ���1010ƥ��ģʽ\d+�ɹ�
		//�ַ���100+ƥ��ģʽ\d+ʧ��
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 27.  \w ʾ��
		System.out.println("  27.  \\w ʾ��");
		String[] dataArr27 = { "a100", "b20", "Ac30", "df10000", "gh0t","11333" };
		for (String str : dataArr27) {
			String patternStr = "\\w+\\d+";
			//String patternStr = "[a-zA-Z0-9]+[0-9]+";   //�ȼ�
			boolean result = Pattern.matches(patternStr, str);
			if (result) {
				System.out.println("�ַ���" + str + "ƥ��ģʽ" + patternStr + "�ɹ�");
			} else {
				System.out.println("�ַ���" + str + "ƥ��ģʽ" + patternStr + "ʧ��");
			}
		}
		
		//���
		//�ַ���a100ƥ��ģʽ\w+\d+�ɹ�
		//�ַ���b20ƥ��ģʽ\w+\d+�ɹ�
		//�ַ���Ac30ƥ��ģʽ\w+\d+�ɹ�
		//�ַ���df10000ƥ��ģʽ\w+\d+�ɹ�
		//�ַ���gh0tƥ��ģʽ\w+\d+ʧ��
		//�ַ���11333ƥ��ģʽ\w+\d+�ɹ�
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 28.  ���� �и��ַ���
		System.out.println("  28.  ���� �и��ַ���");
		String str28 = "нˮ,,ְλ ����;����	�Ա�";
		String[] dataArr28 = str28.split("[,\\s;]");   //      \\s   ��ʾ�հ��ַ���space�� TAB �ȣ�
		for (String strTmp : dataArr28) {
			System.out.println(strTmp);
		}
		//���
		//нˮ
        //
		//ְλ
		//����
		//����
		//�Ա�
		System.out.println("-------------�����ķֽ���---------------");
		
		// 29.  ʹ��Pattern�� split()������ �и��ַ���
		System.out.println(" 29.  ʹ��Pattern�� split()������ �и��ַ���");
		String str29 = "2007��12��11��";
		Pattern p29 = Pattern.compile("[������]");
		String[] dataArr29 = p29.split(str29);
		for (String strTmp : dataArr29) {
			System.out.println(strTmp);
		}
		
		//���
		//2007
		//12
		//11
		System.out.println("-------------�����ķֽ���---------------");
		
		
		// 30. ͨ���������replaceAll ������
		System.out.println(" 30. ͨ���������replaceAll ������");
		//ģʽ��(\d+)(Ԫ|�����|RMB)�������ŷֳ������飬��һ��\d+ƥ�䵥���������֣��ڶ���ƥ��Ԫ������
		//�ң�RMB �е�����һ�����滻���ֱ�ʾ��һ����ƥ��Ĳ��ֲ���($1 ��ʾǰ��ĵ�һ��)���������滻�ɣ�.
		String str = "10Ԫ 1000����� 10000Ԫ 100000RMB";
		String str1 = str.replaceAll("(\\d+)(Ԫ|�����|RMB)","$1��");
		System.out.println(str1);
		
		String str2 = str.replaceAll("(\\d+)(Ԫ|�����|RMB)", "��$2");
		System.out.println(str2);
		
		//���
		//10�� 1000�� 10000�� 100000��
		//��Ԫ ������� ��Ԫ ��RMB
		System.out.println("-------------�����ķֽ���---------------");
		
		
		// 31. appendReplacement demo,  �ô���̫�󡣡�
		System.out.println("  31. appendReplacement demo,  �ô���̫�󡣡�");
		Pattern p31 = Pattern.compile("m(o+)n", Pattern.CASE_INSENSITIVE);
		// ��Pattern���matcher()��������һ��Matcher����
		Matcher m31 = p31.matcher("moon mooon Mon mooooon Mooon");
		StringBuffer sb31 = new StringBuffer();
		// ʹ��find()�������ҵ�һ��ƥ��Ķ���
		boolean result31 = m31.find();
		// ʹ��ѭ���ҳ�ģʽƥ��������滻֮���ٽ����ݼӵ�sb��
		while (result31) {
			// appendReplacement(StringBuffer sb ��String replacement)
			// ʵ�ַ��ն���Ӻ��滻����
			m31.appendReplacement(sb31, "moon12BYJ");
			result31 = m31.find();
		}
		// ������appendTail()���������һ��ƥ����ʣ���ַ����ӵ�sb�
		m31.appendTail(sb31);
		System.out.println("�滻��������" + sb31.toString());
		
		//���
		//�滻��������moon12BYJ moon12BYJ moon12BYJ moon12BYJ moon12BYJ
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 32. ��׼ƥ�����demo  {3-5}
		System.out.println("  32. ��׼ƥ�����demo  {3-5}");
		
		//��{}��ָ����ȷָ�����ֵĴ�����
		//X{2,5}��ʾ X ���ٳ��� 2 �Σ������� 5 ��;
		//X{2,}��ʾ X ���ٳ��� 2 �Σ�������;
		//X{5}��ʾ X ֻ��ȷ�ĳ��� 5 ��
		String[] dataArr32 = { "google", "gooogle", "gooooogle", "goooooogle", "ggle" };
		for (String str32 : dataArr32) {
			String patternStr32 = "g(o{2,5})gle";
			boolean result32 = Pattern.matches(patternStr32, str32);
			if (result32) {
				System.out.println("�ַ���" + str32 + "ƥ��ģʽ" + patternStr32 + "�ɹ�");
			} else {
				System.out.println("�ַ���" + str32 + "ƥ��ģʽ" + patternStr32 + "ʧ��");
			}
		}
		
		//���
		//�ַ���googleƥ��ģʽg(o{2,5})gle�ɹ�
		//�ַ���gooogleƥ��ģʽg(o{2,5})gle�ɹ�
		//�ַ���gooooogleƥ��ģʽg(o{2,5})gle�ɹ�
		//�ַ���goooooogleƥ��ģʽg(o{2,5})gleʧ��
		//�ַ���ggleƥ��ģʽg(o{2,5})gleʧ��
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 33. ������ �� -  ��� ��Χ����
		System.out.println("  33. ������ �� -  ��� ��Χ����");
		// -��ʾ��..��������[a-c]��ͬ��[abc]
		String[] dataArr33 = { "Tan", "Tbn", "Tcn", "Ton", "Twn" };
		for (String str33 : dataArr33) {
			String regex33 = "T[a-c]n";
			boolean result33 = Pattern.matches(regex33, str33);
			if (result33) {
				System.out.println("�ַ���" + str33 + "ƥ��ģʽ" + regex33 + "�ɹ�");
			} else {
				System.out.println("�ַ���" + str33 + "ƥ��ģʽ" + regex33 + "ʧ��");
			}
		}
		
		//���
		//�ַ���Tanƥ��ģʽT[a-c]n�ɹ�
		//�ַ���Tbnƥ��ģʽT[a-c]n�ɹ�
		//�ַ���Tcnƥ��ģʽT[a-c]n�ɹ�
		//�ַ���Tonƥ��ģʽT[a-c]nʧ��
		//�ַ���Twnƥ��ģʽT[a-c]nʧ��
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 34. ��Сд������ Pattern.CASE_INSENSITIVE
		System.out.println("  34. ��Сд������ Pattern.CASE_INSENSITIVE");
		String patternStr34 = "ab";
		Pattern pattern34 = Pattern.compile(patternStr34, Pattern.CASE_INSENSITIVE);
		String[] dataArr34 = { "ab", "Ab", "AB" ,"aB1"};
		for (String str34 : dataArr34) {
			Matcher matcher34 = pattern34.matcher(str34);
			if (matcher34.find()) {
				System.out.println("�ַ���" + str34 + "ƥ��ģʽ" + patternStr34 + "�ɹ�");
			}
		}
		
		//���
		//�ַ���abƥ��ģʽab�ɹ�
		//�ַ���Abƥ��ģʽab�ɹ�
		//�ַ���ABƥ��ģʽab�ɹ�
		//�ַ���aB1ƥ��ģʽab�ɹ�
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 35. ʹ��������ʽ�ָ��ַ���
		System.out.println("  35. ʹ��������ʽ�ָ��ַ���");
		String input35 = "ְ��=GM нˮ=50000 , ����=ְҵ������ ; �Ա�=�� ����=45 ";
		//ע������Ҫ�Ѹ��ӵ�ģʽд��ǰ�棬�����ģʽ����ƥ����
		//�����и�ģʽ��     �ո�,�ո�   �ո�;�ո�  �ո�    
		String patternStr35 = "(\\s*,\\s*)|(\\s*;\\s*)|(\\s+)";
		Pattern pattern35 = Pattern.compile(patternStr35);
		String[] dataArr35 = pattern35.split(input35);
		for (String str35 : dataArr35) {
			System.out.println(str35);
		}
		
		//���
		//ְ��=GM
		//нˮ=50000
		//����=ְҵ������
		//�Ա�=��
		//����=45
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 36. �߼�����ƥ�� <name>Bill</name><salary>50000</salary>
		System.out.println(" 36. �߼�����ƥ�� <name>Bill</name><salary>50000</salary>");
		
		//  \w �����ַ� [a-zA-Z0-9]      
		//  \1 ��Ӧ��һ��С������������ group1
		String regex36 = "<(\\w+)>(\\w+)</\\1>";
		Pattern pattern36 = Pattern.compile(regex36);
		String input36 = "<name>Bill</name><salary>50000</salary><title>GM</title><age>33</ag>";
		Matcher matcher36 = pattern36.matcher(input36);
		while (matcher36.find()) {
			System.out.println("match item str:"+matcher36.group()+"       name:"+matcher36.group(1)+ "  value:"+matcher36.group(2));
		}
		
		//���
		//match item str:<name>Bill</name>       name:name  value:Bill
		//match item str:<salary>50000</salary>       name:salary  value:50000
		//match item str:<title>GM</title>       name:title  value:GM
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 37. ���������ֻ�ϵ��ַ����ĵ��ʲ��ִ�д,  �о��ô���̫��
		System.out.println(" 37. ���������ֻ�ϵ��ַ����ĵ��ʲ��ִ�д,  �о��ô���̫��");
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
		System.out.println("�滻����ִ�Ϊ" + sb37.toString());
		
		//���
		//�滻����ִ�ΪAGE45 SALARY500000 50000 title
		
		System.out.println("-------------�����ķֽ���---------------");
		
		
		// 38. ƥ�� [...]  ����
		System.out.println(" 38. ƥ�� [...]  ����");
		Pattern p38 = Pattern.compile("\\[([^\\]]+)\\]");
		//      \\[                 ([^\\]]+)                          \\]
		//   ƥ��һ��[        ���飺����ƥ��һ����]      ƥ��һ��]
		Matcher m38 = p38.matcher(" [2]  []  [1,3] []]  [[]");
		while(m38.find()){
			String str38 = m38.group();
			System.out.println(str38);
		}
		
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 39. �����ۺ����ã�ʹ�÷��飬������Ϣ�Է��鷽ʽչ�֣�
		System.out.println(" 39. �����ۺ����ã�ʹ�÷��飬������Ϣ�Է��鷽ʽչ�֣�");
		String logEntry = "172.26.155.241 - - [26/Feb/2001:10:56:03 -0500] \"GET /IsAlive.htm HTTP/1.0\" 200 15 ";
		final String IP_SEGMENT_NUM_REGEX = "[0-9]{1,3}";
		final String IP_SEGMENT_SEPARATOR_REGEX  = "\\.";
		final String IP_REGEX =   IP_SEGMENT_NUM_REGEX+IP_SEGMENT_SEPARATOR_REGEX+
												IP_SEGMENT_NUM_REGEX+IP_SEGMENT_SEPARATOR_REGEX+
												IP_SEGMENT_NUM_REGEX+IP_SEGMENT_SEPARATOR_REGEX+
												IP_SEGMENT_NUM_REGEX;
		
		//ע����  [0-9]{1,3}    ������  [0-9]{1-3}
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
		
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 40. html���� �������������������� �ǳ����������)
		System.out.println("40. html���� �������������������� �ǳ����������)");
		String input40  = "<font face =\"Arial,Serif\" size=  \"+2\" color=\"re d\">";
		System.out.println("input40:   "+input40);
		
		//���ַ�ʽ���ԣ�Ҫ�Ȱ� ������ţ��Ҽ����� �� �м�Ķ��� ƥ������� ����ƥ������Ķ���
		//Pattern p40 = Pattern.compile("([\\w]+)=\"([\\w]+)\"");
		//Matcher m40 = p40.matcher(input40);
		//while(m40.find()){
		//	System.out.println("name:  "+m40.group(1) +"    value:  "+m40.group(2));
		// }
		
		Pattern p40 = Pattern.compile("<\\s*font\\s+([^>]*)\\s*>");
		//             <                 \\s*        font             \\s+                   ([^>]*)                 \\s*             >
		//          �������        ���ɿո�  �ض��ַ���    ����һ���ո�    ��������Ҽ�����  ���ɿո�    �Ҽ�����
		Matcher m40 = p40.matcher(input40);
		while(m40.find()){
			
			String input40Sub = m40.group(1) ;
			System.out.println("group(1):  "+input40Sub);
			
			Pattern p40sub = Pattern.compile("([a-zA-Z]+)\\s*=\\s*\"([^\"]+)\"");
			//         ([a-zA-Z]+)        \\s*          =              \\s*          \"              ([^\"]+)                  \"
			//       1��������ĸ     ���ɿո�  �ض��ַ�    ���ɿո�   ˫����      1������˫����     ˫����   
			System.out.println("��ѭ��");
			Matcher m40sub = p40sub.matcher(input40Sub);
			while(m40sub.find()){
				
				System.out.println("group(1):  "+m40sub.group(1)+"    group(2):   "+m40sub.group(2));
			}
		}
		System.out.println("-------------�����ķֽ���---------------");
		
		// 41. ͨ������ƥ��http����
		System.out.println("41. ͨ������ƥ��http����");
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
		System.out.println("-------------�����ķֽ���---------------");
		
		// 42. ͨ����������ַ���
		System.out.println("42. ͨ����������ַ���");
		
		//���滻�ؼ��ֵĵ�����Դ
		Map<String,String> tokens42 = new HashMap<String,String>();
		tokens42.put("cat", "Garfield");
		tokens42.put("beverage", "coffee");
		
		String template42 = "${cat} really needs some ${beverage}.";
		//����ƥ��ģʽ��������ʽ
		//String patternString42 = "\\$\\{(" + StringUtils.join(tokens42.keySet(), "|") + ")\\}";
		String patternString42 = "\\$\\{(cat|beverage)\\}";
		
		Pattern pattern42 = Pattern.compile(patternString42);
		Matcher matcher42 = pattern42.matcher(template42);

		//����������appendReplacement, appendTail
		StringBuffer sb42 = new StringBuffer();
		while(matcher42.find()) {
			System.out.println("group(0):  "+matcher42.group(0));
			System.out.println("group(1):  "+matcher42.group(1));
		    matcher42.appendReplacement(sb42, tokens42.get(matcher42.group(1)));
		}
		matcher42.appendTail(sb42);

		//out: Garfield really needs some coffee.
		//ע�⣺�����ǰ������� group(0) �滻�ˡ�
		System.out.println(sb42.toString());
		
		
		System.out.println("-------------�����ķֽ���---------------");
		
		// 43. appendReplacement&appendTail demo��javadoc��
		System.out.println(" 43. appendReplacement&appendTail demo��javadoc��");
		
		 Pattern p43 = Pattern.compile("cat");
		 Matcher m43 = p43.matcher("one cat two cats in the yard");
		 StringBuffer sb43 = new StringBuffer();
		 while (m43.find()) {
		     m43.appendReplacement(sb43, "dog");
		     System.out.println("m43.group():  "+m43.group());
		 }
		 m43.appendTail(sb43);
		 System.out.println(sb43.toString());
		 
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 //�����е��������� Greedy (̰��)��Reluctant(����)�� Possessive(ǿռ)����. 
		 
		 // 44. ������- ̰��(Greedy) ʾ��
		 System.out.println("44. ������- ̰��(Greedy) ʾ��");
		 //Greedy  ������
		 //X?  X��һ�λ�һ��Ҳû��
		 //X*  X����λ���
		 //X+  X��һ�λ���
		 //X{n}  X��ǡ�� n ��
		 //X{n,}  X������ n ��
		 //X{n,m}  X������ n �Σ����ǲ����� m ��
		 //Greedy ����õģ�����ƥ�䷽ʽ���Ȱ������ַ������£�Ȼ��ƥ�������ַ����������ƥ�䣬��
		 //���Ҷ��³�һ���ַ����ٽ���ƥ�䣬ֱ���ҵ�ƥ���������ַ�������Ϊֹ
		 
		Matcher m44 = Pattern.compile("a.*b").matcher("a====b=========b=====");
		while (m44.find()) {
			System.out.println(m44.group());
		}
		 
		 
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 45. ������- Reluctant(����)  ʾ��
		 System.out.println("45. ������- Reluctant(����)  ʾ��");
		 //��ǿ�ģ�����Ը�ġ�
		 //Reluctant  ������
		 //X??  X��һ�λ�һ��Ҳû��
		 //X*?  X����λ���
		 //X+?  X��һ�λ���
		 //X{n}?  X��ǡ�� n ��
		 //X{n,}?  X������ n ��
		 //X{n,m}?  X������ n �Σ����ǲ����� m ��
		 //Reluctant ���ú� Greedy �෴�����ȴ���Сƥ�俪ʼ���ȴ��������һ���ַ���Ȼ�����ƥ�䣬����
		 //ƥ���������һ���ַ���ֱ���ҵ�ƥ��������ַ�������Ϊֹ��
		 //��Ϊ���Ǵ���Сƥ�俪ʼ���ʳ� ����
		Matcher m45 = Pattern.compile("a.*?b").matcher("a====b=========b=====");
		while (m45.find()) {
			System.out.println(m45.group());
		}
		 
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 46. ������- Possessive(ǿռ) ʾ��
		 System.out.println("46. ������- Possessive(ǿռ) ʾ��");
		 //Possessive  ������
		 //X?+  X��һ�λ�һ��Ҳû��
		 //X*+  X����λ���
		 //X++  X��һ�λ���
		 //X{n}+  X��ǡ�� n ��
		 //X{n,}+  X������ n ��
		 //X{n,m}+  X������ n �Σ����ǲ����� m ��
		 //Possessive �� Greedy ��ƥ�䷽ʽһ�����Ȱ������ַ������£�Ȼ��ƥ�������ַ��������ƥ�䣬��
		 //��Ϊƥ�䣬�����ƥ�䣬����Ϊ�����ַ�����ƥ�䣬 ��������Ҷ��³�һ���ַ����ٽ���ƥ�䣬ֻ����һ��
		 //��Ϊ̰���������������ʳ� ǿռ
		 
		Matcher m46 = Pattern.compile("a.*+b").matcher("a====b=========b=====");
		while (m46.find()) {
			System.out.println(m46.group());
		}
		 
		//���κ����
		 
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 47. ������ʾ�� (��ʵǰ���Ѿ��õ��˺ܶ����)
		 //������ʽ��ÿ��"()"�ڵĲ�������һ�������飬ÿ�������鶼��һ����ţ���1,2...�����0��������ƥ�䵽�����ݡ�
		 System.out.println("47. ������ʾ��");
		String text47 = "<textarea rows=\"20\" cols=\"70\">nexus maven</textarea>";
		//String reg47 = "<textarea.*?>.*?</textarea>";
		//�����������ʽ�й����ĸ������飺(<textarea.*?>)��(.*?)��(</textarea>)������ƥ�䵽������  
		String reg47 = "(<textarea.*?>)(.*?)(</textarea>)";   
		Pattern p47 = Pattern.compile(reg47);
		Matcher m47 = p47.matcher(text47);
		while (m47.find()) {
			System.out.println(m47.group(0)); // ����ƥ�䵽������
			System.out.println(m47.group(1)); // (<textarea.*?>)
			System.out.println(m47.group(2)); // (.*?)
			System.out.println(m47.group(3)); // (</textarea>)
		}
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 
		 // 48. �ǲ�����ʾ�� (?:xxxx)
		 // ֻ�����������
		System.out.println("48. �ǲ�����ʾ�� ");
		String text48 = "<textarea rows=\"20\" cols=\"70\">nexus maven</textarea>";
		// String reg48 = "(<textarea.*?>)(.*?)(</textarea>)"; ȫ������

		// �����������ʽ�й��ж��������飺(.*?)������ƥ�䵽�����ݣ�
		// �����ǲ�����:(?:</textarea>)��(?:<textarea.*?>)
		String reg48 = "(?:<textarea.*?>)(.*?)(?:</textarea>)";
		Pattern p48 = Pattern.compile(reg48);
		Matcher m48 = p48.matcher(text48);
		while (m48.find()) {
			System.out.println(m48.group(0)); // ����ƥ�䵽������
			System.out.println(m48.group(1)); // (.*?)
			
			//System.out.println(m48.group(2));  //Exception ... No group 2
		}
			
		 System.out.println("-------------�����ķֽ���---------------");
	
		 // 49. ��xx�������÷�  xx$
		 System.out.println("49. ��xx�������÷�  xx$");
		Pattern p49 = Pattern.compile("(\\d+)([��$])$");  //���һ�� $ ��ʾ��ĳһ���ַ���������������� ��$ ����һ������
		String str49 = "8899��";
		Matcher m49 = p49.matcher(str49);
		if (m49.matches()) {
			System.out.println("���ҽ��: " + m49.group(1));
			System.out.println("��������: " + m49.group(2));
		}
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 50. �ǲ�����ʾ��2   �ǲ�����(?:)
		 System.out.println("50. �ǲ�����ʾ��2   �ǲ�����(?:)");
		Pattern p50 = Pattern.compile("(\\d+)(?:\\.?)(?:\\d+)([��$])$");
		String str50 = "8899.56��";
		Matcher m50 = p50.matcher(str50);
		if (m50.matches()) {
			System.out.println("����:"+m50.group());
			System.out.println("���ҽ��: " + m50.group(1));
			System.out.println("��������: " + m50.group(2));
		}
		 
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 51. �ǲ�����ʾ��3 -    (?=X)  ���������ж���
		 System.out.println(" 51. �ǲ�����ʾ��3 -    (?=X)  ���������ж���");
		 //ƥ����ôһ���ַ�������Ҫ���㣺����λ�ַ������ֻ���ĸ�����Һ������������ a��
		Pattern p51 = Pattern.compile("[0-9a-z]{2}(?=aa)");
		String str51 = "12332aa438aaf";
		Matcher m51 = p51.matcher(str51);
		while (m51.find()) {
			System.out.println(m51.group());
		}
		
		//32aa ����Ӵ�����������������Կ���ƥ�䵽������Ϊ (?=) �Ĳ����ǲ�����ģ����������ֻ�� 32��������aa��
		//ͬ�� 38aa Ҳƥ��������򣬶�������� 38
		//�����뿴һ��:
		//�� str ��һ��ƥ��ɹ���� 32 �󣬳���Ҫ�����������Ƿ���ƥ��������Ӵ�����ô��ʱӦ�ô� 32aa �ĺ�
		//һλ��ʼ�����ң����Ǵ� 32 �ĺ�һλ�أ�Ҳ���Ǵ����� 5 ��ʼ���Ǵ� 7 ��ʼ�أ����˿����뵽�Ǵ� 32aa ����
		//һλ��ʼ�����ң���Ϊ 32aa ƥ����������ʽ��������һλ��Ȼ�����ĺ���Ҳ���Ǵ� 4 ��ʼ����ʵ�����Ǵ� 32 ��
		//��һλҲ���ǵ�һ�� a ��ʼ�����ҡ�ԭ���� (?=) �Ƿǲ���ġ�
		//���� API �ĵ�����ôע�͵ģ�
		//(?=X) X, via zero- - width positive lookahead
		//�ɼ� zero-width�����ȣ�˵�ľ��������˼��
		
		//���
		//32
		//38
		
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 52. �ǲ�����ʾ��4 -    (?=X)  ���������ж���, ��������λ����֤
		System.out.println("52. �ǲ�����ʾ��4 -    (?=X)  ���������ж���, ��������λ����֤");
		Pattern p52 = Pattern.compile("[0-9a-z]{2}(?=aa)");
		String str52 = "aaaaaaaa";
		Matcher m52 = p52.matcher(str52);
		while (m52.find()) {
			System.out.println(m52.group());
		}
		 
		//��һ����������� aa aa aa
		//����һ�£�
		//����ַ���һ���� 8 �� a��
		//��һ��ƥ��Ƚ������ҵ����Ǿ���ǰ�ĸ���aaaa ,��Ȼ�����͵��ĸ� a �ǲ�����ģ���������ǵ�һ�͵ڶ��� a��
		//���ż������ң���ʱ�Ǵӵ����� a ��ʼ������������ 4 �� a ���䵽�ˣ�������������͵��ĸ� a��
		//���ż������ң���ʱ�Ǵӵ���� a ��ʼ���嵽�ˣ��� 4 �� a ���䵽�ˣ������������͵����� a��
		//����������ң���ʱ�Ǵӵ��߸� a ��ʼ����Ȼ�����ߺ͵ڰ˸� a,������������ʽ��ƥ�����������ҽ�����
		
		//���
		//aa
		//aa
		//aa	
		
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 53. �ǲ�����ʾ��5 -    (?=X) 
		Pattern p53 = Pattern.compile("(?=hopeful)hope");
		String str53 = "hopeful";
		Matcher m53 = p53.matcher(str53);
		while (m53.find()) {
			System.out.println(m53.group());
		}
		 
		//������ʽ����˼�ǣ��Ƿ���ƥ�� hopeful,����ܣ��򲶻� hopeful �е� hope����Ȼ����������ƥ����Ӵ���
		//�Ǵ� f ��ʼ��
		
		 System.out.println("-------------�����ķֽ���---------------");
		 
		 // 54. �ǲ�����ʾ��6 -    (?<=X) 
		Pattern p54 = Pattern.compile("(?<=aa)[0-9a-z]{2}");
		String str54 = "12332aa438aaf";
		Matcher m54 = p54.matcher(str54);
		while (m54.find()) {
			System.out.println(m54.group());
		}

		// ���������ʽ����˼�ǣ�ƥ����ôһ���ַ�������Ҫ���㣺����λ�ַ������ֻ���ĸ������ǰ�����������
		// ����ĸ a ��

		// ���
		// 43
		System.out.println("-------------�����ķֽ���---------------");
		
		
		// 55. ƥ��n������, {n,}
		String str55 = "abc-def cc----ff";
		String[] arr55 = str55.split("([^a-zA-Z\\-])|([\\-]{2,})");
		System.out.println("arr:"+U.array2Str(arr55)); //arr:{abc-def,cc,ff}
		
		str55 = "abc--def cc----ff";
		arr55 = str55.split("([^a-zA-Z\\-])|([\\-]{2,3})");
		System.out.println("arr:"+U.array2Str(arr55)); //arr:{abc,def,cc,-ff}
		
		
		// 56. �����С�����
		
		//��һ�仰������û�п�����ɶ��˼������
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
		
		
		//57. �� \  �滻Ϊ /
		 String s1 = "huawei\\device\\TestCase002.java";
	        String  s2 = s1.replaceAll("\\\\","/");
	        U.err("s1:"+s1);
	        U.err("s2:"+s2);
		
	}
	
	
	
	
	
	private static void testparseStrWithRegex02() {
		//          ("aa;116,39","bb;116,39"):("cc;116,39","dd;116,39","ee;116,39")
		//   "..."  ��������˫�����м�Ĺ�����зָ�
		String s = "(\"�����ׯ;116.3,39.97\",\"֪����;116.3,39.9\",\"֪��·;116.3,39.9\"):(\"���������Ͽ�;116.4,39.9\",\"��ƽ����;116.4,39.9\",\"��ƽ�ﱱ��;116.4,39.9\")";
		//����ƥ����� ����һ��",   Ȼ���ҿ�ѡ����"  ,  �������һ��" 
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
		
		//����1����������
		src = "  (2 , 3 , 22 ) , ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//����2�� �������в���3������
		src = "  (2 , 3  ) , ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//����3�� �����м��пո�
		src = "  (2 , 3 , 22 ) , ( - 1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//����4�����������
		src = "  (2 , 3 , (22 ) , ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//����5���������ֵ� �ָ��ַ����� ������Ӧ����  ),(
		src = "  (2 , 3 , 22 ) ; ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		src = "  (2 , 3 , 22 ) ( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		//����6�� �ж�����ַ�
		src = "  (2 , 3 , 22 ),a( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
		
		src = "  (2 , 3 , 22a ),( -1 , 5 , -5 )";
		bRtn = parseStrWithRegex01( src,  list);
		U.info("src:"+src+"   ;bRtn:"+bRtn+" ; list:"+list);
	}
	
	// src��    (2 , 3 , 22 ) , ( -1 , 5 , -5 )
	//���������   (2 , 3 , 22 )      ( -1 , 5 , -5 )
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
		
		//   ),( ����Ŀ ��֧�ֿո��ַ���
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

