package org.community.test.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ly
 */
@Component
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.convertAndSend(text);
        System.out.println("send: " + text);
    }
}