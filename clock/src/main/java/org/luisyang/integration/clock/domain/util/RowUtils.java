package org.luisyang.integration.clock.domain.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.luisyang.integration.clock.domain.Row;
import org.luisyang.integration.clock.domain.comparable.RowComparable;

public class RowUtils {

	private static RowComparable rowCompar = new RowComparable();

	private RowUtils() {
	}

	/**
	 * 打印
	 * 
	 * @param rows
	 */
	public static void printRows(List<Row> rows) {

		for (int i = 0; i < rows.size(); i++) // 外循环是循环的次数
		{
			for (int j = rows.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
			{

				if (rows.get(i).equals(rows.get(j))) {
					rows.remove(j);
				}

			}
		}
		for (Row row : rows) {
			System.out.println(Arrays.toString(row.getCell()));
		}

	}

	@SuppressWarnings("static-access")
	public static void printAndSortRows(List<Row> rows) {
		rowCompar.sortASC = false;
		rowCompar.sortByDate = true;
		Collections.sort(rows, rowCompar);
		printRows(rows);
	}
}
