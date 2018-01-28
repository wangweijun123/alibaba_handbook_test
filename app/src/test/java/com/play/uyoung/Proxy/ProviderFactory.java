package com.play.uyoung.Proxy;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class ProviderFactory {

    public static FontProvider getFontProvider() {
        return new CacheFontProvider(new FontProviderFromDisk());
    }
}
