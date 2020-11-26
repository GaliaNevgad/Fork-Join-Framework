package com.company;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class Service extends RecursiveTask<Long> {
    int[] arrayOfNum;

    public Service(int[] arrayOfNum) {
        this.arrayOfNum = arrayOfNum;
    }

    @Override
    protected Long compute() {
        if (arrayOfNum.length < 20) {
            long sum = 0;
            for (int elements : arrayOfNum) {
                sum += elements;
            }
            return sum;
        } else {
            Service firstly = new Service(Arrays.copyOfRange(arrayOfNum, 0, arrayOfNum.length/2));
            firstly.fork(); //запускает задачу в пул, но не запускает её выполнение
            Service secondly = new Service(Arrays.copyOfRange(arrayOfNum, arrayOfNum.length/2, arrayOfNum.length));
            long res = secondly.compute();
            return firstly.join() + res;//join возвращает результат выполнения задачи
        }
    }
}
