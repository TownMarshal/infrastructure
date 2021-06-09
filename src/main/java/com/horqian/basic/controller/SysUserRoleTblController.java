package com.horqian.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysRolePermissionTbl;
import com.horqian.basic.entity.SysUserRoleTbl;
import com.horqian.basic.service.SysUserRoleTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户角色管理")
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleTblController {

    @Autowired
    private SysUserRoleTblService sysUserRoleTblService;

    @ApiOperation("用户授予角色")
    @PostMapping("/update")
    public CommonResult add(@RequestBody List<SysUserRoleTbl> list) {
        QueryWrapper<SysUserRoleTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",list.get(0).getRoleId());
        sysUserRoleTblService.remove(queryWrapper);
        boolean save = sysUserRoleTblService.saveBatch(list);
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

}
