package com.gyp.example.sort;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class SortApplication {

    private static int SIZE = 10000000;

    public static void main(String[] args) {

        long l = System.currentTimeMillis();

        int array[] = new int[SIZE];
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(SIZE);
        }
        System.out.println("造数据耗时：" + (System.currentTimeMillis() - l));

        long l1 = System.currentTimeMillis();
        new XiEr().sort(array);
        long xier = System.currentTimeMillis();
        System.out.println("希尔排序耗时：" + (xier - l1));

        new QS().sort(array);
        long qs = System.currentTimeMillis();
        System.out.println("快速排序耗时：" + (qs - xier));

        //SpringApplication.run(SortApplication.class, args);
    }

}
