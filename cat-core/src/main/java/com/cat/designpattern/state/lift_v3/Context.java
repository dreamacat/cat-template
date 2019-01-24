package com.cat.designpattern.state.lift_v3;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public class Context {

    public final static OpenningState openningState = new OpenningState();
    public final static ClosingState closingState = new ClosingState();
    public final static StoppingState stoppingState = new StoppingState();
    public final static RunningState runningState = new RunningState();



    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }
    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }
    public void stop() {
        this.liftState.stop();
    }


}
