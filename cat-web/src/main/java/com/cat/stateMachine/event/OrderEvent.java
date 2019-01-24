package com.cat.stateMachine.event;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wangxiaoqiang
 * @since 2019/01/24
 **/
@Data
public class OrderEvent implements Serializable {
    private Long orderId;
}
