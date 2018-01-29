package com.play.uyoung.dynamicproxy;


/**
 * Created by wangweijun on 2018/1/28.
 */

public class RealPerson implements Person {
    @Override
    public void doSomething() {
        System.out.println("real doSomething ...");
    }

    @Override
    public void doWhat() {
        System.out.println("real doWhat ...");
    }
}
