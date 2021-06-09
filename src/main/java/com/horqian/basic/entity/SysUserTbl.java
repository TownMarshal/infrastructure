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
public class SysUserTbl implements Serializable, UserDetails {

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

    private String userName;

    private String loginName;

    private String password;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer deleteFlag;

    @TableField(exist = false)
    private String token;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色列表")
    private List<SysUserRoleView> roleList;

    @TableField(exist = false)
    @ApiModelProperty(value = "权限列表")
    private List<SysRolePermissionView> permissionList;


    //获取用户角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(roleList != null){
            for (SysUserRoleView role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    //是否过期 重写方法数据库加上相关字段即可
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否已被锁定 重写方法数据库加上相关字段即可
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否过期 重写方法数据库加上相关字段即可
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否禁用 重写方法数据库加上相关字段即可
    @Override
    public boolean isEnabled() {
        return true;
    }
}
