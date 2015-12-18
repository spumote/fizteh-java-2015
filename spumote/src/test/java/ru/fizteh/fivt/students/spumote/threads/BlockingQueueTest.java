package ru.fizteh.fivt.students.spumote.threads;

/**
 * Created by spumote on 17.12.15.
 */

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueTest {
    private static ThreadsBlockingQueue<Integer> queue = new ThreadsBlockingQueue<Integer>(20);

    static class OfferTest implements Runnable {
        private List<Integer> list = new LinkedList<Integer>();
        OfferTest(int n) {
            for (int i = 0; i < n; i++) {
                list.add(i + 1);
            }
        }
        @Override
        public void run() {
            try {
                queue.offer(list);
                System.out.println("Offer");
            } catch (InterruptedException e) { }
        }
    }

    static class TakeTest implements Runnable {
        private int listSize;
        private List<Integer> list;
        TakeTest(int n) {
            listSize = n;
        }

        @Override
        public void run() {
            try {
                list = queue.take(listSize);
                System.out.print("Take: ");
                for (int i = 0; i < list.size(); i++)
                    System.out.print(list.get(i) + " ");
                System.out.println();
            } catch (InterruptedException e) { }
        }
    }

    public static void main(String[] args) {
        new Thread(new TakeTest(3)).start();
        new Thread(new OfferTest(4)).start();
       // new Thread(new TakeTest(9)).start();
        //new Thread(new OfferTest(8)).start();
    }
}
