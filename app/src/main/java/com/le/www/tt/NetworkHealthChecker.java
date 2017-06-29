package com.le.www.tt;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangweijun1 on 2017/6/29.
 */

public class NetworkHealthChecker extends BaseHealthChecker
{
    public NetworkHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
    }

    @Override
    public void verifyService()
    {
        Log.d("DaoExample","tid:" + Thread.currentThread().getId() +" Checking " + this.getServiceName());
        try
        {
            Thread.sleep(4000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + this.getServiceName() + " is UP");
    }
}