package com.horqian.basic.service.impl;

import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.mapper.SysUserTblMapper;
import com.horqian.basic.service.SysUserTblService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
@Service
public class SysUserTblServiceImpl extends ServiceImpl<SysUserTblMapper, SysUserTbl> implements SysUserTblService {

    @Override
    public SysUserTbl selectByUserName(String userName) {
        return this.baseMapper.selectByUserName(userName);
    }
}
