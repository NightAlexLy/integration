package org.luisyang.integration.log.log4j;

import org.apache.log4j.Logger;

/**
 * log4j测试类：
 * 	
 * 		<最详细的Log4j使用教程> http://www.codeceo.com/article/log4j-usage.html
 * 		<eclipse, Log4j配置（真心的详细～）>http://www.cnblogs.com/alipayhutu/archive/2012/06/21/2558249.html
 * 
 * 	Log4j官网：http://logging.apache.org/log4j/2.x/javadoc.html
 *  
 *  配置文件存放位置：resources/log4j.properties
 * 
 * @author ly
 */
public class Log4jTest {

	private static Logger logger = Logger.getLogger(Log4jTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("This is println message.");

		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
		// 记录warn级别的信息
		logger.warn("This is warn message.");
		
		try {
			System.out.println(1/0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		}
	}

}
