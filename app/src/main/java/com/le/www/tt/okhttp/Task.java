package com.le.www.tt.okhttp;

import android.util.Log;

import java.util.Random;

/**
 * Created by wangweijun1 on 2017/9/25.
 */

public class Task implements Runnable{
    int id;
    int millis;

    private Dispatcher dispatcher;
    public Task(int id, Dispatcher dispatcher) {
        this.id = id;
        this.millis = new Random().nextInt(5);
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        Log.i(Dispatcher.TAG, Thread.currentThread().getId() + " 开始执行任务task id :"+id);
        try {
            Thread.sleep(millis * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(Dispatcher.TAG, Thread.currentThread().getId() + " 完成任务task id :"+id + " spend time:"+millis + "秒");

        dispatcher.finished(this);
    }
}
