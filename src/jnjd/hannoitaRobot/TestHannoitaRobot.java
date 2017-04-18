package jnjd.hannoitaRobot;


import org.junit.Test;
import static org.junit.Assert.*;


public class TestHannoitaRobot  {
	HannoitaRobot oj = new HannoitaRobot();

	@Test
	public void test01() {
		System.out.println("***********test01->基本用例***************");
		
		int size=3;
		IControlable controller = new Cargador(size,1,0,2);
		oj.manager(controller);
		IGetResult getRes = (IGetResult)controller;
		for(int i=1;i<size;i++){
			assertEquals(i, getRes.popResult());
		}
		
	}
	
	@Test
	public void test02() {
		System.out.println("***********test02->基本用例***************");
		
		int size=24;
		IControlable controller = new Cargador(size,1,0,2);
		oj.manager(controller);
		IGetResult getRes = (IGetResult)controller;
		for(int i=1;i<size;i++){
			assertEquals(i, getRes.popResult());
		}
		
	}
	
}
