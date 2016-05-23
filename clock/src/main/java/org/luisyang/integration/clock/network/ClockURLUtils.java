package org.luisyang.integration.clock.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.luisyang.integration.clock.constant.ClockConstant;

public class ClockURLUtils {

	/**
	 * ��
	 * 
	 * keyģ�壺������"username=kevin&password=12345"
	 * 
	 * @param key
	 * @throws Exception 
	 */
	public static void clock(String key) throws Exception {

		try {
			String sTotalString = "";
			URL url = new URL(ClockConstant.LOGIN_URL);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
			out.write(key); // ��ҳ�洫����ݡ�post�Ĺؼ����ڣ�
			out.flush();
			out.close();
			// һ�����ͳɹ��������·����Ϳ��Եõ��������Ļ�Ӧ��
			String sCurrentLine;
			//sCurrentLine = "";
			//sTotalString = "";
			InputStream l_urlStream = connection.getInputStream();
			// ��˵�е�����װ����
			BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
			while ((sCurrentLine = l_reader.readLine()) != null) {
				sTotalString += sCurrentLine + "\r\n";
			}
			System.out.println(sTotalString.substring(4, 50));
			System.out.println(key + "---->Success!!!!!");
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			System.out.println(key + "---->Fail!!!!!");
			throw e;
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			System.out.println(key + "---->Fail!!!!!");
			throw e;
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(key + "---->Fail!!!!!");
			throw e;
		}
	}
}
