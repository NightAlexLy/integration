package org.luisyang.integration.mq.metaq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.luisyang.integration.mq.metaq.api.MqConsumer;
import org.luisyang.integration.mq.metaq.api.exception.MetaqConsumerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author ly
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-appName-metaQ-consumer.xml")
public class MetaqConsumerTest02 {

	@Autowired
	MqConsumer metaqConsumer;


	@Test
	public void receive02() throws IOException {
		System.out.println("starting receive data:");
		try {
			metaqConsumer.receive("my_test_00002", "tt-group");
		} catch (MetaqConsumerException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getCode() + e.getMsg());

			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = "my products test";

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

}
