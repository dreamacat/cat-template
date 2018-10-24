package com.cat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author WangLei
 * on 2018/8/7
 */
@Slf4j
@Component
public class AsyncRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            log.error(" !!! Async Event Reject, Error :{}", e);
            Thread.currentThread().interrupt();
        }
    }
}
