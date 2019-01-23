package cn.pomxl.springbootrabbitmq.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class Book implements Serializable {

    private static final long serialVersionUID = 338538004448245588L;
    private String name;
    private String author;

    public Book(String name, String author) {

        this.name = name;
        this.author = author;
    }

    public Book() {
    }
}
