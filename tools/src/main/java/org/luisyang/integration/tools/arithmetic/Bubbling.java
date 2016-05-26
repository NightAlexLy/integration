package org.luisyang.integration.tools.arithmetic;

/**
 * 冒泡排序算法
 * 
 * @author ly
 */
public class Bubbling {

	public static void main(String[] args) {

		int[] values = { 3, 1, 6, 2, 9, 0, 7, 4, 5 };

		sort(values);

		for (int i = 0; i < values.length; i++) {// 排序后打印数组中的元素

			System.out.println("Index: " + i + "  value: " + values[i]);

		}

	}

	private static void sort(int[] values) {
		// TODO Auto-generated method stub
		int temp;

		for (int i = 0; i < values.length; i++) {// 趟数

			for (int j = 0; j < values.length - i - 1; j++) {// 比较次数

				if (values[j] > values[j + 1]) {

					temp = values[j];

					values[j] = values[j + 1];

					values[j + 1] = temp;

				}

			}
		}
	}
}
