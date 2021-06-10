package com.horqian.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.horqian.basic.vo.SysRolePermissionView;
import com.horqian.basic.vo.SysUserRoleView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserTbl对象", description="")
public class SysUserTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "单位id")
    private Long unitId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "部门id")
    private Long departmentId;

    private String phone;

    private String loginName;

    @TableField(select = false)
    private String password;

    private String userName;

    @TableField(select = false)
    private Date createTime;

    @TableField(select = false)
    private Date updateTime;

    @TableLogic
    @TableField(select = false)
    private Integer deleteFlag;


}
