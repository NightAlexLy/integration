package org.luisyang.integration.mq.activemq.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.BlobMessage;


/**
 * @author ly
 *
 */
public class EventListenerFile implements MessageListener {
    public void onMessage(Message message) {
        try {
            if(message instanceof BlobMessage)
            {
                BlobMessage blobMessage = (BlobMessage) message;
                InputStream input = blobMessage.getInputStream();

                FileOutputStream fos = new FileOutputStream("data/mysql-workbench.rpm_new");

                byte[] b = new byte[1024*1024*200];

                while((input.read(b)) != -1){

                    fos.write(b);

                }

                input.close();

                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
