package com.le.www.tt.interceptor;

/**
 * Created by wangweijun1 on 2017/7/7.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response process(Chain chain) {
        Request request = chain.request;
        request.content = request.content + ", add Cache";

        Response response = chain.response;
        response.content = response.content + ", add Cache";
        return chain.process();
    }
}
