package com.edso.bai3.service;

import com.edso.bai3.Const;
import com.edso.bai3.model.Consumer;
import com.edso.bai3.model.Message;
import com.edso.bai3.model.Producer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class MessageService {
    private BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(Const.SIZE_OF_QUEUE);

    public void message() throws InterruptedException {

        ReentrantLock queueLock = new ReentrantLock();

        // tạo một pool chứa số lượng thread của Producer và Consumer
        ExecutorService executor = Executors.newFixedThreadPool(Const.SIZE_OF_COMSUMER + Const.SIZE_OF_PRODUCER);

        for (int i = 0; i < Const.SIZE_OF_PRODUCER; i++) {
            Producer producer = new Producer(queue, queueLock);
            executor.execute(producer);
        }

        for (int i = 0; i < Const.SIZE_OF_COMSUMER; i++) {
            Consumer consumer = new Consumer(queue, queueLock);
            executor.execute(consumer);
        }
    }

}
