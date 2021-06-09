package com.horqian.basic.service;

import com.horqian.basic.entity.SysPermissionTbl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
public interface SysPermissionTblService extends IService<SysPermissionTbl> {

    List<SysPermissionTbl> selectByParentId(Long parentId);

    void deleteByParentId(Long parentId);

}
