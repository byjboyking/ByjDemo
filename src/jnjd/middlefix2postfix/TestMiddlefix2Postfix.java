package jnjd.middlefix2postfix;

import java.util.List;

import junit.framework.TestCase;

public class TestMiddlefix2Postfix extends TestCase {

	public void test001() {
		System.out.println("*********test001-正常用例************");
		assertEquals("1 2 +", Middlefix2Postfix.expChange("1+2"));
	}
	
	public void test002() {
		System.out.println("*********test002-正常用例************");
		assertEquals("1 2 3 * +", Middlefix2Postfix.expChange("1+2*3"));
	}
	
	public void test003() {
		System.out.println("*********test003-正常用例************");
		assertEquals("1 2 + 3 - 4 +", Middlefix2Postfix.expChange("1+2-3+4"));
	}
	
	public void test004() {
		System.out.println("*********test004-正常用例************");
		assertEquals("1 2 + 3 - 4 5 * +", Middlefix2Postfix.expChange("1+2-3+4*5"));
	}
	
	public void test005() {
		System.out.println("*********test005-正常用例************");
		assertEquals("1 2 3 8 * - + 4 5 * +", Middlefix2Postfix.expChange("1+(2-3*8)+4*5"));
	}
	
	public void test006() {
		System.out.println("*********test006-processInput 测试************");
		List<String> list = Middlefix2Postfix.processInput("11+(22-3*83)/5");
		assertEquals(11, list.size());
	}
	
	public void test007() {
		System.out.println("*********test007-正常用例，大于9的数字************");
		assertEquals("1 23 333 8 * - + 444 5 * +", Middlefix2Postfix.expChange("1+(23-333*8)+444*5"));
	}
	
	public void test008() {
		System.out.println("*********test008-正常用例************");
		assertEquals("1 2 + 3 -", Middlefix2Postfix.expChange("1+2-3"));
	}
	
	public void test009() {
		System.out.println("*********test008-正常用例************");
		assertEquals("1 2 + 3 4 * -", Middlefix2Postfix.expChange("1+2-3*4"));
	}
}
