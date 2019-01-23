package cn.pomxl.springbootrabbitmq.Listener;

import cn.pomxl.springbootrabbitmq.entry.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BookService {

    @RabbitListener(queues = "direct_queue")
    public void receive(Book book){
        System.out.println(book.toString());
    }

    @RabbitListener(queues = "fanout_queue")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
