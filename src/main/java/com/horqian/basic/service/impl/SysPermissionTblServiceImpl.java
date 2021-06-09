package com.horqian.basic.service.impl;

import com.horqian.basic.entity.SysPermissionTbl;
import com.horqian.basic.mapper.SysPermissionTblMapper;
import com.horqian.basic.service.SysPermissionTblService;
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
public class SysPermissionTblServiceImpl extends ServiceImpl<SysPermissionTblMapper, SysPermissionTbl> implements SysPermissionTblService {

    @Override
    public List<SysPermissionTbl> selectByParentId(Long parentId) {
        return this.baseMapper.selectByParentId(parentId);
    }

    @Override
    public void deleteByParentId(Long parentId) {
         this.baseMapper.deleteByParentId(parentId);
    }
}
