import lombok.SneakyThrows;

import java.time.LocalDate;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Delivery thread1 = new Delivery("Floresti", LocalDate.parse("2017-09-02"));
        thread1.start();
        thread1.join();

        Delivery thread2 = new Delivery("Apahida", LocalDate.parse("2017-09-03"));
        thread2.start();
        thread2.join();

        Delivery thread3 = new Delivery ("Turda", LocalDate.parse("2017-09-01"));
        thread3.start();
        thread3.join();

        Delivery thread4 = new Delivery("Floresti", LocalDate.parse("2017-09-01"));
        thread4.start();
        thread4.join();

        Delivery.total();
    }
}
