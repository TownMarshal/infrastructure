package com.horqian.basic.entity;

import com.horqian.basic.vo.SysRolePermissionView;
import com.horqian.basic.vo.SysUserRoleView;;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDetails implements  UserDetails {

    private SysUserTbl sysUserTbl;

    private String token;

    private List<SysUserRoleView> roleList;

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
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return sysUserTbl.getUserName();
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
