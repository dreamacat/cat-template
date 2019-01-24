package com.cat.designpattern.state.lift_v2;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public interface ILift {

    int OPENING_STATE = 1;
    int CLOSING_STATE = 2;
    int RUNNING_STATE = 3;
    int STOPPING_STATE = 4;

    void setState(int state);

    void open();
    void close();
    void run();
    void stop();
}
