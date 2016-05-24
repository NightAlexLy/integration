package org.luisyang.integration.tools.seq.perf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.luisyang.integration.tools.domian.SeqConfig;
import org.luisyang.integration.tools.properties.PropTest;

/**
 * 性能分析
 * 
 * @author ly
 */
public class PerformanceAnalysis {

	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static List<Integer> sequences = new ArrayList<Integer>();
	public static int rows = 0;
	public static int statisticNum = 0;

	public static void main(String[] args) throws Exception {

		SeqConfig config = PropTest.getPropByFile("seq.properties", SeqConfig.class);

		CountDownLatch begin = new CountDownLatch(1);
		CountDownLatch end = new CountDownLatch(config.getThreadNum() * 1);

		TestAnalysis[] threads = new TestAnalysis[config.getThreadNum() * 1];

		for (int i = 0; i < config.getThreadNum() * 1; i++) {
			// threads[2*i] = new TestAnalysis(75, "cib-seq-test-1", i+1,
			// config.getFilePath(), begin, end);
			// threads[2*i+1] = new TestAnalysis(76, "cib-seq-test-1", i+1,
			// config.getFilePath(), begin, end);
			threads[i] = new TestAnalysis(75, config.getSeqId(), i, config.getFilePath(), begin, end);
		}

		ExecutorService exec = Executors.newFixedThreadPool(config.getThreadNum() * 1);
		for (TestAnalysis analysis : threads)
			exec.execute(analysis);
		begin.countDown();
		end.await();
		int[] arr = getValuesByMap(map);
		if (arr != null && arr.length > 1) {
			Arrays.sort(arr);
			System.err.println("总执行分钟:::" + map.size());
			System.err.println("文件总行数:::" + rows + "---->总的读取行数:::" + statisticNum);
			System.err.println(
					"最小TPS:::" + arr[1] + "---->最大TPS:::" + arr[arr.length - 2] + "---->平均TPS:::" + average(arr));

			// 验证是否递增
			isIncrease();

		}
		exec.shutdown();

	}

	private static void isIncrease() {
		// TODO Auto-generated method stub
		int[] arr = getArrayByList(sequences);
		Arrays.sort(arr);
		int j = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != j) {
				try {
					throw new Exception("sequences is not Increase....");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(1);
				}
			}
			j++;
		}
		System.err.println("sequences is Increase....");
	}

	private static int[] getArrayByList(List<Integer> list) {
		// TODO Auto-generated method stub
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	private static int[] getValuesByMap(Map<String, Integer> map2) {
		// TODO Auto-generated method stub
		int[] arr = new int[map2.size()];
		int idx = 0;
		for (String key : map2.keySet()) {
			arr[idx] = map2.get(key);
			statisticNum += arr[idx];
			idx++;
		}
		return arr;
	}

	public synchronized static void autoIncrease(String key, Integer sequence) {
		// TODO Auto-generated method stub
		Integer value = map.get(key);
		if (value != null) {
			map.put(key, value + 1);
		} else {
			map.put(key, 1);
		}
		sequences.add(sequence);
	}

	public static int average(int[] arr) {
		// TODO Auto-generated method stub

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i == 0 || i == arr.length - 1)
				continue;
			sum += arr[i];
		}

		return sum / (arr.length - 2);
	}
}

class TestAnalysis implements Runnable {

	private int ip;
	private String key;
	private int num;
	private CountDownLatch begin;
	private CountDownLatch end;
	private String path;

	public TestAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestAnalysis(int ip, String key, int num, String path, CountDownLatch begin, CountDownLatch end) {
		super();
		this.ip = ip;
		this.key = key;
		this.num = num;
		this.path = path;
		this.begin = begin;
		this.end = end;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public CountDownLatch getBegin() {
		return begin;
	}

	public void setBegin(CountDownLatch begin) {
		this.begin = begin;
	}

	public CountDownLatch getEnd() {
		return end;
	}

	public void setEnd(CountDownLatch end) {
		this.end = end;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		try {
			begin.await();
			br = new BufferedReader(new FileReader(new File(this.getFilePath())));
			int num = 0;
			Map<String, Integer> tempMap = new HashMap<String, Integer>();
			while (br.ready()) {
				String readLine = br.readLine();
				String[] tempArrStr = readLine.split("------>");
				String date = tempArrStr[0];
				String key = date.substring(0, date.length() - 7);
				PerformanceAnalysis.autoIncrease(key, Integer.valueOf(tempArrStr[1]));
				this.autoIncrease(tempMap, key);
				num++;
			}
			PerformanceAnalysis.rows += num;
			System.out.println(this.getFilePath() + "----->>文件读取完毕,总行数::" + num);
			this.analysis(tempMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					br = null;
				}
			}
			end.countDown();
		}
	}

	private void analysis(Map<String, Integer> tempMap) {
		// TODO Auto-generated method stub

		int[] arr = this.getValuesByMap(tempMap);
		if (arr != null && arr.length > 1) {
			Arrays.sort(arr);
			printTps(arr, PerformanceAnalysis.average(arr));
		}

	}

	private void printTps(int[] arr, int averageNum) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("--->机器IP:::").append(this.getIp()).append("--->ThreadNum:::").append(this.getNum())
				.append("--->最小TPS:::").append(arr[0]).append("--->最大TPS:::").append(arr[arr.length - 2])
				.append("--->平均TPS:::").append(averageNum);
		System.out.println(sb.toString());
		System.out.println("");
		sb = null;
	}

	private int[] getValuesByMap(Map<String, Integer> tempMap) {
		// TODO Auto-generated method stub
		int[] arr = new int[tempMap.size()];
		int idx = 0;
		for (String key : tempMap.keySet()) {
			arr[idx] = tempMap.get(key);
			idx++;
		}
		return arr;
	}

	private void autoIncrease(Map<String, Integer> tempMap, String key2) {
		// TODO Auto-generated method stub
		Integer value = tempMap.get(key);
		if (value != null) {
			tempMap.put(key, value + 1);
		} else {
			tempMap.put(key, 1);
		}
	}

	private String getFilePath() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(this.getPath()).append(File.separator).append(this.getKey()).append(File.separator)
				.append(this.getIp()).append(File.separator).append("sequence_").append(this.getNum()).append(".log");
		return sb.toString();
	}

}