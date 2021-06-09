package com.horqian.basic.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;

import com.horqian.basic.entity.SysRoleTbl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author macro
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserRoleView对象", description="VIEW")
public class SysUserRoleView extends SysRoleTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long userId;


}
