package com.play.uyoung;

import com.play.uyoung.Proxy.Font;
import com.play.uyoung.Proxy.FontProvider;
import com.play.uyoung.Proxy.JavaProxy;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JavaProxyTest {
    @Test
    public void testJavaProxy() throws Exception {

        FontProvider fontProvider = new JavaProxy().create(FontProvider.class);

        Font font = fontProvider.getFont("xxxx");
        System.out.println("name:" + font.name);

        System.out.println("##############");

//        MusicProvider musicProvider = new JavaProxy().create(MusicProvider.class);
//        System.out.println("" + musicProvider);
    }
}