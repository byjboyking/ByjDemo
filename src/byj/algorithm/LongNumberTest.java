package byj.algorithm;

import junit.framework.TestCase;

public class LongNumberTest extends TestCase {

	public void test001() {
		System.out.println("*********test001-bigIntegerMultiply 测试************");
		String ret1 = LongNumber.bigIntegerMultiply("12", "34");
		long ret2 = 12*34;
		assertEquals(String.valueOf(ret2), ret1);
	}
	
	public void test002() {
		System.out.println("*********test002-processPrefixZero 测试************");
		String ret1 = LongNumber.processPrefixZero("00123");
		assertEquals("123", ret1);
		
		ret1 = LongNumber.processPrefixZero("0000");
		assertEquals("0", ret1);
		
		ret1 = LongNumber.processPrefixZero("0");
		assertEquals("0", ret1);
	}
	
	public void test003() {
		System.out.println("*********test003-isLegalBigNumber 测试************");
		assertEquals(false, LongNumber.isLegalBigNumber("+"));
		assertEquals(false, LongNumber.isLegalBigNumber("-"));
		assertEquals(false, LongNumber.isLegalBigNumber("a"));
		assertEquals(true, LongNumber.isLegalBigNumber("0"));
		assertEquals(false, LongNumber.isLegalBigNumber("+."));
		
		assertEquals(false, LongNumber.isLegalBigNumber("+3322a"));
		assertEquals(true, LongNumber.isLegalBigNumber("+33.22"));
		assertEquals(true, LongNumber.isLegalBigNumber("+33022"));
		
		assertEquals(true, LongNumber.isLegalBigNumber("-.332"));
		
		assertEquals(true, LongNumber.isLegalBigNumber("-333."));
	}
	
	public void test004() {
		System.out.println("*********test004-insertDot 测试************");
		assertEquals("1.234", LongNumber.insertDot("1234",3));
		assertEquals("0.001234", LongNumber.insertDot("1234",6));
		assertEquals("1234", LongNumber.insertDot("1234",0));
		
		assertEquals("123.4", LongNumber.insertDot("1234",1));
	}
	
	
	public void test005() {
		System.out.println("*********test005-removeEndZeroAfterDot 测试************");
		assertEquals("1234", LongNumber.removeEndZeroAfterDot("1234"));
		assertEquals("123.4", LongNumber.removeEndZeroAfterDot("123.4"));
		assertEquals("123.4", LongNumber.removeEndZeroAfterDot("123.400"));
		assertEquals("123", LongNumber.removeEndZeroAfterDot("123.000"));
		assertEquals("0", LongNumber.removeEndZeroAfterDot(".000"));
		
		
	}
	

	
	
	public void test007() {
		System.out.println("*********test001-正常用例************");
		String ret = LongNumber.bigNumberMultiple("080", "0.125");
		assertEquals("10", ret);
	}
	
	
	
	public void test009() {
		System.out.println("*********test003-正常用例-负数相乘************");
		String ret = LongNumber.bigNumberMultiple("-080", "-0.125");
		assertEquals("10", ret);
	}
	
	
	
	public void test010() {
		System.out.println("*********test004-正常用例-正数和负数相乘************");
		String ret = LongNumber.bigNumberMultiple("080", "-0.125");
		assertEquals("-10", ret);
	}
	
	
	public void test011() {
		System.out.println("*********test005-正常用例-两个超大数相乘************");
		String ret = LongNumber.bigNumberMultiple("8883485354352854.553434543544", "-4536536535435435.125");
		assertEquals("-40300255872027327518905221034985.969687399583", ret);
	}
	
	public void test012() {
		System.out.println("*********test005-正常用例-两个超大数相乘 +************");
		String ret = LongNumber.bigNumberMultiple("+8883485354352854.553434543544", "-4536536535435435.125");
		assertEquals("-40300255872027327518905221034985.969687399583", ret);
	}
	
	public void test013() {
		System.out.println("*********test007-非法字符串************");
		String ret = LongNumber.bigNumberMultiple("+4.55343+3544", "35.125");
		assertEquals(null, ret);
	}
	
	public void test014() {
		System.out.println("*********test008-非法字符串************");
		String ret = LongNumber.bigNumberMultiple("-4.553f433544", "35.125");
		assertEquals(null, ret);
	}
	

	
}
