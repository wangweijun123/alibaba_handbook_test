package com.play.uyoung.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangweijun on 2018/1/28.
 */

public class ProviderFactory {

    public static FontProvider getFontProvider() {
        return (FontProvider) Proxy.newProxyInstance(FontProvider.class.getClassLoader(),
                new Class[]{FontProvider.class},
                new CachedProviderHandler(new FontProviderFromDisk()));

//        return new CacheFontProvider(new FontProviderFromDisk());
    }

    public static MusicProvider getMusicProvider() {
//        return new MusicProviderFromDisk();
        return (MusicProvider) Proxy.newProxyInstance(MusicProvider.class.getClassLoader(),
                new Class[]{MusicProvider.class},
                new CachedProviderHandler(new MusicProviderFromDisk()));
    }


    public static class CachedProviderHandler implements InvocationHandler {
        private Map<String, Object> cached = new HashMap<>();
        private Object target;

        public CachedProviderHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Type[] types = method.getParameterTypes();;
            System.out.println(" 方法名字:"+method.getName()+", types length:"+types.length+", types[0]:"+types[0]);
            if (method.getName().matches("get.+") && (types.length == 1) && (types[0] == String.class)) {
                String key = (String) args[0];
                Object value = cached.get(key);
                if (value == null) {
                    System.out.println("缓存没有对应名字:"+key);
                    value = method.invoke(target, args);
                    cached.put(key, value);
                } else {
                    System.out.println("缓存存在 : "+key);
                }
                return value;
            }
            return method.invoke(target, args);
        }
    }

}
