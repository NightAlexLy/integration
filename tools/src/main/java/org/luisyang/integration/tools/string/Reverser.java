package org.luisyang.integration.tools.string;

/**
 * 反转字符串
 * 
 * @author apple
 */
public class Reverser {
	

	/**
	 * 反转字符串方法
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		
		//1.第一种方式：采用JDK中StringBuffer的反转方法，它不仅速度快，效率高。
//		if( (str == null) || (str.length() <= 1) ){
//			return str;
//		}
//		return new StringBuffer(str).reverse().toString();
		
		//2.第二种方式：采用递归的方式
		/**StringBuffer result = new StringBuffer(str);
		for (int i = 0; i < (str.length() / 2); i++) {
			int swapIndex = str.length() - 1 - i;
			char swap = result.charAt(swapIndex);
			result.setCharAt(swapIndex, result.charAt(i));
			result.setCharAt(i, swap);
		}
		return result.toString();*/
		
		//3.第三种方式：采用数组的方式
		/**char[] chars = str.toCharArray();
		int right = chars.length - 1;
		for (int left = 0; left < right; left++) {
			char swap = chars[left];
			chars[left] = chars[right];
			chars[right--] = swap;
		}
		return new String(chars);*/
		
		//4.第四种方式：StringBUuffer追加的方式
		StringBuffer sb = new StringBuffer(str.length());
		for (int i = str.length() - 1; i >= 0; i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(reverse("abc"));
		
	}
}
