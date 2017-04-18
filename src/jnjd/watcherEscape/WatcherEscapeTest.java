package jnjd.watcherEscape;

import junit.framework.TestCase;

public class WatcherEscapeTest extends TestCase {

	private WatcherEscape oj = new WatcherEscape();
	
	public void test001(){
		System.out.println("*********test001->基本用例-时间足够的情况下， 全部使用魔法，可以逃出***************");
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(302,201,21,str);
		assertEquals("Yes", str.toString());
		assertEquals(4, result);
	}
	
	public void test002(){
		System.out.println("*********test002->基本用例-时间不足够的情况下， 全部使用魔法，不能逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(302,201,3,str);
		assertEquals("No", str.toString());
		assertEquals(180, result);
	}
	
	public void test003(){
		System.out.println("*********test003->基本用例-场景3：使用初始魔法点数耗费时间 超过了 可用时间，无法逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(42,2001,3,str);
		assertEquals("No", str.toString());
		assertEquals(180, result);
	}
	
	public void test004(){
		System.out.println("*********test004->初始魔法为0，使用魔法可以逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(0,120,7,str);
		assertEquals("Yes", str.toString());
		assertEquals(7, result);
	}
	
	public void test005(){
		System.out.println("*********test005->初始魔法为0，使用魔法不能逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(0,121,7,str);
		assertEquals("No", str.toString());
		assertEquals(120, result);
	}
	
	
	public void test006(){
		System.out.println("*********test006->初始魔法为12，使用魔法可以逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(12,120,9,str);
		assertEquals("Yes", str.toString());
		assertEquals(4, result);
	}
	
	public void test007(){
		System.out.println("*********test007->初始魔法为12，使用魔法不能逃出，时间不够***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(12,120,3,str);  //1:60; 2:17*2=34;
		assertEquals("No", str.toString());
		assertEquals(94, result);
	}
	
	public void test008(){
		System.out.println("*********test008->初始魔法为17，使用魔法可以逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(17,120,4,str); 
		assertEquals("Yes", str.toString());
		assertEquals(3, result);
	}
	
	public void test009(){
		System.out.println("*********test009->初始魔法为17，使用魔法无法逃出***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(17,1500,5,str);  //1:60;   2:60;  2:34;
		assertEquals("No", str.toString());
		assertEquals(154, result);
	}
	
	
	public void test010(){
		System.out.println("*********test010->初始魔法为11，时间不够，只能使用步行***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(11,1500,3,str);  //1:60;  2:34
		assertEquals("No", str.toString());
		assertEquals(94, result);
	}
	
	public void test011(){
		System.out.println("*********test011->初始魔法为11，时间不够，只能使用步行***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(11,1500,7,str);  //1:60;  6:102
		assertEquals("No", str.toString());
		assertEquals(162, result);
	}
	
	public void test012(){
		System.out.println("*********test012->***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(251,6724,458,str);  //1:60;  6:102
		assertEquals("Yes", str.toString());
		assertEquals(330, result);
	}

}
