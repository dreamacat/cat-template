package com.cat.designpattern.state.lift_v2;

/**
 * @author wangxiaoqiang
 * @since 2019/01/21
 **/
public class Lift implements ILift {
    private int state;

    @Override
    public void setState(int state) {
        this.state = state;
    }

    private void closeWithoutLogic() {
        System.out.println("电梯关门...");
    }

    private void runWithoutLogic() {
        System.out.println("电梯上下运行...");
    }

    private void stopWithoutLogic() {
        System.out.println("电梯停止...");
    }

    private void openWithoutLogic() {
        System.out.println("电梯开门...");
    }

    @Override
    public void open() {
        switch (this.state) {
            case OPENING_STATE :
                //do nothing
                break;
            case STOPPING_STATE :
                openWithoutLogic();
                setState(STOPPING_STATE);
                break;
            case RUNNING_STATE :
                //do nothing
                break;
            case CLOSING_STATE :
                openWithoutLogic();
                setState(CLOSING_STATE);
                break;
            default:
                break;
        }
    }

    @Override
    public void close() {
        switch (this.state) {
            case OPENING_STATE :
                closeWithoutLogic();
                this.setState(CLOSING_STATE);
                break;
            case STOPPING_STATE :
                //do nothing
                break;
            case RUNNING_STATE :
                //do nothing
                break;
            case CLOSING_STATE :
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        switch (this.state) {
            case OPENING_STATE :
                //do nothing
                break;
            case STOPPING_STATE :
                runWithoutLogic();
                setState(RUNNING_STATE);
                //do nothing
                break;
            case RUNNING_STATE :
                //do nothing
                break;
            case CLOSING_STATE :
                runWithoutLogic();
                setState(RUNNING_STATE);
                break;
            default:
                break;
        }
    }

    @Override
    public void stop() {
        switch (this.state) {
            case OPENING_STATE :
                //do nothing
                break;
            case STOPPING_STATE :
                //do nothing
                break;
            case RUNNING_STATE :
                //do nothing
                stopWithoutLogic();
                setState(CLOSING_STATE);
                break;
            case CLOSING_STATE :
                stopWithoutLogic();
                setState(CLOSING_STATE);
                break;
            default:
                break;
        }
    }
}
