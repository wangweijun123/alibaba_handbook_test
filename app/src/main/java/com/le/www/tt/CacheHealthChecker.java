package com.le.www.tt;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangweijun1 on 2017/6/29.
 */

public class CacheHealthChecker extends BaseHealthChecker
{
    public CacheHealthChecker(CountDownLatch latch)  {
        super("CacheHealthChecker Service", latch);
    }

    @Override
    public void verifyService()
    {
        Log.d("DaoExample","tid:" + Thread.currentThread().getId() +" Checking " + this.getServiceName());
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Log.d("DaoExample","tid:" + Thread.currentThread().getId() + this.getServiceName() + " is UP");
    }
}