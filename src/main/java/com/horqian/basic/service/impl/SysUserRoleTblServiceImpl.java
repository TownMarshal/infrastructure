package com.horqian.basic.service.impl;

import com.horqian.basic.entity.SysUserRoleTbl;
import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.mapper.SysUserRoleTblMapper;
import com.horqian.basic.service.SysUserRoleTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class SysUserRoleTblServiceImpl extends ServiceImpl<SysUserRoleTblMapper, SysUserRoleTbl> implements SysUserRoleTblService {

    @Override
    public List<SysUserTbl> selectUserByRoleId(Long roleId) {
        return this.baseMapper.selectUserByRoleId(roleId);
    }
}
