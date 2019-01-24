package com.cat.designpattern.state.lift_v3;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 * <p>
 *`这个类主要做两个事情：
 * 1.状态是怎么来的？
 * 2.这个状态下能做什么？
 * </p>
 *
 **/
public abstract class LiftState {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();

}
