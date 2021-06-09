package com.horqian.basic.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AjaxResponseBody implements Serializable {

    private Integer status;
    private String msg;
    private Object result;

}

