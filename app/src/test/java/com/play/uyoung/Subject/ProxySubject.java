package com.play.uyoung.Subject;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class ProxySubject implements Subject{
    RealSubject realSubject = new RealSubject();

    @Override
    public void doSomething() {
        System.out.println("调用真实对象之前 haha");
        realSubject.doSomething();
        System.out.println("调用真实对象之后 haha");
    }

    @Override
    public void doWhat() {
        realSubject.doWhat();
    }
}
