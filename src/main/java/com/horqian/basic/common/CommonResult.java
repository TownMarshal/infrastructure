package com.horqian.basic.common;

import lombok.Data;

/**
 * @author macro
 * @create 2020-11-05-14:02
 **/
@Data
public class CommonResult<T> {
    public int code;
    private String msg;
    private T data;
}
