package org.luisyang.integration.tools.domian;

/**
 * seq_config
 * 
 * @author ly
 */
public class SeqConfig {

	/**
	 * 文件夹路径
	 */
	private String filePath;
	/**
	 * 线程数
	 */
	private int threadNum;
	/**
	 * Key
	 */
	private String seqId;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	@Override
	public String toString() {
		return "SeqConfig [filePath=" + filePath + ", threadNum=" + threadNum + ", seqId=" + seqId + "]";
	}

}
