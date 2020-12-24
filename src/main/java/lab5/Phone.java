package lab5;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@ToString
public class Phone {
    private String model;
    private String color;
    private Integer memory;
    private Boolean wifiModule;
    private Integer cost;
    private Lock lock = new ReentrantLock();

}
