package com.play.uyoung.Subject;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class RealSubject implements Subject{
    @Override
    public void doSomething() {
        System.out.println("real doSomething ...");
    }

    @Override
    public void doWhat() {
        System.out.println("real doWhat ...");
    }
}
