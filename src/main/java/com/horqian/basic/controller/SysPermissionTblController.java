package com.horqian.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.horqian.basic.annotation.PassToken;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysPermissionTbl;
import com.horqian.basic.entity.SysRolePermissionTbl;
import com.horqian.basic.service.SysPermissionTblService;
import com.horqian.basic.service.SysRolePermissionTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
@Api(tags = "权限管理")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionTblController {

    @Autowired
    private SysPermissionTblService sysPermissionTblService;
    @Autowired
    private SysRolePermissionTblService sysRolePermissionTblService;

    @ApiOperation("查询权限树")
    @GetMapping("/selectTree")
    public CommonResult selectTree(){
        List<SysPermissionTbl> list = sysPermissionTblService.selectByParentId(1L);
        List<SysPermissionTbl> sonList = new ArrayList<>();
        for(SysPermissionTbl tbl: list){
            tbl.setChildrenList(new ArrayList<>());
            sonList.addAll(sysPermissionTblService.selectByParentId(tbl.getId()));
        }
        if(sonList != null && sonList.size() > 0){
            for(SysPermissionTbl tbl : sonList){
                tbl.setChildrenList(sysPermissionTblService.selectByParentId(tbl.getId()));
                for(SysPermissionTbl parentTbl : list){
                    if(parentTbl.getId().equals(tbl.getParentId())){
                        parentTbl.getChildrenList().add(tbl);
                        break;
                    }
                }
            }
        }
        return CommonResponse.makeRsp(CommonCode.SUCCESS,list);
    }

    @ApiOperation("新建权限")
    @PostMapping("/add")
    public CommonResult add(@RequestBody SysPermissionTbl sysPermissionTbl) {
        boolean save = sysPermissionTblService.save(sysPermissionTbl);
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("修改权限")
    @PostMapping("/update")
    public CommonResult update(@RequestBody SysPermissionTbl sysPermissionTbl) {
        boolean update = sysPermissionTblService.updateById(sysPermissionTbl);
        if (update) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("删除权限")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam Long id) {
        //查询是否有子权限
        List<SysPermissionTbl> list = sysPermissionTblService.selectByParentId(id);
        List<Long> idList = new ArrayList<>();
        List<SysPermissionTbl> childrenList = new ArrayList<>();
        if(list != null && list.size() >0){
            for(SysPermissionTbl tbl : list){
                idList.add(tbl.getId());
                childrenList.addAll(sysPermissionTblService.selectByParentId(tbl.getId()));
            }
        }
        if(childrenList != null && childrenList.size() > 0){
            for(SysPermissionTbl tbl : childrenList){
                idList.add(tbl.getId());
            }
        }
        idList.add(id);
        //删除权限
        sysPermissionTblService.removeByIds(idList);
        //删除角色拥有的权限
        QueryWrapper<SysRolePermissionTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("permission_id",idList);
        boolean delete = sysRolePermissionTblService.remove(queryWrapper);
        if (delete) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

}

