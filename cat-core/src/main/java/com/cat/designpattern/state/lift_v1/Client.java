package com.cat.designpattern.state.lift_v1;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public class Client {
    public static void main(String[] args) {
        ILift lift = new Lift();
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
        lift.open();
    }
}
