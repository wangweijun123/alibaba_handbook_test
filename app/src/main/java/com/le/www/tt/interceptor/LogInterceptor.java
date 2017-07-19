package com.le.www.tt.interceptor;

/**
 * Created by wangweijun1 on 2017/7/7.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response process(Chain chain) {
        Request request = chain.request;
        request.content =  request.content + ", add some log";

        Response response = new Response();
        response.content = response.content + ", add some log";

        chain.response = response;

//        return chain.process();
        return response;
    }
}
