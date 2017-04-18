package jnjd.wordGame;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWordGame {
	
	

	

//	@Test
//	public void test02_best1() {
//		System.out.println("******test02-基本用例");
//		assertEquals(1, WordGameBest1.who("boa"));
//		
//		assertEquals(false, WordGameBest1.isLowerChar("aBc"));
//		
//		assertEquals(false, WordGameBest1.isStrictIncrease("aaa"));
//		assertEquals(true, WordGameBest1.isStrictIncrease("abc"));
//		
//		assertEquals(false, WordGameBest1.isStrictIncrease("cza"));
//		
//		assertEquals(true, WordGameBest1.isStrictIncrease("c"));
//		
//		assertEquals(1, WordGameBest1.who("bad"));
//		assertEquals(0, WordGameBest1.who("aaa"));
//		
//		assertEquals(1, WordGameBest1.who("aaaa"));
//		
//		
//		assertEquals(1, WordGameBest1.who("cbad"));
//		
//		assertEquals(0, WordGameBest1.who("cba"));
//	}
	
	@Test
	public void test02_best2() {
		System.out.println("******test02-基本用例");
		assertEquals(1, WordGameBest2.who("boa"));
//		assertEquals(1, WordGameBest2.who("bad"));
//		assertEquals(0, WordGameBest2.who("aaa"));
//		assertEquals(1, WordGameBest2.who("aaaa"));
//		assertEquals(1, WordGameBest2.who("cbad"));
//		assertEquals(0, WordGameBest2.who("cba"));
	}

}
