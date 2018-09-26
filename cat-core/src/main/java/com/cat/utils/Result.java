package com.cat.utils;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/
public class Result<T> {
    private boolean success = false;
    private T data = null;
    private String msg = "";
    private String code = "500";
    private String traceId;

    public Result() {
        this.setupTraceId();
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result();
        r.setData(data);
        r.setSuccess(true);
        r.setCode("200");
        r.setMsg("success");
        return r;
    }

    public static <T> Result<T> fail(String code, String msg) {
        Result<T> r = new Result();
        r.setSuccess(false);
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    private void setupTraceId() {
        try {
            try {
                this.traceId = "trace_" + System.currentTimeMillis();
            } catch (Throwable var2) {
                this.traceId = "trace_" + System.currentTimeMillis();
            }
        } catch (Throwable var3) {
            ;
        }

    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }
}