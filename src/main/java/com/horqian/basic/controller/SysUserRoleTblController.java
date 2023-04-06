package com.horqian.basic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysUserRoleTbl;
import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.service.SysUserRoleTblService;
import com.horqian.basic.service.SysUserRoleViewService;
import com.horqian.basic.vo.SysUserRoleReq;
import com.horqian.basic.vo.SysUserRoleView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "用户角色管理")
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleTblController {

    @Autowired
    private SysUserRoleTblService sysUserRoleTblService;
    @Autowired
    private SysUserRoleViewService sysUserRoleViewService;

    @ApiOperation("用户授予角色")
    @PostMapping("/update")
    public CommonResult add(@RequestBody SysUserRoleReq req) {
        QueryWrapper<SysUserRoleTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", req.getUserId());
        sysUserRoleTblService.remove(queryWrapper);
        boolean save = true;
        if (req.getRoleIdList() != null && req.getRoleIdList().length > 0) {
            List<SysUserRoleTbl> list = new ArrayList<>();
            for (Long roleId : req.getRoleIdList()) {
                SysUserRoleTbl tbl = new SysUserRoleTbl();
                tbl.setUserId(req.getUserId());
                tbl.setRoleId(roleId);
                list.add(tbl);
            }
            save = sysUserRoleTblService.saveBatch(list);
        }
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("根据角色查询用户")
    @GetMapping("/selectUserByRoleId")
    public CommonResult selectUserByRoleId(@RequestParam Long roleId) {
        List<SysUserTbl> list = sysUserRoleTblService.selectUserByRoleId(roleId);
        if (list != null && list.size() > 0) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS, list);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("查询用户角色")
    @GetMapping("/selectRoleByUserId")
    public CommonResult selectRoleByUserId(@RequestParam Long userId) {
        //查询出来该用户的角色列表
        QueryWrapper<SysUserRoleView> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("user_id", userId);
        List<SysUserRoleView> list = sysUserRoleViewService.list(roleWrapper);
        return CommonResponse.makeRsp(CommonCode.SUCCESS, list);
    }
}
