package jnjd.longNumMultiply;


import junit.framework.TestCase;

public class TestLongNumMultiply extends TestCase {

	public void test001() {
		System.out.println("*********test001-正常用例************");
		String ret = LongNumMultiply. multiply("080", "0.125");
		assertEquals("10", ret);
	}
	
	public void test002() {
		System.out.println("*********test002-转换函数测试************");
		assertEquals("80", LongNumMultiply.convert("80.000"));
		assertEquals("80.3", LongNumMultiply.convert("80.300"));
		assertEquals("11.345", LongNumMultiply.convert("011.345"));
		assertEquals("11345", LongNumMultiply.convert("0011345"));
		assertEquals("0.345", LongNumMultiply.convert("000.345"));
		
		assertEquals("33.345", LongNumMultiply.convert("+33.345"));
	}
	
	public void test003() {
		System.out.println("*********test003-正常用例-负数相乘************");
		String ret = LongNumMultiply. multiply("-080", "-0.125");
		assertEquals("10", ret);
	}
	
	public void test004() {
		System.out.println("*********test004-正常用例-正数和负数相乘************");
		String ret = LongNumMultiply. multiply("080", "-0.125");
		assertEquals("-10", ret);
	}
	
	
	public void test005() {
		System.out.println("*********test005-正常用例-两个超大数相乘************");
		String ret = LongNumMultiply. multiply("8883485354352854.553434543544", "-4536536535435435.125");
		assertEquals("-40300255872027327518905221034985.969687399583", ret);
	}
	
	public void test006() {
		System.out.println("*********test005-正常用例-两个超大数相乘 +************");
		String ret = LongNumMultiply. multiply("+8883485354352854.553434543544", "-4536536535435435.125");
		assertEquals("-40300255872027327518905221034985.969687399583", ret);
	}
	
	public void test007() {
		System.out.println("*********test007-非法字符串************");
		String ret = LongNumMultiply. multiply("+4.55343+3544", "35.125");
		assertEquals(null, ret);
	}
	
	public void test008() {
		System.out.println("*********test008-非法字符串************");
		String ret = LongNumMultiply. multiply("-4.553f433544", "35.125");
		assertEquals(null, ret);
	}
	
}
