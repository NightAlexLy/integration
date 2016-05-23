package org.luisyang.integration.clock.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StringUtils {

	public static String EMTRY = "";
	
	/**
	 * username=kevin&password=12345
	 * @param username
	 * @param password
	 * @return
	 */
	public static String replace(String username,String password){
		StringBuffer sb = new StringBuffer();
		
		sb.append("userName=")
		.append(username)
		.append("&password=")
		.append(password);
		
		return sb.toString();
	}

	public static String[] covertToStringArr(List<String> list) {
		String[] strArr = new String[list.size()];
		int index = 0;
		for(String str : list){
			strArr[index++] = str;
		}
		return strArr;
	}
	
	public static String[] covertToStringArr(Set<String> list) {
		String[] strArr = new String[list.size()];
		int index = 0;
		for(String str : list){
			strArr[index++] = str;
		}
		return strArr;
	}
	
	
	public static List<String> covertToListByArr(String[] list) {
		List<String> arrayList = new ArrayList<String>();
		for(String str : list){
			arrayList.add(str);
		}
		return arrayList;
	}
	
	public static boolean isEmtry(String str){
		if(str == null || str.length() == 0){
			return false;
		}
		return true;
	}

}
