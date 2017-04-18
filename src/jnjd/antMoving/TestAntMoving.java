package jnjd.antMoving;

import junit.framework.TestCase;

public class TestAntMoving extends TestCase {

	double [] pos = {3,7,11,17,23};
	double length = 27;
	double  speed =1;
	
	
	public void test001() {
		System.out.println("*********test001-��������************");
		double [] ret = AntMoving.calcAvergeTime(length, pos, speed);
		assertEquals(7, ret[0],0.000001f);
		assertEquals(20, ret[1],0.000001f);
	}
	
	public void test002() {
		System.out.println("*********test002-��������************");
		assertEquals(17, AntMoving.leaveTime(length, pos, speed, 0),0.000001f);
	}
}
