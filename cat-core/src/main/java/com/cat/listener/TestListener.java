package com.cat.listener;

import com.cat.constant.enums.EventTypeEnum;
import com.cat.model.EntityVO;
import com.cat.model.LeaseCompanyDO;
import com.cat.utils.EventEntity;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author wangxiaoqiang
 * @since 2018/09/19
 **/

@Component
public class TestListener {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(LeaseCompanyDO event) {
        System.out.println(" before commit 当前线程："+Thread.currentThread().getName());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, fallbackExecution = true)
    public void afterCommit(LeaseCompanyDO event) {
        System.out.println(" after commit 当前线程："+Thread.currentThread().getName());
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void afterCompletion(LeaseCompanyDO event) {
        System.out.println(" after completion 当前线程："+Thread.currentThread().getName());
    }
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(LeaseCompanyDO event) {
        System.out.println(" after rollback 当前线程："+Thread.currentThread().getName());
    }
}
