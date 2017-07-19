package com.le.www.tt.interceptor;

import android.util.Log;

import com.le.www.tt.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器链条
 * Created by wangweijun1 on 2017/7/7.
 */

public class Chain {
    public final Request request;
    public Response response;
    public final List<Interceptor> interceptors;

    public Chain(Request request) {
        this.request = request;
        this.interceptors = new ArrayList<>();

        this.interceptors.add(new LogInterceptor());
        this.interceptors.add(new CacheInterceptor());
    }




    private int index = 0;
    public Response process() {
        Log.i(MainActivity.TAG, "index:"+index);
        if (index < interceptors.size()) {
            Interceptor nextInterceptor = interceptors.get(index);
            // 注意这个家伙哦，递归的时候
            index ++;

            nextInterceptor.process(this);
        }
        //
        Log.i(MainActivity.TAG, "全部拦截器已调用完");
        return response;
    }
}
