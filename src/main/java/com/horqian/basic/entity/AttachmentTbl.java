package com.horqian.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AttachmentTbl对象", description="")
public class AttachmentTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;

    @ApiModelProperty(value = "附件说明")
    private String comments;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "附件分类")
    private Integer attachmentType;

    @ApiModelProperty(value = "附件地址")
    private String attachmentPath;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer deleteFlag;


}
