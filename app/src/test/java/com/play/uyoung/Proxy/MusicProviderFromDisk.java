package com.play.uyoung.Proxy;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class MusicProviderFromDisk implements MusicProvider {
    @Override
    public Music getMusic(String name) {
        System.out.println("new Music()");
        return new Music(name);
    }
}
