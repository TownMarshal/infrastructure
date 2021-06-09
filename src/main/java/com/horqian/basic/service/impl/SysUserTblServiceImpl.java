package com.horqian.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.mapper.SysUserTblMapper;
import com.horqian.basic.service.SysRolePermissionViewService;
import com.horqian.basic.service.SysUserRoleViewService;
import com.horqian.basic.service.SysUserTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.horqian.basic.utils.JwtUtils;
import com.horqian.basic.vo.SysRolePermissionView;
import com.horqian.basic.vo.SysUserRoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
@Service
public class SysUserTblServiceImpl extends ServiceImpl<SysUserTblMapper, SysUserTbl> implements SysUserTblService, UserDetailsService {

    @Autowired
    private SysUserRoleViewService sysUserRoleViewService;
    @Autowired
    private SysRolePermissionViewService sysRolePermissionViewService;

    @Override
    public SysUserTbl loadUserByUsername(String username) throws UsernameNotFoundException {
        //首先通过用户名查询是否有该用户
        QueryWrapper<SysUserTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        SysUserTbl sysUserTbl = this.baseMapper.selectOne(queryWrapper);
        //如果用户存在就开始执行身份认证以及授权的相关操作
        if(sysUserTbl != null) {
            //查询出来该用户的角色列表
            QueryWrapper<SysUserRoleView> roleWrapper = new QueryWrapper<>();
            roleWrapper.eq("user_id",sysUserTbl.getId());
            List<SysUserRoleView> roleList=sysUserRoleViewService.list(roleWrapper);
            //权限列表
            List<SysRolePermissionView> permissionList=new ArrayList<>();
            for(SysUserRoleView role : roleList) {
                QueryWrapper<SysRolePermissionView> permissionWrapper = new QueryWrapper<>();
                permissionWrapper.eq("role_id",role.getId());
                permissionList.addAll(sysRolePermissionViewService.list(permissionWrapper));
            }
            String token = JwtUtils.createToken(sysUserTbl.getId().toString(), sysUserTbl.getLoginName(),sysUserTbl.getUsername());
            sysUserTbl.setToken(token);
            sysUserTbl.setRoleList(roleList);
            sysUserTbl.setPermissionList(permissionList);
            return sysUserTbl;

        } else {
            throw new UsernameNotFoundException("用户名不存在!");
        }
    }
}
