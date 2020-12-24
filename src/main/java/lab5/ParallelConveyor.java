package lab5;

import com.github.javafaker.Faker;
import java.lang.Thread;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class ParallelConveyor {

    public void construct(List<Phone> phones) throws InterruptedException {
        BlockingDeque<Phone> startModel = new LinkedBlockingDeque<>(phones);
        BlockingDeque<Phone> modelMemory = new LinkedBlockingDeque();
        BlockingDeque<Phone> memoryWifiModule = new LinkedBlockingDeque();
        BlockingDeque<Phone> wifiColor = new LinkedBlockingDeque();
        BlockingDeque<Phone> colorCost = new LinkedBlockingDeque();

        Thread model = new Thread(() -> {
            Faker faker = new Faker();

            Phone phone = null;
            try {
                phone = startModel.poll(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (phone != null) {
                phone.getLock().lock();
                phone.setModel(faker.app().name());
                phone.getLock().unlock();
                try {
                    modelMemory.offer(phone, 10, TimeUnit.MILLISECONDS);
                    phone = startModel.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread memory = new Thread(() -> {
            Faker faker = new Faker();

            Phone phone = null;
            try {
                phone = modelMemory.poll(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (phone != null) {
                phone.getLock().lock();
                phone.setMemory(faker.number().numberBetween(100, 2000));
                phone.getLock().unlock();
                try {
                    memoryWifiModule.offer(phone, 10, TimeUnit.MILLISECONDS);
                    phone = modelMemory.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread wifi = new Thread(() -> {
            Faker faker = new Faker();

            Phone phone = null;
            try {
                phone = memoryWifiModule.poll(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (phone != null) {
                phone.getLock().lock();
                phone.setWifiModule(true);
                phone.getLock().unlock();
                try {
                    wifiColor.offer(phone, 10, TimeUnit.MILLISECONDS);
                    phone = memoryWifiModule.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        Thread color = new Thread(() -> {
            Faker faker = new Faker();

            Phone phone = null;
            try {
                phone = wifiColor.poll(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (phone != null) {
                phone.getLock().lock();
                phone.setColor(faker.color().name());
                phone.getLock().unlock();
                try {
                    colorCost.offer(phone, 10, TimeUnit.MILLISECONDS);
                    phone = wifiColor.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread cost = new Thread(() -> {
            Faker faker = new Faker();

            Phone phone = null;
            try {
                phone = colorCost.poll(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (phone != null) {
                phone.getLock().lock();
                phone.setCost(faker.number().numberBetween(1, 2000));
                phone.getLock().unlock();
                try {
                    phone = colorCost.poll(10, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });


        Thread[] threads = new Thread[]{model, memory, cost, color, wifi};
        for (int i  =0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i  = 0; i < threads.length; i++) {
            threads[i].join();
        }

    }
}

