package lab5;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {


        List<Phone> phones = new ArrayList<Phone>();
        for (int i = 0; i < 100000; i++) {
            phones.add(new Phone());
        }
        Long t1 = System.nanoTime();
        LinearConveyor.constuctor(phones);
        Long t2 = System.nanoTime();
        System.out.println(t2 - t1);

        List<Phone> phones2 = new ArrayList<Phone>();
        for (int i = 0; i < 10; i++) {
            phones2.add(new Phone());
        }

        t1 = System.nanoTime();
        ParallelConveyor parallelConveyor = new ParallelConveyor();
        parallelConveyor.construct(phones2);
         t2 = System.nanoTime();
        System.out.println(t2 - t1);

        for (Phone phone:
            phones2 ) {
            System.out.println(phone);
        }

    }


}
