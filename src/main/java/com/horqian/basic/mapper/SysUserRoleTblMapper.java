package com.horqian.basic.mapper;

import com.horqian.basic.entity.SysUserRoleTbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.horqian.basic.entity.SysUserTbl;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
public interface SysUserRoleTblMapper extends BaseMapper<SysUserRoleTbl> {

    List<SysUserTbl> selectUserByRoleId(Long roleId);

}
