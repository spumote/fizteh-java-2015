package ru.fizteh.fivt.students.spumote.threads;

/**
 * Created by spumote on 17.12.15.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class ThreadsBlockingQueue<T> {
    private int maxQueueSize;
    private Queue<T> queue;
    ThreadsBlockingQueue(int maxSize) {
        maxQueueSize = maxSize;
        queue = new LinkedList<>();
    }
    public synchronized void offer(List<T> e) throws InterruptedException {
        while (queue.size() + e.size() > maxQueueSize) {
            wait();
            Thread.sleep(100);
        }
        for (int i = 0; i < e.size(); ++i) {
            queue.add(e.get(i));
        }
        notifyAll();
    }
    public synchronized List<T> take(int n) throws InterruptedException {
        while (queue.size() < n) {
            wait();
            Thread.sleep(100);
        }
        List<T> ans = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            ans.add(queue.remove());
        }
        notifyAll();
        return ans;
    }
}
