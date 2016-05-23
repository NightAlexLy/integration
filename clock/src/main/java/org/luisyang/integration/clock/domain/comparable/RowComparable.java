package org.luisyang.integration.clock.domain.comparable;

import java.util.Comparator;

import org.luisyang.integration.clock.domain.Row;

public class RowComparable implements Comparator<Row> {

	// 对象的排序方式[升、降]
	public static boolean sortASC = true;

	// 对象的排序属性
	// public static boolean sortByName = false;
	public static boolean sortByDate = true;
	// public static boolean sortByPrice = false;

	@Override
	public int compare(Row o1, Row o2) {
		String[] cell1 = o1.getCell();
		String[] cell2 = o2.getCell();

		int result = 0;
		if (sortASC) {
			if (sortByDate) {
				Integer day1 = Integer.valueOf(cell1[1].substring(6, 8));
				Integer day2 = Integer.valueOf(cell2[1].substring(6, 8));
				result = day1.compareTo(day2);
			}
		} else {
			if (sortByDate) {
				Integer day1 = Integer.valueOf(cell1[1].substring(6, 8));
				Integer day2 = Integer.valueOf(cell2[1].substring(6, 8));
				result = -day1.compareTo(day2);
			}
		}
		return result;
	}
}
