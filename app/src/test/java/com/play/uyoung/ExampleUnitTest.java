package com.play.uyoung;

import com.play.uyoung.Proxy.Font;
import com.play.uyoung.Proxy.FontProvider;
import com.play.uyoung.Proxy.Music;
import com.play.uyoung.Proxy.MusicProvider;
import com.play.uyoung.Proxy.ProviderFactory;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testJavaProxy() throws Exception {
        FontProvider fontProvider = ProviderFactory.getFontProvider();
        Font font = fontProvider.getFont("微软雅黑");
        System.out.println(font.name);
        System.out.println("再来一次字体");
        font = fontProvider.getFont("微软雅黑");
        System.out.println(font.name);

        System.out.println("##############");

        MusicProvider musicProvider = ProviderFactory.getMusicProvider();
        Music music = musicProvider.getMusic("周杰伦");
        System.out.println(music.name);
        System.out.println("再来一次哥");
        music = musicProvider.getMusic("周杰伦");
        System.out.println(music.name);
    }
}