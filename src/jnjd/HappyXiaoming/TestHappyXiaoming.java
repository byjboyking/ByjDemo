package jnjd.HappyXiaoming;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestHappyXiaoming {

	@Before
	public void setUp() throws Exception {
		instance =  new HappyXiaoming_Best();
	}
	private HappyXiaoming_Best instance;
	
	@Test
	public void test01() {
		System.out.println("***********test01->基本用例***************");
		
		int[][] a = { 
				{300,4},
				{200,1},
				{100,3},
				{150,1},
				{50,5}
		};
		assertEquals(700, instance.getMaxWeight(a));
	}

	
	@Test
	public void test02() {
		System.out.println("***********test02->压力测试用例***************");
		int[][] a = { 
				{4700,24},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				{200,2},
				
				{200,2},
				{200,2},
				{200,2},
				{200,2}
		};
		assertEquals(9200, instance.getMaxWeight(a));
	}
}
