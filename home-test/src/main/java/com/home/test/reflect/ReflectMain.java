package com.home.test.reflect;

/**
 * @author malikai
 * @version 1.0
 * @date 2021-4-22 10:09
 */
public class ReflectMain {
    public static void main(String[] args) {
        JdkReflectProxy proxy = new JdkReflectProxy();
        IUserService userService = (IUserService) proxy.bind(new UserServiceImpl());
        userService.getUser("jdk动态代理");

        CgLibReflectProxy cgLibReflect = new CgLibReflectProxy();
        UserServiceImpl hello = (UserServiceImpl) cgLibReflect.getInstance(UserServiceImpl.class);

        hello.getUser("cglib动态代理");
    }
}
