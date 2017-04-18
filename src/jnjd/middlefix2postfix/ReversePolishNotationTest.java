package jnjd.middlefix2postfix;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import jnjd.middlefix2postfix.ReversePolishNotation.SyntaxErrorException;
import junit.framework.TestCase;

public class ReversePolishNotationTest  {

	public static class Formater1 {
		
		//  \\s   ±íÊ¾¿Õ°××Ö·û£¨space£¬ TAB µÈ£©
		final static Pattern ws1 = Pattern.compile("^\\s+|\\s+$");
		final static Pattern ws2 = Pattern.compile("\\s+");
		final static Pattern op = Pattern.compile("\\s*([\\+\\-\\*/])\\s*");

		public static String normalize(String str) {
			str = ws1.matcher(str).replaceAll("");
			str = ws2.matcher(str).replaceAll(" ");
			Matcher m = op.matcher(str);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, m.group(1));
			}

			m.appendTail(sb);
			return sb.toString();
		}

	}
//
	
	
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case03() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case04() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert(null);
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case05() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("1 + 1");
	}
	
	@Test
	public void test_case01() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		//assertEquals(Formater1.normalize(instance.convert("1 + 1 $")), "1 1+");
		
		assertEquals(instance.convert("1 + 1 $"), "1 1+");
	}

	@Test
	public void test_case06() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		//assertEquals(Formater1.normalize(instance.convert("5 + ((1 + 2)*4)-3$")), "5 1 2+4*+3-");
		assertEquals(instance.convert("5 + 2 - 3$"), "5 2+3-");
	}
	
	
	@Test
	public void test_case02() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		assertEquals(Formater1.normalize(instance.convert("5 + ((1 + 2)*4)-3$")), "5 1 2+4*+3-");
		
		//assertEquals(instance.convert("5 + ((1 + 2)*4)-3$"), "5 1 2+4*+3-");
	}
	
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case07() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("1 + $");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case08() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("1 + (1$");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case09() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("1 + 3 -$");
	}
	
	@Test
	public void test_case10() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		//assertEquals(Formater1.normalize(instance.convert("5 + ((1 + 2)*4)-3$")), "5 1 2+4*+3-");
		assertEquals(instance.convert("555 + 222 - 333$"), "555 222+333-");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case11() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("1 + 03 $");
	}
	
	@Test(expected=SyntaxErrorException.class)
	public void test_case12() throws SyntaxErrorException {
		ReversePolishNotation instance = new ReversePolishNotation();
		instance.convert("1 + + 3 $");
	}
	
}
