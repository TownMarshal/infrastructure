package com.horqian.basic.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRoleReq  implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private Long[] roleIdList;
}
