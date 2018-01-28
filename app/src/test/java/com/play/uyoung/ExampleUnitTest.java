package com.play.uyoung;

import com.play.uyoung.Proxy.Font;
import com.play.uyoung.Proxy.FontProvider;
import com.play.uyoung.Proxy.ProviderFactory;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() throws Exception {
        FontProvider fontProvider = ProviderFactory.getFontProvider();
        Font font = fontProvider.getFont("微软雅黑");
        System.out.println(font.name);
        System.out.println("##############");
        font = fontProvider.getFont("微软雅黑");
        System.out.println(font.name);
    }
}