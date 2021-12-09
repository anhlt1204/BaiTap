package com.edso.bai3.model;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Consumer extends Thread {
    private BlockingQueue<Message> queue;
    private ReentrantLock queueLock;

    public Consumer(BlockingQueue<Message> queue, ReentrantLock queueLock) {
        this.queue = queue;
        this.queueLock = queueLock;
    }

    @Override
    public void run() {
        while (true) {
            if (queueLock.tryLock()) {
                try {
                    // Kiểm tra queue rỗng, nếu rỗng Consumer sẽ chờ khi có message
                    if (queue.isEmpty()) {
                        log.info("Queue is empty. Wait ...");
                        continue;
                    }
                    log.info("-----> Receive " + queue.take());
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    queueLock.unlock();
                }
            }
        }
    }
}

