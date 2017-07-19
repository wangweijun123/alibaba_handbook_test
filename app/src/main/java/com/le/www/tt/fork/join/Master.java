package com.le.www.tt.fork.join;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangweijun on 2017/7/18.
 */

public class Master {
    public static final String TAG = "Master";

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>());

    public Master() {

    }

    public void execute(Task task) {
        executor.execute(task);
    }

    public interface CallBack {
        void itemfinished();
    }



    public static final class Task implements Runnable {
        int id;
        long millis;

        CallBack callBack;

        public Task(int id, long millis, CallBack callBack) {
            this.id = id;
            this.millis = millis;
            this.callBack = callBack;
        }

        @Override
        public void run() {
            Log.i(TAG, Thread.currentThread().getId() + " 开始执行任务task id :"+id);
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, Thread.currentThread().getId() + " 完成任务task id :"+id + " spend time:"+millis);
            callBack.itemfinished();
        }
    }
}
