package com.home.test.chain;

/**
 * @author little cloth
 * @date 2021年11月17日 17:23
 */
public interface Handler {
    public abstract void handleRequest(int number);   //具体处理用户请求60钻石抽一次还是270钻石抽五次
    public abstract void setNextHandler(Handler handler);
}
