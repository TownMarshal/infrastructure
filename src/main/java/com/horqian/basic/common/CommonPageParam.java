package com.horqian.basic.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author macro
 * @create 2020-11-05-14:42
 **/
@Data
public class CommonPageParam<T> {
    /**
     * 当前页码数
     */
    @ApiModelProperty(value = "当前页数", example = "1")
    private Integer pageNo;
    /**
     * 当前设置的条数
     */
    @ApiModelProperty(value = "当前设置条数", example = "20")
    private Integer pageSize;

    private T data;

    private String order;

    @ApiModelProperty(hidden = true)
    public IPage getPage() {
        IPage page = null;
        if (pageNo != null && pageSize != null) {
            page = new Page<>(pageNo, pageSize);
        } else {
            page = new Page<>();
        }
        return page;
    }
}
