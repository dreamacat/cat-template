package com.cat.designpattern.state.lift_v1;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public interface ILift {
    //电梯要能开门
    void open();
    //能开就要能关
    void close();
    //不能上下运行就不是电梯了
    void run();
    //能运行不能停就太可怕了
    void stop();
}
