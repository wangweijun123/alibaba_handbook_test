package com.play.uyoung.Proxy;


/**
 * Created by wangweijun on 2018/1/28.
 */

public class FontProviderFromDisk implements FontProvider {
    @Override
    public Font getFont(String name) {
        System.out.println("new Font()");
        return new Font(name);
    }
}
