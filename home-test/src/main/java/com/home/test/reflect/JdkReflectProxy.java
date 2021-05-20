package com.home.test.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author malikai
 * @version 1.0
 * @date 2021-4-22 10:01
 */
public class JdkReflectProxy implements InvocationHandler {

    private Object target;

    public Object bind(Object target) {
        this.target = target;

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk 动态代理对象");
        Object result;
        System.out.println("我准备获取用户...");
        result = method.invoke(target, args);
        System.out.println("获取用户结束...");

        return result;
    }
}
