package com.play.uyoung.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class CacheFontProvider implements FontProvider{
    Map<String, Font> map = new HashMap<>();

    FontProvider fontProvider;

    public CacheFontProvider(FontProvider fontProvider){
        this.fontProvider = fontProvider;
    }

    @Override
    public Font getFont(String name) {
        Font font = map.get(name);
        if (font == null) {
            System.out.println("缓存没有对应名字:"+name);
            font = fontProvider.getFont(name);
            map.put(name, font);
        } else {
            System.out.println("缓存存在:"+name);
        }

        return font;
    }
}
