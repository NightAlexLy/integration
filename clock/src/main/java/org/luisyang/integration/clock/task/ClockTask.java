package org.luisyang.integration.clock.task;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.luisyang.integration.clock.constant.ClockConstant;
import org.luisyang.integration.clock.network.ClockURLUtils;
import org.luisyang.integration.clock.utils.DateUtils;
import org.luisyang.integration.clock.utils.StringUtils;

public class ClockTask extends AbstractTimingTask {

	
	
	@Override
	public void run() {

		Date date = new Date();
		System.out.println("----->" + DateUtils.getStrforDate(date));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.HOUR_OF_DAY) == 8
				&& (calendar.get(Calendar.MINUTE) >= 35 && calendar.get(Calendar.MINUTE) <= 54)) {
			sendRequest(ClockConstant.USERS);
			System.out.println("Clock  Success！！！！！！！！");

		}
	}

	public static Map<String, String> sendRequest(Map<String, String> user) {
		if (user != null && user.size() > 0) {
			Map<String, String> flags = new HashMap<String, String>();
			String[] names = StringUtils.covertToStringArr(user.keySet());
			Random random = new Random();
			int length = names.length;
			for(int i =0;i<length;i++){
				int num = random.nextInt(names.length);
				String name = names[num];
				names = ArrayUtils.removeElement(names, names[num]);
				try {
					Thread.sleep(1200 * 60);
					ClockURLUtils.clock(StringUtils.replace(name, ClockConstant.USERS.get(name)));
				} catch (Exception e) {
					flags.put(name, ClockConstant.USERS.get(name));
					System.err.println(e.getMessage());
				}	
			}
			return flags.size() > 0 ? sendRequest(flags) : null;
		}else{
			return null;
		}
	}
	
	/*public static void main(String[] args) {
		
		Map<String,String> users = new HashMap<String,String>();
		users.put("����", "123123213");
		users.put("����", "123123213");
		users.put("����", "123123213");
		users.put("����", "123123213");
		users.put("����", "123123213");
		users.put("С��", "123123213");
		users.put("ɵ��", "123123213");
		
		
		String[] names = StringUtils.covertToStringArr(users.keySet());
		Random random = new Random();
		int length = names.length;
		for(int i =0;i<length;i++){
			int num = random.nextInt(names.length);
			//System.out.println(num);
			System.out.println(names[num]);
			//System.out.println(Arrays.toString(names));
			names = ArrayUtils.removeElement(names, names[num]);
			//System.out.println(Arrays.toString(names));
		}
		names = ArrayUtils.removeElement(names, names[2]);
		System.out.println(Arrays.toString(names));
		names = ArrayUtils.removeElement(names, names[2]);
		System.out.println(Arrays.toString(names));
		
	}*/

}
