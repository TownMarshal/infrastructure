package com.horqian.basic.mapper;

import com.horqian.basic.entity.SysUserTbl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2021-06-09
 */
public interface SysUserTblMapper extends BaseMapper<SysUserTbl> {

    SysUserTbl selectByUserName(String userName);

}
