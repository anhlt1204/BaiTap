package com.edso.bai3.model;

import com.edso.bai3.Const;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Producer extends Thread {

    private BlockingQueue<Message> queue;
    private ReentrantLock queueLock;

    public Producer(BlockingQueue<Message> queue, ReentrantLock queueLock) {
        this.queue = queue;
        this.queueLock = queueLock;
    }

    @Override
    public void run() {
        while (true){
            try {
                queueLock.lock();
                try {
                    // Kiểm tra queue đầy, nếu đầy Producer sẽ chờ khi có queue có chỗ trống
                    if (queue.size() >= Const.SIZE_OF_QUEUE) {
                        log.info("Queue is full. Wait ...");
                        continue;
                    } else {
                        Message m = new Message( "'s message ");
                        log.info("-----> Send... " + m);
                        queue.add(m);
                    }
                } finally {
                    queueLock.unlock();
                }

                Thread.sleep(300);

            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }
    }
}

