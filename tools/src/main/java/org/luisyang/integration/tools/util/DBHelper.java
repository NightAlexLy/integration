package org.luisyang.integration.tools.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.luisyang.integration.tools.domian.User;

public class DBHelper {

	private static final String JDBC_PROPERTEIES = "jdbc.properties";

	private static String URL = "";
	private static String USERNAME = "";
	private static String PASSWORD = "";
	
	private static Connection connection = null;

	static {
		Properties properties = new Properties();
		try {
			properties.load(DBHelper.class.getClassLoader().getResourceAsStream(JDBC_PROPERTEIES));
			// properties.load(new FileInputStream(new File(JDBC_PROPERTEIES)));
			// System.out.println(properties.size());
			initJDBCInfo(properties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 返回连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	/**
	 * 关闭资源
	 * 
	 * @param connection
	 * @param statement
	 * @param set
	 */
	public static void close(Connection connection, PreparedStatement statement, ResultSet set) {

		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				set = null;
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				statement = null;
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				connection = null;
			}
		}
	}

	/**
	 * 初始化属性
	 * 
	 * @param properties
	 */
	private static void initJDBCInfo(Properties properties) {
		URL = properties.getProperty("jdbc.url");
		USERNAME = properties.getProperty("jdbc.user");
		PASSWORD = properties.getProperty("jdbc.password");
	}

	/**
	 * 执行insert update delete语句
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static int executeSQL(String sql, Object... args) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			statement.setObject(i + 1, args[i]);
		}
		int result = statement.executeUpdate();
		close(connection, statement, null);
		return result;
	}
	
	/**
	 * 根据SequenceId获得下一个值
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static int getSeqenceById(String sql, Object... args) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			statement.setObject(i + 1, args[i]);
		}
		ResultSet query = statement.executeQuery();
		ResultSetMetaData md =  query.getMetaData();
		int columnCount = md.getColumnCount();
		int index = 1;
		while (query.next()) {
			// 将集合放在map中
			for (int i = 1; i <= columnCount; i++) {
				index = Integer.valueOf(query.getObject(i).toString());
				break;
			}
		}
		close(connection, statement, null);
		return index;
	}

	@SuppressWarnings("rawtypes")
	public static <T> List<T> executeQuery(String sql, Class T, Object... args) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			statement.setObject(i + 1, args[i]);
		}
		ResultSet query = statement.executeQuery();
		List<T> list = null;
		try {
			list = resultSetToT(query, T);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(connection, statement, null);
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T> List<T> resultSetToT(ResultSet query, Class T) throws SQLException, InstantiationException, ReflectiveOperationException {
		// TODO Auto-generated method stub
		if (query == null)
			return Collections.EMPTY_LIST;
		ResultSetMetaData md =  query.getMetaData();
		int columnCount = md.getColumnCount();
		List<T> list = new ArrayList<T>();
		// 将map放入集合中方便使用个别的查询
		while (query.next()) {
			T t = (T) T.newInstance();
			// 将集合放在map中
			for (int i = 1; i <= columnCount; i++) {
				String name = md.getColumnName(i);
				Method method = t.getClass().getMethod("set"+String.valueOf(name.charAt(0)).toUpperCase()+name.substring(1),getFieldType(T,name));
				method.invoke(t, query.getObject(i));
			}
			list.add(t);
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	private static Class getFieldType(Class T,String name){
		Field[] declaredFields = T.getDeclaredFields();
		for (Field field : declaredFields) {
			if(field.getName().equals(name))
				return field.getType();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {

		List<User> users = executeQuery("select * from user where id = 1", User.class);
		for(User user : users){
			System.out.println(user.toString());
		}

		System.out.println(DBHelper.getSeqenceById("select nextval(?) as value","test"));;
	}
}
