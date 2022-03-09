package com.horqian.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horqian.basic.annotation.SysLog;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonPageParam;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.service.SysUserTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author macro
 * @since 2021-06-09
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/sysUser")
public class SysUserTblController {

    @Autowired
    private SysUserTblService sysUserTblService;


    // 不好用是因为没添加注解@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//    @PreAuthorize("hasRole('admin')")
    @ApiOperation("查询用户")
    @PostMapping("select")
    public CommonResult select(@RequestBody CommonPageParam<SysUserTbl> commonPageParam) {
        QueryWrapper<SysUserTbl> queryWrapper = new QueryWrapper<>(commonPageParam.getData());
        queryWrapper.orderByDesc("create_time");
        IPage<SysUserTbl> page = sysUserTblService.page(commonPageParam.getPage(), queryWrapper);
        return CommonResponse.makePageRsp(CommonCode.SUCCESS, page);
    }

    @ApiOperation("新建用户")
    @PostMapping("/add")
    @SysLog(type = "新建", name = "系统用户")
    public CommonResult add(@RequestBody SysUserTbl sysUserTbl) {
        QueryWrapper<SysUserTbl> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", sysUserTbl.getUserName());
        List<SysUserTbl> list = sysUserTblService.list(queryWrapper);
        if (list != null && list.size() > 0) {
            return CommonResponse.makeRsp(CommonCode.FAIL, "用户名已存在！");
        }
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        sysUserTbl.setPassword(bcp.encode(sysUserTbl.getPassword()));
        boolean save = sysUserTblService.save(sysUserTbl);
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("修改用户")
    @PostMapping("/update")
    public CommonResult update(@RequestBody SysUserTbl sysUserTbl) {
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        if (sysUserTbl.getPassword() != null) {
            sysUserTbl.setPassword(bcp.encode(sysUserTbl.getPassword()));
        }
        boolean update = sysUserTblService.updateById(sysUserTbl);
        if (update) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @Secured("ROLE_admin")
    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam Long id) {
        boolean delete = sysUserTblService.removeById(id);
        if (delete) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }
}

