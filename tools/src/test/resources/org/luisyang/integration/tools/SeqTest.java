package org.luisyang.integration.tools;

import java.sql.Connection;
import java.sql.DriverManager;

import junit.framework.TestCase;

/**
 * 分布式唯一ID测试
 * 
 * @author ly
 */
public class SeqTest extends TestCase{

	private static final String URL = "jdbc:mysql://192.168.91.135:3306/test?useUnicode=yes&characterEncoding=utf8";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static Connection connection = null;
	
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void setUp() throws Exception {
		connection = DriverManager.getConnection(URL, USER, PASSWORD);
		super.setUp();
	}
	
	public void testA(){
		System.out.println(connection);
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		if(connection != null){
			connection.close();
			connection = null;
		}
		
	}
}
