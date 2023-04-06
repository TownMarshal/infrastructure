package com.horqian.basic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonPageParam;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.SysLogTbl;
import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.service.SysLogTblService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2021-06-22
 */

@Api(tags = "日志管理")
@RestController
@RequestMapping("/sysLog")
public class SysLogTblController {
    @Autowired
    private SysLogTblService sysLogTblService;
    @ApiOperation("查询日志信息")
    @PostMapping("select")
    public CommonResult select(@RequestBody CommonPageParam<SysLogTbl>commonPageParam){
        QueryWrapper<SysLogTbl> sysLogTblQueryWrapper = new QueryWrapper<>(commonPageParam.getData());
        IPage<SysUserTbl> page = sysLogTblService.page(commonPageParam.getPage(), sysLogTblQueryWrapper);
        return CommonResponse.makePageRsp(CommonCode.SUCCESS, page);
    }
}

