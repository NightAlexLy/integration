package org.luisyang.integration.mq.kafka.callback;

import org.apache.kafka.clients.consumer.ConsumerRecord;


/**
 * @author ly
 *
 * @param <T>
 * @param <G>
 */
public interface MessageCallback<T,G> {
    public void handleMessage(ConsumerRecord<T, G> record);
}
