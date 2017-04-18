package jnjd.StringSplit;

import java.util.Arrays;

import junit.framework.TestCase;

public class StringSplitTest extends TestCase {

	public void testcase001(){
		String[] input={"434353f","ABCDEFG1234","1234567890"};
		String[] expect={"434353f0","ABCDEFG1","23400000","12345678","90000000"};

		assertTrue(Arrays.equals(expect,StringSplit.splitString(input)));
	}
	
	public void testcase002(){
		String[] input={"12345678","123456789",};
		String[] expect={"12345678","12345678","90000000"};

		assertTrue(Arrays.equals(expect,StringSplit.splitString(input)));
	}
}
