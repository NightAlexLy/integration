package org.luisyang.integration.mq.activemq.lb;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author ly
 *
 */
public class LBPublisher {
    private final String connectionUri = "failover:(tcp://localhost:61616,tcp://localhost:61617)";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    private final Random pricePicker = new Random();
    private final ArrayList<String> symbols = new ArrayList<String>(3);

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory("admin","admin",connectionUri);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("EVENTS.QUOTES");

        symbols.add("AAPL"); symbols.add("GOOG"); symbols.add("MSFT"); symbols.add("ORCL");
    }

    public void after() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void run() throws Exception {
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 1000; ++i) {
            System.out.println("Producer sending price update("+i+")");
            for (String symbol : symbols) {
                Message message = session.createMessage();
                message.setStringProperty("symbol", symbol);
                message.setFloatProperty("price", pricePicker.nextFloat() * 1000);
                System.out.println(symbol + ": $ ("+message.getFloatProperty("price")+")");
                producer.send(message);
            }
            Thread.sleep(500);
        }

        producer.close();
    }

    public static void main(String[] args) {
        LBPublisher producer = new LBPublisher();
        System.out.print("\n\n\n");
        System.out.println("Starting example Stock Ticker FTPublisher now...");
        try {
            producer.before();
            producer.run();
            producer.after();
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e.getMessage());
        }
        System.out.println("Finished running the sample Stock Ticker FTPublisher app.");
        System.out.print("\n\n\n");
    }
}
