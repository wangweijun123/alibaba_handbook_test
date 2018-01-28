package com.le.www.tt;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池在后台等待，等待条件来执行相对应操作
 * Created by wangweijun1 on 2017/7/7.
 */

public class ConnectionPool {

    private final static int MAX_CONNECTIONS = 5;

    private Deque<Connection> connections = new ArrayDeque<>();

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(), new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("clean connection thread and id:" + thread.getId());
            return thread;
        }
    });

    /**
     * synchronized (obj) {
     *         while (&lt;condition does not hold&gt;) {
     *             obj.wait(timeout, nanos);
     *         ... // Perform action appropriate to condition
     *         }
     *     }
     */
    private Runnable cleanRunnable = new Runnable() {
        @Override
        public void run() {
            synchronized (connections) {
                while (cleanRunning) {
                    if (connections.size() <= MAX_CONNECTIONS) {
                        try {
                            Log.i(MainActivity.TAG, "clean thread 等待。。。");
                            connections.wait();
                            Log.i(MainActivity.TAG, "clean thread 被其他线程唤醒 ");
                        } catch (InterruptedException e) {
                            cleanRunning = false;
                            e.printStackTrace();
                            Log.i(MainActivity.TAG, "线程被打断了 " + e.getMessage());
                        }
                    }
                    // clean first connection 根据条件
                    Log.i(MainActivity.TAG, "开始执行判断connections.size()"+connections.size());
                    if (connections.size() > MAX_CONNECTIONS) {
                        Connection con = connections.removeFirst();
                        Log.i(MainActivity.TAG, "remove connection id:"+con.id);
                    }

                }
                Log.i(MainActivity.TAG, "清理线程退出了 ");
            }
        }
    };

    private volatile boolean cleanRunning = false;// 是否停止线程池

    public void start() {
        if (!cleanRunning) {
            cleanRunning = true;
            executor.execute(cleanRunnable);
        }
    }


    public void quit() {
        if (cleanRunning) {
            cleanRunning = false;
            Log.i(MainActivity.TAG, "executor shutdown");
            executor.shutdownNow();
        }
    }

    public void put(Connection connection) {
        synchronized (connections) {
            connections.add(connection);
            Log.i(MainActivity.TAG, Thread.currentThread().getId()+"  add connection "+connection.id+" 总大小 :" + connections.size());
            // 在哪个对象调用connections.wait(), 就在哪个对象调用notifyAll
            connections.notifyAll();
        }
    }

    public static void  executeTwice() {
        for (int i=0;i<2;i++) {
            executor.execute(myRunnable);
        }

    }

    static Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            Log.i(MainActivity.TAG, "executor xxxx");
        }
    };

}
