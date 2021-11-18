package com.home.test.chain;

/**
 * @author little cloth
 * @date 2021年11月17日 17:25
 */
public class RareCrystalOfKings implements Handler {
    private Handler handler;      //存放当前处理者后继的Hander接口变量

    public void handleRequest(int number) {
        if (number == 201) {               //当幸运值满201时，出稀有水晶
            System.out.println("【稀有】王者水晶");
        } else
            handler.handleRequest(number);      //将请求传递给下一个处理者
    }

    public void setNextHandler(Handler handler) {
        this.handler = handler;
    }
}
