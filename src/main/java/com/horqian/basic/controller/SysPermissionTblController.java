package com.horqian.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.horqian.basic.annotation.PassToken;
import com.horqian.basic.annotation.SysLog;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysPermissionTbl;
import com.horqian.basic.entity.SysRolePermissionTbl;
import com.horqian.basic.service.SysPermissionTblService;
import com.horqian.basic.service.SysRolePermissionTblService;
import com.horqian.basic.service.SysRolePermissionViewService;
import com.horqian.basic.service.SysUserRoleViewService;
import com.horqian.basic.utils.JwtUtils;
import com.horqian.basic.vo.SysRolePermissionView;
import com.horqian.basic.vo.SysUserRoleView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @Autowired
    private SysUserRoleViewService sysUserRoleViewService;
    @Autowired
    private SysRolePermissionViewService sysRolePermissionViewService;

    @ApiOperation("查询权限树")
    @GetMapping("/selectTree")
    public CommonResult selectTree() {
        List<SysPermissionTbl> list = sysPermissionTblService.selectByParentId(1L);
        List<SysPermissionTbl> sonList = new ArrayList<>();
        for (SysPermissionTbl tbl : list) {
            tbl.setChildrenList(new ArrayList<>());
            sonList.addAll(sysPermissionTblService.selectByParentId(tbl.getId()));
        }
        if (sonList.size() > 0) {
            for (SysPermissionTbl tbl : sonList) {
                tbl.setChildrenList(sysPermissionTblService.selectByParentId(tbl.getId()));
                for (SysPermissionTbl parentTbl : list) {
                    if (parentTbl.getId().equals(tbl.getParentId())) {
                        parentTbl.getChildrenList().add(tbl);
                        break;
                    }
                }
            }
        }
        return CommonResponse.makeRsp(CommonCode.SUCCESS, list);
    }
/*
 select * from sys_permission_tbl
      where parent_id = #{parentId}
      order by order_num
 */
    @ApiOperation("根据用户查询权限树")
    @GetMapping("/selectTreeByUserId")
    public CommonResult selectTreeByUserId(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Access-Token");
        List<SysRolePermissionView> list = new ArrayList<>();
        List<SysRolePermissionView> returnList = new ArrayList<>();
        if (token != null) {
            String userId = JwtUtils.getAudience(token);
            //查询出来该用户的角色列表
            QueryWrapper<SysUserRoleView> roleWrapper = new QueryWrapper<>();
            roleWrapper.eq("user_id", userId);
            List<SysUserRoleView> roleList = sysUserRoleViewService.list(roleWrapper);
            if (roleList != null && roleList.size() > 0) {
                List<Long> roleIdList = new ArrayList<>();
                for (SysUserRoleView role : roleList) {
                    roleIdList.add(role.getId());
                }
                QueryWrapper<SysRolePermissionView> permissionWrapper = new QueryWrapper<>();
                permissionWrapper.in("role_id", roleIdList);
                permissionWrapper.groupBy("id");
                list.addAll(sysRolePermissionViewService.list(permissionWrapper));
            }
            if (list.size() > 0) {
                List<SysRolePermissionView> sonList = new ArrayList<>();
                //根权限
                for (SysRolePermissionView view : list) {
                    if (view.getParentId().equals(1L)) {
                        view.setChildrenList(new ArrayList<>());
                        returnList.add(view);
                    }
                }
                //子权限
                for (SysRolePermissionView view : list) {
                    for (SysRolePermissionView parent : returnList) {
                        if (parent.getId().equals(view.getParentId())) {
                            view.setChildrenList(new ArrayList<>());
                            parent.getChildrenList().add(view);
                            sonList.add(view);
                        }
                    }
                }
                //孙权限
                for (SysRolePermissionView view : list) {
                    for (SysRolePermissionView parent : sonList) {
                        if (parent.getId().equals(view.getParentId())) {
                            parent.getChildrenList().add(view);
                        }
                    }
                }
            }
        }
        return CommonResponse.makeRsp(CommonCode.SUCCESS, returnList);
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
        if (list != null && list.size() > 0) {
            for (SysPermissionTbl tbl : list) {
                idList.add(tbl.getId());
                childrenList.addAll(sysPermissionTblService.selectByParentId(tbl.getId()));
            }
        }
        if (childrenList != null && childrenList.size() > 0) {
            for (SysPermissionTbl tbl : childrenList) {
                idList.add(tbl.getId());
            }
        }
        idList.add(id);
        //删除权限
        sysPermissionTblService.removeByIds(idList);
        //删除角色拥有的权限
        QueryWrapper<SysRolePermissionTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("permission_id", idList);
        boolean delete = sysRolePermissionTblService.remove(queryWrapper);
        if (delete) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

}

