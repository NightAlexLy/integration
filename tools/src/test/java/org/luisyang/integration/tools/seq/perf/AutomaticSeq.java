package org.luisyang.integration.tools.seq.perf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.luisyang.integration.tools.domian.SeqConfig;
import org.luisyang.integration.tools.properties.PropTest;
import org.luisyang.integration.tools.util.DBHelper;

/**
 * 自动生成sequence并保存在文件中
 * 
 * @author ly
 */
public class AutomaticSeq {

	public static final String DEFAULT_KEY = "seq_test";
	public static Map<Integer, File> fileMap = new HashMap<Integer, File>();
	public static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	public static void main(String[] args) throws Exception {

		SeqConfig config = PropTest.getPropByFile("seq.properties", SeqConfig.class);
		System.out.println("开启线程数：------>" + config.getThreadNum());
		System.out.println("文件输出位置：------>" + config.getFilePath());
		String key = DEFAULT_KEY;
		if(config.getSeqId() != null && config.getSeqId().length() > 0)
			key = config.getSeqId();
		
		for (int i = 0; i < config.getThreadNum(); i++) {
			fileMap.put(i, createFile(config.getFilePath(), i));
			new Thread(new TestSequence(i,key)).start();
		}

	}
	

	/**
	 * 根据路径及线程编号自动生成文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param threadNum
	 *            线程编号
	 */
	private static File createFile(String filePath, int threadNum) {

		StringBuffer sb = new StringBuffer();
		sb.append(filePath).append(File.separator).append("sequence_").append(threadNum).append(".log");

		File file = new File(sb.toString());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}

class TestSequence implements Runnable {

	private int num;
	private String key;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(AutomaticSeq.fileMap.get(this.num)));
			while(true){
				writer.write(AutomaticSeq.format.format(System.currentTimeMillis()) + "------>"
					+ DBHelper.getSeqenceById("select nextval(?) as value", this.getKey()));
				writer.newLine();
				writer.flush();
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					writer = null;
				}
			}
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public TestSequence(int num, String key) {
		super();
		this.num = num;
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TestSequence() {
		super();
		// TODO Auto-generated constructor stub
	}
}