package com.cat.designpattern.state.lift_v1;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public class Lift implements ILift {
    @Override
    public void open() {
        System.out.println("电梯开门...");
    }

    @Override
    public void close() {
        System.out.println("电梯关门...");
    }

    @Override
    public void run() {
        System.out.println("电梯上下运行...");
    }

    @Override
    public void stop() {
        System.out.println("电梯停止...");
    }
}
