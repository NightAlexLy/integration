package org.luisyang.integration.tools.properties;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import org.luisyang.integration.tools.domian.SeqConfig;

/**
 *
 * properties文件属性获取
 * 
 * @author ly
 */
public class PropTest {
	
	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			//加载文件配置
			properties.load(PropTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
			System.out.println(properties.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Properties getPropByFile(String fileName) throws IOException{
		Properties properties = new Properties();
		//加载文件配置
		properties.load(PropTest.class.getClassLoader().getResourceAsStream(fileName));
		return properties;
	}

	public static SeqConfig getPropByFile(String fileName, Class<SeqConfig> class1) throws Exception {
		Properties properties = new Properties();
		//加载文件配置
		properties.load(PropTest.class.getClassLoader().getResourceAsStream(fileName));
		SeqConfig config = class1.newInstance();
		for(Object key : properties.keySet()){
			String name = key.toString();
			Class clazz = getFieldType(class1,name);
			Method method = config.getClass().getMethod("set"+String.valueOf(name.charAt(0)).toUpperCase()+name.substring(1),clazz);
			if(clazz.getName().equals("int")){
				method.invoke(config, Integer.valueOf(properties.get(key).toString()));
			}else {
				method.invoke(config, properties.get(key));
			}
		}
		return config;
	}
	

	/**
	 * 遗留问题： 
	 * 	1.如果参数为多个的情况下，怎么处理？
	 * @param T
	 * @param name
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Class getFieldType(Class T,String name){
		Field[] declaredFields = T.getDeclaredFields();
		for (Field field : declaredFields) {
			if(field.getName().equals(name))
				return field.getType();
		}
		return null;
	}

}