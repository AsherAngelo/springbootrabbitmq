package cn.pomxl.springbootrabbitmq;

import cn.pomxl.springbootrabbitmq.entry.Book;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootrabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Test
    public void createExchange(){
        rabbitAdmin.declareExchange(new DirectExchange("rabbitadmin.direct"));
    }

    @Test
    public void createQueue(){
        rabbitAdmin.declareQueue(new Queue("rabbitadmin.queue"));
    }

    @Test
    public void createBinds(){
        rabbitAdmin.declareBinding(new Binding("rabbitadmin.queue",Binding.DestinationType.QUEUE,"rabbitadmin.direct","rabbitadmin.queue",null));
    }

    @Test
    public void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhaomengjie");
        rabbitTemplate.convertAndSend("direct_exchange","direct_exchange",map);
    }

    @Test
    public void fanoutExchange() {
        rabbitTemplate.convertAndSend("exchange_fanout","fanout_queue",new Book("name","zhaomengjie"));
    }

    @Test
    public void recive() {
        Object o = rabbitTemplate.receiveAndConvert("direct_queue");
        System.out.println(o);
    }

}
