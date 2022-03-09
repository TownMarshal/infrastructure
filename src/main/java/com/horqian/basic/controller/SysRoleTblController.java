package com.horqian.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonPageParam;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysRolePermissionTbl;
import com.horqian.basic.entity.SysRoleTbl;
import com.horqian.basic.service.SysRolePermissionTblService;
import com.horqian.basic.service.SysRoleTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/sysRole")
public class SysRoleTblController {

    @Autowired
    private SysRoleTblService sysRoleTblService;
    @Autowired
    private SysRolePermissionTblService sysRolePermissionTblService;

    @ApiOperation("角色下拉列表")
    @GetMapping("/selectList")
    public CommonResult selectList() {
        QueryWrapper<SysRoleTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "role_name");
        List<Map<String, Object>> maps = sysRoleTblService.listMaps(queryWrapper);
        return CommonResponse.makeRsp(CommonCode.SUCCESS, maps);
    }

    @ApiOperation("查询角色")
    @PostMapping("/select")
    public CommonResult select(@RequestBody CommonPageParam<SysRoleTbl> commonPageParam) {
        QueryWrapper<SysRoleTbl> queryWrapper = new QueryWrapper<>(commonPageParam.getData());
        IPage<SysRoleTbl> page = sysRoleTblService.page(commonPageParam.getPage(), queryWrapper);
        return CommonResponse.makePageRsp(CommonCode.SUCCESS, page);
    }

    @ApiOperation("新建角色")
    @PostMapping("/add")
    public CommonResult add(@RequestBody SysRoleTbl sysRoleTbl) {
        boolean save = sysRoleTblService.save(sysRoleTbl);
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public CommonResult update(@RequestBody SysRoleTbl sysRoleTbl) {
        boolean update = sysRoleTblService.updateById(sysRoleTbl);
        if (update) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @Secured("ROLE_admin")
    @ApiOperation("删除角色")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam Long id) {
        boolean delete = sysRoleTblService.removeById(id);
        //删除角色的权限
        QueryWrapper<SysRolePermissionTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",id);
        sysRolePermissionTblService.remove(queryWrapper);
        if (delete) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

}

