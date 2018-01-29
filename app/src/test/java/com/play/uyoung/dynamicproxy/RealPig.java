package com.play.uyoung.dynamicproxy;

/**
 * Created by wangweijun on 2018/1/29.
 */

public class RealPig implements Pig {

    @Override
    public void eat() {
        System.out.println("real eat ...");
    }
}
