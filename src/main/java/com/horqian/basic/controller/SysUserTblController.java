package com.horqian.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonPageParam;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.service.SysUserTblService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2021-06-09
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserTblController {

    @Autowired
    private SysUserTblService sysUserTblService;

    @ApiOperation("查询用户")
    @PostMapping("select")
    public CommonResult select(@RequestBody CommonPageParam<SysUserTbl> commonPageParam) {
        QueryWrapper<SysUserTbl> queryWrapper = new QueryWrapper<>(commonPageParam.getData());
        queryWrapper.orderByDesc("update_time");
        IPage<SysUserTbl> page = sysUserTblService.page(commonPageParam.getPage(), queryWrapper);
        return CommonResponse.makePageRsp(CommonCode.SUCCESS, page);
    }

    @ApiOperation("新建用户")
    @PostMapping("/add")
    public CommonResult add(@RequestBody SysUserTbl sysUserTbl) {

        boolean save = sysUserTblService.save(sysUserTbl);
        if (save) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public CommonResult update(@RequestBody SysUserTbl sysUserTbl) {
        boolean update = sysUserTblService.updateById(sysUserTbl);
        if (update) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete")
    public CommonResult delete(Long id) {
        boolean delete = sysUserTblService.removeById(id);
        if (delete) {
            return CommonResponse.makeRsp(CommonCode.SUCCESS);
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }
}

