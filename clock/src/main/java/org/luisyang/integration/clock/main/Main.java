package org.luisyang.integration.clock.main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;

import org.luisyang.integration.clock.constant.ClockConstant;
import org.luisyang.integration.clock.statistics.OAStatistics;
import org.luisyang.integration.clock.task.AbstractTimingTask;
import org.luisyang.integration.clock.task.ClockLogOutTask;
import org.luisyang.integration.clock.task.ClockTask;
import org.luisyang.integration.clock.utils.StringUtils;

public class Main {

	static {
		Properties prop = new Properties();
		try {
			prop.load(Main.class.getClassLoader().getResourceAsStream("clock.properties"));
			for (Object key : prop.keySet()) {
				if ("loginUrl".equals(key.toString())) {
					ClockConstant.LOGIN_URL = prop.getProperty("loginUrl");
				}
				if (key.toString().contains("user")) {
					String val = (String) prop.get(key);
					String[] tmpStrArr = val.split(",");
					ClockConstant.USERS.put(tmpStrArr[0], tmpStrArr[1]);
				}
				if(key.toString().contains("queryName")){
					String val = (String)prop.get(key);
					ClockConstant.NAMES.addAll(StringUtils.covertToListByArr(val.split(",")));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		System.out.println("-------------------   ----------------------");
		System.out.println("---------------   -GGG-   ------------------");
		System.out.println("-------------------   ----------------------");
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		
		boolean flag =true;
		while (flag) {
			flag = initUI();
			
		}

	}

	@SuppressWarnings({"resource"})
	private static boolean initUI() {

		System.out.println("------------- 1. CLOCK INFO ----------------");
		System.out.println("------------- 2. LOGIN  CLOCK  -------------");
		System.out.println("------------- 3. CLOCK HISTORY  ------------");
		System.out.println("------------- 4. TIMING CLOCK  -------------");
		System.out.println("------------- 5. LOGOUT CLOCK  -------------");
		System.out.println("------------- 6. CLS WINDOWS   -------------");
		System.out.println("------------- 7. TIMING LOGOUT CLOCK   -----");
		System.out.println("------------- 8. QUIT          -------------");
		System.out.print("PLEASE INPUT NUMBER ：");

		String name = StringUtils.EMTRY;
		String password = StringUtils.EMTRY;

		Scanner can = new Scanner(System.in);
		switch (can.nextLine()) {
		case "1":
			OAStatistics.toDayWorkStatistics(StringUtils.covertToStringArr(ClockConstant.NAMES));
			break;
		case "2":
			System.out.print("CLOCK NAME ：");
			name = can.nextLine();
			password = ClockConstant.USERS.get(name);
			password = StringUtils.isEmtry(password) ? password : "111111";
			OAStatistics.login(name, password);
			break;
		case "3":
			System.out.print("CLOCK NAME ：");
			name = can.nextLine();
			password = ClockConstant.USERS.get(name);
			password = StringUtils.isEmtry(password) ? password : "111111";
			OAStatistics.login(name, password);
			System.out.print("CLOCK CHINAESS NAME :");
			String chinaessName = can.nextLine();
			System.out.print("Year AND Month(YYYYMM) :");
			String temp = can.nextLine();
			OAStatistics.historyWorkStatistics(chinaessName,temp);
			break;
		case "4":
			timingClock(new ClockTask(),0);
			return false;
		case "5":
			System.out.print("LOGOUT CLOCK NAME ：");
			name = can.nextLine();
			password = ClockConstant.USERS.get(name);
			password = StringUtils.isEmtry(password) ? password : "111111";
			OAStatistics.logout(name,password);
			break;
		case "6":
			clearCommand();
			break;
		case "7":
			System.out.print("LOGOUT CLOCK NAME (USE , PARTITION)：");
			name = can.nextLine();
			password = ClockConstant.USERS.get(name);
			password = StringUtils.isEmtry(password) ? password : "111111";
			System.out.print("CLOCK TIMING (FORMAT： yyyyMMddhhmm) ：");
			String date = can.nextLine();
			timingClock(new ClockLogOutTask(date, name,password),1000*10);
			return false;
		case "8":
			System.exit(0);
			break;
		}
		return true;

	}

	private static void clearCommand() {

		try {
			// Runtime.getRuntime().exec(command);
			new ProcessBuilder("cmd", "/C", "cls").inheritIO().start();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("------------- COMMAND ERROR  ----------------");
		}
	}

	private static void timingClock(AbstractTimingTask task,int period){
		
		Calendar calendar = Calendar.getInstance(); int year =
		calendar.get(Calendar.YEAR); int month =
		calendar.get(Calendar.MONTH); int day =
		calendar.get(Calendar.DAY_OF_MONTH);//ÿ： 
		calendar.set(year, month,day, 8, 39, 00);
		Date date = calendar.getTime();
		Timer timer = new Timer(); 
		period = period == 0 ?  60 * 1000 * 2 : period; //ÿ：�dateʱ：ִ：task：ÿ：2：�ظ�ִ：
		timer.schedule(task, date, period); //ÿ：�dateʱ：ִ：task,
		//：ִ：һ： 
		//timer.schedule(task, date); //Timer timer = new Timer();
		//timer.schedule(new ClockTask(), 60 * 1000);
		
	}

}
