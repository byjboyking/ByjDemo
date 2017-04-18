package jnjd.wordReverse;


import junit.framework.TestCase;

public class WordReverseTest extends TestCase {

	public void test001(){
		System.out.println("*******test001-��������**********");
		WordReverse demo = new WordReverse();
		String pInput= "I am a - student";
		String pResult = "student a am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(0, demo.converse(pInput, szResult));
		assertEquals(pResult, szResult.toString());
	}
	
	public void test002(){
		System.out.println("*******test002-��������-��һ��-**********");
		WordReverse demo = new WordReverse();
		String pInput= "I am a-student";
		String pResult = "a-student am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(0, demo.converse(pInput, szResult));
		assertEquals(pResult, szResult.toString());
	}
	
	public void test003(){
		System.out.println("*******test003-��������-**********");
		WordReverse demo = new WordReverse();
		String pInput= "I am cc-dd-ee";
		String pResult = "cc-dd-ee am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(0, demo.converse(pInput, szResult));
		assertEquals(pResult, szResult.toString());
	}
	
	public void test004(){
		System.out.println("*******test004-��������-�ַ�������**********");
		WordReverse demo = new WordReverse();
		String pInput= "I am cc-dd-eeqwaaaaaaaaaab";
		String pResult = "cc-dd-eeqwaaaaaaaaaa am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(-1, demo.converse(pInput, szResult));
		//assertEquals(pResult, szResult.toString());
	}
	
	public void test005(){
		System.out.println("*******test005-��������-**********");
		WordReverse demo = new WordReverse();
		String pInput= "I am cc-dd-ee";
		String pResult = "cc-dd-ee am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(0, demo.converse(pInput, szResult));
		assertEquals(pResult, szResult.toString());
	}
	
	public void test006(){
		System.out.println("*******test006-��������-**********");
		WordReverse demo = new WordReverse();
		String pInput= "I am -dd-ee";
		String pResult = "-dd-ee am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(0, demo.converse(pInput, szResult));
		assertEquals(pResult, szResult.toString());
	}
	
	public void test007(){
		System.out.println("*******test007-�����쳣-**********");
		WordReverse demo = new WordReverse();
		String pInput= "";
		String pResult = "-dd-ee am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(-1, demo.converse(pInput, szResult));
		//assertEquals(pResult, szResult.toString());
	}
	
	public void test008(){
		System.out.println("*******test008-�����쳣-**********");
		WordReverse demo = new WordReverse();
		String pInput= null;
		//String pResult = "-dd-ee am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(-1, demo.converse(pInput, szResult));
		//assertEquals(pResult, szResult.toString());
	}
	
	public void test009(){
		System.out.println("*******test009-�����쳣-**********");
		WordReverse demo = new WordReverse();
		String pInput= "1";
		//String pResult = "-dd-ee am I";
		StringBuffer szResult = new StringBuffer();
		assertEquals(-1, demo.converse(pInput, szResult));
		//assertEquals(pResult, szResult.toString());
	}
	
}
