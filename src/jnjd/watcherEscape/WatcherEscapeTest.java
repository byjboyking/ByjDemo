package jnjd.watcherEscape;

import junit.framework.TestCase;

public class WatcherEscapeTest extends TestCase {

	private WatcherEscape oj = new WatcherEscape();
	
	public void test001(){
		System.out.println("*********test001->��������-ʱ���㹻������£� ȫ��ʹ��ħ���������ӳ�***************");
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(302,201,21,str);
		assertEquals("Yes", str.toString());
		assertEquals(4, result);
	}
	
	public void test002(){
		System.out.println("*********test002->��������-ʱ�䲻�㹻������£� ȫ��ʹ��ħ���������ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(302,201,3,str);
		assertEquals("No", str.toString());
		assertEquals(180, result);
	}
	
	public void test003(){
		System.out.println("*********test003->��������-����3��ʹ�ó�ʼħ�������ķ�ʱ�� ������ ����ʱ�䣬�޷��ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(42,2001,3,str);
		assertEquals("No", str.toString());
		assertEquals(180, result);
	}
	
	public void test004(){
		System.out.println("*********test004->��ʼħ��Ϊ0��ʹ��ħ�������ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(0,120,7,str);
		assertEquals("Yes", str.toString());
		assertEquals(7, result);
	}
	
	public void test005(){
		System.out.println("*********test005->��ʼħ��Ϊ0��ʹ��ħ�������ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(0,121,7,str);
		assertEquals("No", str.toString());
		assertEquals(120, result);
	}
	
	
	public void test006(){
		System.out.println("*********test006->��ʼħ��Ϊ12��ʹ��ħ�������ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(12,120,9,str);
		assertEquals("Yes", str.toString());
		assertEquals(4, result);
	}
	
	public void test007(){
		System.out.println("*********test007->��ʼħ��Ϊ12��ʹ��ħ�������ӳ���ʱ�䲻��***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(12,120,3,str);  //1:60; 2:17*2=34;
		assertEquals("No", str.toString());
		assertEquals(94, result);
	}
	
	public void test008(){
		System.out.println("*********test008->��ʼħ��Ϊ17��ʹ��ħ�������ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(17,120,4,str); 
		assertEquals("Yes", str.toString());
		assertEquals(3, result);
	}
	
	public void test009(){
		System.out.println("*********test009->��ʼħ��Ϊ17��ʹ��ħ���޷��ӳ�***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(17,1500,5,str);  //1:60;   2:60;  2:34;
		assertEquals("No", str.toString());
		assertEquals(154, result);
	}
	
	
	public void test010(){
		System.out.println("*********test010->��ʼħ��Ϊ11��ʱ�䲻����ֻ��ʹ�ò���***************");
		//magic, distance, time
		StringBuilder str = new StringBuilder();
		int result = oj.helpWatcherEscape(11,1500,3,str);  //1:60;  2:34
		assertEquals("No", str.toString());
		assertEquals(94, result);
	}
	
	public void test011(){
		System.out.println("*********test011->��ʼħ��Ϊ11��ʱ�䲻����ֻ��ʹ�ò���***************");
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
