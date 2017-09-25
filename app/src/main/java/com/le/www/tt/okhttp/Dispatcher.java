package com.le.www.tt.okhttp;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangweijun1 on 2017/9/25.
 *
 * okhttp 最大请求数量64个请求，如果都很长，那肯定会创建64个线程，每个线程
 * 最大空闲时间是一分钟，超过一分钟，会destory，
 */

public class Dispatcher {
    public static final String TAG = "Dispatcher";
    /** 缓存任务队列(注意它只是一个引用)*/
    private final Deque<Task> readyAsyncTask = new ArrayDeque<>();

    /** 正在运行的任务队列 */
    private final Deque<Task> runningAsyncTask = new ArrayDeque<>();

    /** 线程池执行任务队列 */
    private ExecutorService service;

    /** 最大请求数量 */
    private int maxRequests = 5;

    private ExecutorService getService() {
        if (service == null) {
            synchronized (this) {
                if (service == null) {// 注意这个workQueue，为何LinkedBloackQueue会导致只会产生一个线程
                    service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                            60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                }
            }
        }
        return service;
    }

    public synchronized void enqueue(Task newTask) {
        Log.i(TAG, "正在运行的任务 size :"+runningAsyncTask.size());
        if (runningAsyncTask.size() < maxRequests) {
            Log.i(TAG, "任务id ：" + newTask.id + " 被添加到运行队列");
            runningAsyncTask.add(newTask);
            getService().execute(newTask);
        } else {
            Log.i(TAG, "任务id ：" + newTask.id + " 被添加到缓存队列");
            readyAsyncTask.add(newTask);
        }
    }

    public void finished(Task finishedtask) {
        synchronized (this) {
            boolean succ = runningAsyncTask.remove(finishedtask);
            Log.i(TAG, "已完成任务id:"+finishedtask.id+", 从运行任务列表删除 succ:"+succ);
            if (succ) {
                if (runningAsyncTask.size() >= maxRequests ) {
                    Log.i(TAG, "运行任务已达到最大数量");
                    return;// 运行任务已达到最大数量
                }
                if (readyAsyncTask.isEmpty()) {
                    Log.i(TAG, "缓存队列没有任务");
                    return; // 缓存队列没有任务
                }

                Iterator<Task> iter = readyAsyncTask.iterator();
                Task task;
                while (iter.hasNext()) {
                    task = iter.next();
                    iter.remove();
                    Log.i(TAG, "从缓存获取任务id："+task.id+" 添加进运行队列执行它");
                    runningAsyncTask.add(task);
                    getService().execute(task);
                    if (runningAsyncTask.size() >= maxRequests) {
                        return;
                    }
                }
            }
        }
    }
}
