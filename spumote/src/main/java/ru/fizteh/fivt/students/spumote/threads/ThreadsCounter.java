package ru.fizteh.fivt.students.spumote.threads;

/**
 * Created by spumote on 14.12.15.
 */

public class ThreadsCounter {

    public static class Runner {
        private int curNum = 0;

        class ThClass extends Thread {
            private volatile int number;

            @Override
            public void run() {
                while (true) {
                    if (curNum + 1 == number) {
                        System.out.println("Thread-" + number);
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
            curNum = n;
            ThClass[] threads = new ThClass[n];
            for (int i = 0; i < n; i++) {
                threads[i] = new ThClass(i + 1);
                threads[i].start();
            }
            while (true) {
                if (curNum == n) {
                    curNum = 0;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) { }
                }
            }
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
