package com.horqian.basic.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author macro
 * @create 2020-11-05-14:12
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CommonCode {
    //文件不匹配
    FILETYPEFAIL(5001, "文件类型不匹配"),
    // 成功
    SUCCESS(200, "成功"),
    // 失败
    FAIL(400, "失败"),
    // 未认证（签名错误）
    UNAUTHORIZED(401, "未认证"),
    // 接口不存在
    NOT_FOUND(404, "接口不存在"),
    // 夫妻已投票
    IS_VOTE(201, "夫妻已经投票"),
    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");


    private int code;
    private String message;
}
