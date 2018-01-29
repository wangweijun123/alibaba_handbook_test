package com.play.uyoung.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangweijun on 2018/1/28.
 *
 * jdk 动态代理，proxy，代理类不需要改动
 *
 * 普通代理，接口改动，代理类必须改动
 *
 * 代理对象拥有真实对象的共同接口,代理对象在操作真实对象前后加点东西
 *
 * 代理对象拥有目标对象的引用
 *
 * java 静态代理 (1 代理对象拥有真实对象的引用 2 真实对象修改接口,代理对象必须修改)
 * java 动态代理 (2 代理对象无需跟着真实对象的接口改变而改变)
 *
 */

public class ProxyHandler implements InvocationHandler{

    Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("invoke");
        result = method.invoke(target, args);
        System.out.println("invoke finished");
        return result;
    }
}
