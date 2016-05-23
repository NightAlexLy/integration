package org.luisyang.spring.basic.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Map ctxPropertiesMap;
	 
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     Properties props) throws BeansException {
        ctxPropertiesMap = new HashMap();
        for (Object key : props.keySet()) {
        	if(key.equals("a")){
        		props.setProperty(key.toString(), "abc");
        	}
        	if(key.equals("jdbc.password")){
        		props.setProperty(key.toString(), "");
        	}
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
        System.out.println(props.toString());
        //重新加载配置
        super.processProperties(beanFactoryToProcess, props);
    }
    public static Object getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
	
}
