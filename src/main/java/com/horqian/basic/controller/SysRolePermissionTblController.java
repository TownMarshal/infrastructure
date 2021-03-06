package com.horqian.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysRolePermissionTbl;
import com.horqian.basic.service.SysRolePermissionTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色权限管理")
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionTblController {

    @Autowired
    private SysRolePermissionTblService sysRolePermissionTblService;

    @ApiOperation("角色授予权限")
    @PostMapping("/update")
    public CommonResult add(@RequestBody List<SysRolePermissionTbl> list) {
        QueryWrapper<SysRolePermissionTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",list.get(0).getRoleId());
        sysRolePermissionTblService.remove(queryWrapper);
        boolean save = sysRolePermissionTblService.saveBatch(list);
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("查询角色权限")
    @GetMapping("/selectRolePermission")
    public CommonResult selectRolePermission(@RequestParam Long roleId) {
        QueryWrapper<SysRolePermissionTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        List<SysRolePermissionTbl> list = sysRolePermissionTblService.list(queryWrapper);
        if (list != null && list.size() > 0) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS,list);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

}
