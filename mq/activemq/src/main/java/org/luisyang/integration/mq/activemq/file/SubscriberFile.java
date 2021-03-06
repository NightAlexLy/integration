package org.luisyang.integration.mq.activemq.file;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.util.concurrent.TimeUnit;


/**
 * @author ly
 *
 */
public class SubscriberFile
{
    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory("admin","admin", connectionUri);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("EVENTS.QUOTES1");
    }

    public void after() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void run() throws Exception {
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new EventListenerFile());
        TimeUnit.MINUTES.sleep(5);
        connection.stop();
        consumer.close();
    }

    public static void main(String[] args) {
        SubscriberFile producer = new SubscriberFile();
        System.out.print("\n\n\n");
        System.out.println("Starting example Stock Ticker FTSubscriber now...");
        try {
            producer.before();
            producer.run();
            producer.after();
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e.getMessage());
        }
        System.out.println("Finished running the sample Stock Ticker FTSubscriber app.");
        System.out.print("\n\n\n");
    }
}
