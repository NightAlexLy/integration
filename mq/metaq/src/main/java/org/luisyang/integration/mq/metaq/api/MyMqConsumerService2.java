package org.luisyang.integration.mq.metaq.api;

public class MyMqConsumerService2 implements MqConsumerService{

    @Override
    public void exec(MqEventBean eventBean) {

        
        System.out.println("exec====>>>>>>");
        if(eventBean != null){
            
            System.out.println(eventBean.getBodyValue("entity1").toString());
        }
        
        
    }

}
