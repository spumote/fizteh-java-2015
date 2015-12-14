package ru.fizteh.fivt.students.spumote.threads;

/**
 * Created by spumote on 15.12.15.
 */

import java.lang.*;

class ThClass extends Thread {
    private volatile int number;

    @Override
    public void run() {
        while (true) {
            if (threads.curNum + 1 == number) {
                System.out.println("Thread-" + number);
                threads.curNum++;
            }
            else {
                try{
                    Thread.sleep(100);
                } catch(InterruptedException e){}
            }
        }
    }

    ThClass() {}
    ThClass(int _num) {
        this.number = _num;
    }
}

public class threads {
    public static int curNum = 0;

    public static void main(String[] args) {
        int n = 0;
        if (args.length > 0)
            n = Integer.parseInt(args[0]);
        curNum = n;
        ThClass[] threads = new ThClass[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new ThClass(i + 1);
            threads[i].start();
        }
        while (true) {
            if (curNum == n)
                curNum = 0;
            else {
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){}
            }
        }
    }
}