package org.luisyang.integration.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则表达式
 * 
 * @author ly
 */
public class RegularExpressionTest {

	@Test
	public void testExpression() {

		String jdbcPass = "jdbc.db_0.password";

		Pattern pattern = Pattern.compile("^jdbc[a-zA-z0-9_.]+password$");
		Matcher matcher = pattern.matcher(jdbcPass);
		boolean b = matcher.matches();
		
		System.out.println(b);
	}
}
