package com.home.test.reflect;

/**
 * @author malikai
 * @version 1.0
 * @date 2021-4-22 9:58
 */
public class UserServiceImpl implements IUserService {
    @Override
    public void getUser(String name) {
        System.out.println("this is " + name + ",test reflect...");
    }
}
