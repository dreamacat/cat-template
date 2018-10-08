package com.cat.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by gaoxiaoran on 2018/4/11.
 */
@Data
@AllArgsConstructor
public class AsyncException extends Exception {

    private static final long serialVersionUID = 3079029334996494789L;

    private int code;
    private String errorMessage;
}
