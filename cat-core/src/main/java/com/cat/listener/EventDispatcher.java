package com.cat.listener;

import com.cat.dao.user.LeaseCompanyDao;
import com.cat.model.LeaseCompanyDO;
import com.cat.service.LeaseCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangxiaoqiang
 * @since 2018/10/08
 **/
@Slf4j
@Component
public class EventDispatcher {

    private static ConcurrentHashMap<String, List<EventHandler>> handlersMap = new ConcurrentHashMap<>();

    @Autowired
    private LeaseCompanyService leaseCompanyService;

    /**
     * 异步，无论是否有事务，都执行
     * @param event
     */
    @Async
    @TransactionalEventListener(fallbackExecution = true, phase = TransactionPhase.AFTER_COMMIT)
    public void handleEventAsynAfterCommit(AsyncEventEntity event) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println();
        }
        log.info("事件通知开始执行------start-----：event:[{}]  time:[{}]  threadId:[{}] ,traceId:[{}]", event.getTypeEnum().getDesc(),
                event.getTimestamp(), Thread.currentThread().getId(), event.getTraceId());

        doHandler(event);
        log.info("事件通知开始执行------end-----：[{}] [{}}] ", event.getTypeEnum().getDesc(), event.getTimestamp());

    }

    /**
     * 同步，无论是否有事务，都执行
     * @param event
     */
    @TransactionalEventListener(fallbackExecution = true)
    public void handleEventAfterCommit(SyncEventEntity event) {
        log.info("事件通知开始执行------start-----：event:[{}]  time:[{}]  threadId:[{}] ,traceId:[{}]", event.getTypeEnum().getDesc(),
                event.getTimestamp(), Thread.currentThread().getId(), event.getTraceId());
        doHandler(event);
        log.info("事件通知开始执行------end-----：[{}] [{}}] ", event.getTypeEnum().getDesc(), event.getTimestamp());


    }

    private void doHandler(AbstractEvent event) {
        leaseCompanyService.getLeaseCompanyByCode("000000");
        List<EventHandler> handlers = handlersMap.get(event.getTypeEnum().name());
        if (CollectionUtils.isEmpty(handlers)) {
            log.warn("无可执行的handler，typeName = " + event.getTypeEnum().name());
            return;
        }

        Object obj = event.getData();
        if (obj instanceof LeaseCompanyDO) {
            System.out.println("doTransactionAfterCommitAsynEvent:" +((LeaseCompanyDO) obj).getLeaseCompanyName());
        } else {
            System.out.println("不知道是啥");
        }
    }
}
