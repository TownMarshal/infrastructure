package com.horqian.basic.service;

import com.horqian.basic.entity.SysUserRoleTbl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.horqian.basic.entity.SysUserTbl;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
public interface SysUserRoleTblService extends IService<SysUserRoleTbl> {

    List<SysUserTbl> selectUserByRoleId(Long roleId);

}
