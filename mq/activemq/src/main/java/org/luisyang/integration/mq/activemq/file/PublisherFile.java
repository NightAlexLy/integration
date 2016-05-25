package org.luisyang.integration.mq.activemq.file;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;

import javax.jms.*;
import java.net.URL;

/**
 * @author ly
 *
 */
public class PublisherFile {
    private final String connectionUri = "tcp://localhost:61616?jms.blobTransferPolicy.defaultUploadUrl=http://172.16.130.129/git/fileServer/";
    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private ActiveMQSession session;
    private Destination destination;

    public void before() throws Exception {
        connectionFactory = new ActiveMQConnectionFactory("admin","admin",connectionUri);
        connection = connectionFactory.createConnection();
        session = (ActiveMQSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createTopic("EVENTS.QUOTES1");

    }

    public void after() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void run() throws Exception {
        MessageProducer producer = session.createProducer(destination);
        BlobMessage blobMessage = session.createBlobMessage(new URL("http://172.16.130.129/git/fileServer/ID:wangzhangguiPC-34636-1458614768885-1:1:1:1:1"));
        blobMessage.setStringProperty("FILE_NAME", "a.java");
        producer.send(blobMessage);
    }

    public static void main(String[] args) {
        PublisherFile producer = new PublisherFile();
        System.out.print("\n\n\n");
        try {
            producer.before();
            producer.run();
            producer.after();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("\n\n\n");
    }
}
