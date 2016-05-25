package org.luisyang.integration.mq.metaq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.integration.mq.metaq.api.MqEventBean;
import org.luisyang.integration.mq.metaq.api.MqProducer;
import org.luisyang.integration.mq.metaq.api.exception.MetaqProducerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author ly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-appName-metaQ-producer.xml")
public class MetaqProducerTest02 {

	@Autowired
	MqProducer metaqProducer;

	@Test
	public void send() throws IOException {
		try {
			System.out.println("please input you message:");

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "my products test";

			int i = 0;
			while ((line = reader.readLine()) != null) {

				//System.out.println(line);
//				metaqProducer.publish("mytest03", line + ">>>>>>" + i++);

				MqEventBean eventBean= new MqEventBean();
				
				TuserEntity entity1= new TuserEntity();
				entity1.setAge(19+i);
				entity1.setDes("des entity1 " + i + ":" + line);
				entity1.setId(1L+i);
				entity1.setName("name1 "+i);
				entity1.setUserId(1001L);
				
				eventBean.setBodyValue("entity1", JSON.toJSONString(entity1));
				
				TuserEntity entity2= new TuserEntity();
				entity2.setAge(29+i);
				entity1.setDes("des entity2 " + i + ":" + line);
				entity2.setId(2L);
				entity2.setName("name2 "+i);
				entity2.setUserId(2001L+i);
				
				eventBean.setBodyValue("entity2", JSON.toJSONString(entity2));

				metaqProducer.publish("my_test_00002", eventBean);

				
				if (line.equals("exit")) {
					break;
				}
			}
		} catch (MetaqProducerException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getCode() + e.getMsg());
		}

	}

	@Ignore
	@Test
	public void send01() throws IOException {
		/*try {
			metaqProducer.publish("mytest03", "welcoe cotents00001335S dxxx");

			// System.out.println("sssbb");
		} catch (MetaqProducerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = "my products test";

		while ((line = reader.readLine()) != null) {

			System.out.println(line);
			break;
		}*/
	}
}
