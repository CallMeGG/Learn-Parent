package com.gyp.example;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Title: TestSemaphore
 * Description: TODO
 * date 2021/6/1 14:33
 *
 * @author GYP
 * @version 1.0
 */
public class TestSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(i, semaphore).start();
        }
    }

    static class Car extends Thread {
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();
                System.out.println("第" + num + "辆车占一个车位");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("第" + num + "辆车开走");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
