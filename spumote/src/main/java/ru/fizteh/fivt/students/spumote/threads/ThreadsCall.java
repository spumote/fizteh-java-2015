package ru.fizteh.fivt.students.spumote.threads;

/**
 * Created by spumote on 14.12.15.
 */
import java.util.Random;

public class ThreadsCall {

    public static class Runner {
        private int curNum = 0;
        private boolean allReady = false;
        private boolean alive = true;
        private Random random;

        class ThClass extends Thread {
            private volatile int number;

            @Override
            public void run() {
                while (alive) {
                    if (curNum + 1 == number) {
                        int x = random.nextInt(9) + 1;
                        if (x == 1) {
                            allReady = false;
                            System.out.println("No");
                        } else {
                            System.out.println("Yes");
                        }
                        curNum++;
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) { }
                    }
                }
            }

            ThClass() {
            }

            ThClass(int num) {
                this.number = num;
            }
        }

        void run(int n) {
            random = new Random();
            curNum = n;
            ThClass[] threads = new ThClass[n];
            for (int i = 0; i < n; i++) {
                threads[i] = new ThClass(i + 1);
                threads[i].start();
            }
            while (true) {
                if (curNum == n) {
                    if (allReady) {
                        break;
                    }
                    allReady = true;
                    System.out.println("Are you ready?");
                    curNum = 0;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) { }
                }
            }
            alive = false;
        }
    }

    public static void main(String[] args) {
        int n = 0;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        new Runner().run(n);
    }
}
