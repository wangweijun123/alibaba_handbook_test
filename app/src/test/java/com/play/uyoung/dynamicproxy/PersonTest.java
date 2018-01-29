package com.play.uyoung.dynamicproxy;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PersonTest {
    @Test
    public void testJavaDynamicProxy() throws Exception {
        // jdk dynamic proxy
        ProxyPerson proxySubject = new ProxyPerson(new RealPerson());
        Person subject = (Person) proxySubject.getProxy();
        subject.doSomething();
    }

    @Test
    public void testJavaDynamicProxyForT() throws Exception {
        ProxyPerson proxySubject = new ProxyPerson();
        // jdk dynamic proxy
        Person subject = proxySubject.getProxy(new RealPerson());
        subject.doSomething();
    }

    @Test
    public void testRealPigForT() throws Exception {
        ProxyPerson proxySubject = new ProxyPerson();
        // jdk dynamic proxy
        Pig pig = proxySubject.getProxy(new RealPig());
        pig.eat();
    }


}