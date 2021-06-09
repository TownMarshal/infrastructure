package com.horqian.basic.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author macro
 * @create 2020-11-05-14:21
 **/
public class CommonResponse {
    public static <T> CommonResult<T> makeRsp(CommonCode commonCode) {
        CommonResult<T> commonResult = new CommonResult<T>();
        commonResult.setMsg(commonCode.getMessage());
        commonResult.setCode(commonCode.getCode());
        return commonResult;
    }

    public static <T> CommonResult<T> makeRsp(CommonCode commonCode, T data) {
        CommonResult<T> commonResult = new CommonResult<T>();
        commonResult.setMsg(commonCode.getMessage());
        commonResult.setCode(commonCode.getCode());
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonPageResult makePageRsp(CommonCode commonCode, IPage iPage) {
        CommonPageResult commonResult = new CommonPageResult();
        commonResult.setMsg(commonCode.getMessage());
        commonResult.setCode(commonCode.getCode());
        commonResult.setData(iPage == null ? null : iPage.getRecords());
        commonResult.setPageNo(iPage == null ? null : iPage.getCurrent());
        commonResult.setPageSize(iPage == null ? null : iPage.getSize());
        commonResult.setTotalCount(iPage == null ? null : iPage.getTotal());
        return commonResult;
    }
}
