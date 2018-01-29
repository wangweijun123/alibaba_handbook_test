package com.play.uyoung.dynamicproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class ProxyPerson {

    Object target;

    public ProxyPerson() {
    }

    public ProxyPerson(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("proxy do ...");
                        Object result = method.invoke(target, args);
                        System.out.println("proxy do again");
                        return result;
                    }
                });
    }

    public <T> T getProxy(final T t) {
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(),
                t.getClass().getInterfaces(),// 注意这里获取的是接口，所以一定是要接口
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(t, args);
                    }
                });
    }
}
