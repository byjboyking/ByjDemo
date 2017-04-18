package jnjd.getMiniNumber;

import junit.framework.TestCase;

public class GetMiniNumberTest extends TestCase {

	public void test001(){
		System.out.println("********test001-基本用例**********");
		int[] input={20,201,203,2033,34};
		long expected = 20120203203334l;  // 应该是201开头
		assertEquals(expected, GetMiniNumber.getMiniNumber(input));
	}
	
	public void test002(){
		System.out.println("********test002-基本用例**********");
		int[] input={12,34,56,90};
		long expected = 12345690l;  
		assertEquals(expected, GetMiniNumber.getMiniNumber(input));
	}
	
	public void test010(){
		System.out.println("********test001-基本用例**********");
		int[] input={2,1};
		assertEquals(12, GetMiniNumber.getMiniNumber(input));
	}
	
	public void test003(){
		System.out.println("********test003-数字过大**********");
		int[] input={12,34,56,78,90,111,333,444,555};
		assertEquals(-1, GetMiniNumber.getMiniNumber(input));
	}
	
	public void test004(){
		System.out.println("********test004-基本用例**********");
		int[] input={120,1201,1,2};
		long expected = 112011202l;  
		assertEquals(expected, GetMiniNumber.getMiniNumber(input));
	}
	
	public void test005(){
		System.out.println("********test005-基本用例**********");
		int[] input={12,12,34,34};
		long expected = 12123434l;  
		assertEquals(expected, GetMiniNumber.getMiniNumber(input));
	}
}
