package com.company;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        int[] arrayOfNum = new int[1000000];

        for(int i = 0; i < arrayOfNum.length; i++){
            arrayOfNum[i] = (int)(Math.random() * 100);
        }

        long startFJ = System.currentTimeMillis();

        ForkJoinPool poolFJ = new ForkJoinPool(10);
        long resFJ = poolFJ.invoke(new Service(arrayOfNum));//invoke - развлетвляет задачу и ожидает результата, не требуя ручного соединения

        long endFJ = System.currentTimeMillis();
        long resTimeFJ = endFJ - startFJ;

        System.out.println("Result: " + resFJ + "\nTime: " + resTimeFJ + " ms");
    }
}
