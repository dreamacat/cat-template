package com.cat.designpattern.state.lift_v3;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        context.setLiftState(Context.closingState);
        context.open();
        context.close();
        context.run();
        context.stop();
        context.open();

    }
}
