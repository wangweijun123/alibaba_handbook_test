package com.le.www.tt.syncronized;

import android.util.Log;

/**
 * Created by wangweijun on 2018/1/27.
 */

public class SyncronizedClassAndThisTest {


    public void testSyncronizedClass() {

        Log.i("DaoExample", "before synchronized tid:"+Thread.currentThread().getName());
        synchronized (SyncronizedClassAndThisTest.class) {
            Log.i("DaoExample", " entern synchronized this sleep start tid:"+Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
                Log.i("DaoExample", " entern synchronized this sleep finished tid:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("DaoExample", "testSyncronizedThis finish tid:"+Thread.currentThread().getName());
    }


    public void testSyncronizedThis() {

        Log.i("DaoExample", "before synchronized tid:"+Thread.currentThread().getName());
        synchronized (SyncronizedClassAndThisTest.this) {
            Log.i("DaoExample", " entern synchronized this sleep start tid:"+Thread.currentThread().getName());
            try {
                Thread.sleep(4000);
                Log.i("DaoExample", " entern synchronized this sleep finished tid:"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("DaoExample", "testSyncronizedThis finish tid:"+Thread.currentThread().getName());
    }



}
