package lab5;
import com.github.javafaker.Faker;

import java.util.List;

public class LinearConveyor {

    static Faker faker = new Faker();
    public static void constuctor(List<Phone> phoneList) {
        for (Phone phone: phoneList) {
            phone.setModel(faker.app().name());

            phone.setColor(faker.color().name());

            phone.setMemory(faker.number().numberBetween(1000, 100000));

            phone.setCost(faker.number().numberBetween(100, 2000));

            phone.setWifiModule(true);
        }
    }
}
