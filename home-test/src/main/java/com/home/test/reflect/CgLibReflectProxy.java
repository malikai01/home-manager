package com.home.test.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 *
 * @author malikai
 * @version 1.0
 * @date 2021-4-22 10:23
 */
public class CgLibReflectProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getInstance(Class clazz) {
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
        System.out.println("-------------------------------");
        System.out.println("我是cglib代理对象");


        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object returnObj = methodProxy.invokeSuper(obj, arg);

        //反射方法后调用
        System.out.println("后置代理");

        return returnObj;
    }
}
