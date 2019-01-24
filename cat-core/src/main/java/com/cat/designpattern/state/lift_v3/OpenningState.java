package com.cat.designpattern.state.lift_v3;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public class OpenningState extends LiftState {
    @Override
    public void open() {
        System.out.println("电梯门开启...");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.closingState);
        super.context.close();
    }

    @Override
    public void run() {
        //do nothing

    }

    @Override
    public void stop() {
        //do nothing
    }
}
