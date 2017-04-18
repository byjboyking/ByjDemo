package jnjd.frogDate;

import junit.framework.TestCase;

public class TestFrogDate extends TestCase {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	public void test001() {
		System.out.println("*********test001-正常用例************");
		assertEquals("4", FrogDate.judgeMeet(new long[]{1,2,3,4,5}));
	}
	
	public void test002() {
		System.out.println("*********test002-正常用例************");
		assertEquals("3", FrogDate.judgeMeet(new long[]{18,4,3,1,10}));
	}
	
	//******************华丽的分界线**********************
	
	public void test003() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test003-正常用例-一次就碰面************");
		//   0______1_______2_______3_______4_______5
		//        pos2(4)        pos1(2)
		//        
		assertEquals("1", FrogDate.judgeMeet(new long[]{3,1,2,4,5}));
	}
	
	public void test004() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test004-正常用例-一次就碰面************");
		//   0______1_______2_______3_______4_______5
		//        pos1(1)        pos2(4)
		//        
		assertEquals("1", FrogDate.judgeMeet(new long[]{1,3,1,4,5}));
	}
	
	public void test005() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test005-正常用例-步长超过L************");
		//   0______1_______2_______3_______4_______5
		//        pos1(1)        pos2(9)
		//        
		assertEquals("1", FrogDate.judgeMeet(new long[]{1,3,1,9,5}));
	}
	
	public void test006() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test006-步长相同-Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,3,14,9,5}));
	}
	
	public void test007() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test007-输入参数异常-Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(null));
	}
	
	public void test008() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test008-输入参数异常-Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,3,9,5}));
	}
	
	public void test009() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test009-输入参数异常-x==y, Impossible************");
		//   0______1_______2_______3_______4_______5
		//        pos1(14)        pos2(9)
		//        
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,1,3,9,5}));
	}
	
	public void test010() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test010-正常用例-走n次后又回到最初的位置,Impossible************");
		//   0______1_______2_______3_______4_______5________6
		//        pos1(3)                 pos2(1)
		//                                pos1     pos2
		//        pos1                                      pos2
		//        pos2                    pos1
		//        pos1     pos2
		//                         pos2   pos1
		//        pos1                    pos2  (回到初始位置)
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,4,3,1,6}));
	}

	public void test011() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test011-正常用例-L足够大，走很长时间才追上************");
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,4,3,1,2000000000}));
	}
	
	public void test012() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test012-正常用例-pos==L************");
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{10,0,3,1,10}));
	}
	
	public void test013() {
		//pos1,pos2,step1,step2,L
		System.out.println("*********test013-正常用例-************");
		assertEquals("Impossible", FrogDate.judgeMeet(new long[]{1,0,3,1,10}));
	}
	
}
