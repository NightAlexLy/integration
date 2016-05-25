package org.luisyang.integration.mq.kafka.callback;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author ly
 *
 * @param <T>
 * @param <G>
 */
public class InternalConsumer<T,G>
{
    private  KafkaConsumer consumer = null;
    private List<String> topics;
    private Thread internalThread = null;
    private MessageCallback callback;
    public InternalConsumer(List<String> topics, Properties props, MessageCallback<T,G> callback)
    {
        this.topics = topics;
        consumer = new KafkaConsumer(props);
        consumer.subscribe(topics);
        this.callback = callback;
        doWork();
    }

    private void doWork()
    {
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    ConsumerRecords<Integer, String> records = consumer.poll(1000);
                    for (ConsumerRecord<Integer, String> record : records) {
                        callback.handleMessage(record);
                    }
                }
            }
        }).start();
    }



    public static void main(String args[]) throws IOException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        InternalConsumer consumer = new InternalConsumer(Collections.singletonList("topic1"), props, new MessageCallback() {
            public void handleMessage(ConsumerRecord record) {
                System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
            }
        });

        System.in.read();
    }

}
