package com.horqian.basic.mapper;

import com.horqian.basic.entity.SysPermissionTbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
public interface SysPermissionTblMapper extends BaseMapper<SysPermissionTbl> {

    List<SysPermissionTbl> selectByParentId(Long parentId);

    void deleteByParentId(Long parentId);

}
