package com.le.www.tt.interceptor;

/**
 * Created by wangweijun1 on 2017/7/7.
 */

public interface Interceptor {
    Response process(Chain chain);
}
