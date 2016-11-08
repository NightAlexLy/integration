package org.luisyang.integration.clock.statistics;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.luisyang.integration.clock.domain.ClockEntity;
import org.luisyang.integration.clock.domain.Row;
import org.luisyang.integration.clock.domain.util.RowUtils;

import com.google.gson.Gson;

public class OAStatistics {

	private static CloseableHttpClient httpClient = null;
	private static Gson gson = new Gson();
	private static List<Row> rows = new ArrayList<Row>();

	static {
		if (httpClient == null) {
			httpClient = HttpClients.createSystem();
		}
	}
	
	public static void login(String userName,String password) {

		HttpPost post = new HttpPost("http://168.7.55.219:8800/yyptoa/ctrl/common/login");

		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

		nvps.add(new BasicNameValuePair("userName", userName));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("IbtnEnter.x", "55"));
		nvps.add(new BasicNameValuePair("IbtnEnter.y", "39"));

		post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		try {
			httpClient.execute(post);
			System.out.println("LOGIN SUCCESS BY NAME ："+userName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LOGIN FAILED BY NAME ："+userName);
		}

	}
	public static ClockEntity toDayWork(String page) {

		String url = "http://168.7.55.219:8800/yyptoa/ctrl/grid/read?serverId=attentionToday&_search=false&rows=20&page="
				+ page + "&sidx=&sord=asc";
		HttpGet get = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(get);

			String json = EntityUtils.toString(response.getEntity());

			return gson.fromJson(json, ClockEntity.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public static ClockEntity historyWork(String name,String page) {

		String url = "http://168.7.55.219:8800/yyptoa/ctrl/grid/read?_=1457601188272&params=&limit=&serverId=oa_signin_all&"
		+ "%24loadonce=false&_search=true&nd=1457601188270&rows=10&page="+page+"&sidx=&"
		+ "sord=asc&filters=%7B%22groupOp%22%3A%22AND%22%2C%22rules%22%3A%5B%7B%22field%22%3A%22user_name"
		+ "%22%2C%22op%22%3A%22eq%22%2C%22data%22%3A%22"+URLEncoder.encode(name)+"%22%7D%5D%7D";
		HttpGet get = new HttpGet(url);
		try {
			CloseableHttpResponse response = httpClient.execute(get);

			String json = EntityUtils.toString(response.getEntity());

			return gson.fromJson(json, ClockEntity.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 */
	public static void historyWorkStatistics(final String chinaessName,String temp) {

		rows.clear();
		final ClockEntity entity = historyWork(chinaessName,"1");
		rows.addAll(entity.getRows());
		
		int threadCount = 3;
		int total = entity.getTotal();
		Thread thread = null;
		final int startIndex = (total - 1) / threadCount;
		for (int i = 1; i <= threadCount; i++) {
			final int index = i == 1 ? 2 : startIndex * (i - 1) + 1;
			final int end = i == threadCount ? total : startIndex * i;
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
                    for (int j = index; j <= end; j++) {
						rows.addAll(historyWork(chinaessName,String.valueOf(j)).getRows());
					}
				}
			});
			thread.start();
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Row> nameRows = new ArrayList<Row>();
		for(int i =0;i<rows.size();i++){
			if (rows.get(i).getId().contains(chinaessName)) {
				String[] cells = rows.get(i).getCell();
				if(cells.length > 0 && StringUtils.isNotBlank(cells[2])){
					String date = cells[1];
					if(StringUtils.isNotEmpty(date) && StringUtils.isNotEmpty(date.replaceAll("\\s+",""))){
						String cellMonth = date.substring(0,6);
						if(cellMonth.equals(temp)){
							nameRows.add(rows.get(i));
						}	
					}
				}
			}
		}
		RowUtils.printAndSortRows(nameRows);
	}

	public static void logout(String name, String password) {
		
		login(name, password);
		
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		HttpPost post = new HttpPost("http://168.7.55.219:8800/yyptoa/ctrl/sign/sign");

		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		
		nvps.add(new BasicNameValuePair("signaction", "1"));
		
		post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		
		try {
			
			CloseableHttpResponse response = httpClient.execute(post);

			System.out.println(EntityUtils.toString(response.getEntity()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void toDayWorkStatistics(String[] names){
		
		rows.clear();
		ClockEntity entity = toDayWork("1");
		rows.addAll(entity.getRows());
		int threadCount = 3;
		// entity.getTotal();
		int total = entity.getTotal();
		Thread thread = null;
		final int startIndex = (total - 1) / threadCount;
		for (int i = 1; i <= threadCount; i++) {
			final int index = i == 1 ? 2 : startIndex * (i - 1) + 1;
			final int end = i == threadCount ? total : startIndex * i;
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = index; j <= end; j++) {
						rows.addAll(toDayWork(String.valueOf(j)).getRows());
					}
				}
			});

			thread.start();
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		test : for(String name : names){
			for (Row row : rows) {
				if (row.getId().contains(name)) {
					System.out.println(Arrays.toString(row.getCell()));
					continue test;
				}	
			}
		}
	}

}
