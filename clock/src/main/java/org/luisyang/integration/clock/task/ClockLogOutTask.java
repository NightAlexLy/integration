package org.luisyang.integration.clock.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.luisyang.integration.clock.constant.ClockConstant;
import org.luisyang.integration.clock.network.ClockURLUtils;
import org.luisyang.integration.clock.statistics.OAStatistics;
import org.luisyang.integration.clock.utils.DateUtils;
import org.luisyang.integration.clock.utils.StringUtils;

public class ClockLogOutTask extends AbstractTimingTask {

	private String timing;
	private String logOutName;
	private String password;
	
	
	public ClockLogOutTask() {
	}
	
	public ClockLogOutTask(String timing, String logOutName,String password) {
		this.timing = timing;
		this.logOutName = logOutName;
		this.password = password;
	}

	@Override
	public void run() {

		Date date = DateUtils.getDate();
		//��ʱ��ʱ��
		Date date2 = DateUtils.formDate(timing, null);
		long difference = date2.getTime() - date.getTime();
		if(difference > 0 && difference <= 2 * 60 * 1000){
			
			String[] names = logOutName.split(",");
			for(String name : names){
				OAStatistics.logout(name, password);
			}
			System.out.println("Timing Clock Success!!! By" + DateUtils.getStrforDate(date));
		}
		
	}

	public static Map<String, String> sendRequest(Map<String, String> user) {
		if (user != null && user.size() > 0) {
			Map<String, String> flags = new HashMap<String, String>();
			for (String key : user.keySet()) {
				try {
					Thread.sleep(1200 * 60);
					ClockURLUtils.clock(StringUtils.replace(key, ClockConstant.USERS.get(key)));
				} catch (Exception e) {
					flags.put(key, ClockConstant.USERS.get(key));
					System.err.println(e.getMessage());
				}
			}
			return flags.size() > 0 ? sendRequest(flags) : null;
		}else{
			return null;
		}
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public void setLogOutName(String logOutName) {
		this.logOutName = logOutName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
