package com.horqian.basic.common;

import lombok.Data;

/**
 * @author macro
 * @create 2020-11-07-14:33
 **/
@Data
public class CommonPageResult extends CommonResult {
    private Long pageNo;
    private Long pageSize;
    private Long totalCount;
}
