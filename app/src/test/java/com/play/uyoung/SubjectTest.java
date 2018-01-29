package com.play.uyoung;

import com.play.uyoung.Subject.ProxyHandler;
import com.play.uyoung.Subject.ProxySubject;
import com.play.uyoung.Subject.RealSubject;
import com.play.uyoung.Subject.Subject;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SubjectTest {
    @Test
    public void testJavaDynamicProxy() throws Exception {

        Subject subject = (Subject)new ProxyHandler().bind(new RealSubject());

        subject.doSomething();

//        subject.doWhat();


    }

    @Test
    public void testJavaStaticProxy() throws Exception {

        Subject subject = new ProxySubject();

        subject.doSomething();
    }

    @Test
    public void testProxy() throws Exception {
        Subject s2 = returnProxy(new RealSubject());
        System.out.println("s2:"+s2);
        s2.doSomething();
    }


    private Subject returnProxy(final Subject subject) {
        System.out.println("subject:"+subject);
        return (Subject)Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),// 注意这个class[]
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(subject, args);
                    }
                });
    }
}