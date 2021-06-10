package com.horqian.basic.controller;


import com.horqian.basic.annotation.PassToken;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.AttachmentTbl;
import com.horqian.basic.service.AttachmentTblService;
import com.horqian.basic.utils.ObsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2021-06-09
 */
@Api(tags = "附件管理")
@RestController
@RequestMapping("/attachment")
public class AttachmentTblController {

    @Autowired
    private ObsUtil obsUtil;
    @Autowired
    private AttachmentTblService attachmentTblService;

    @PassToken
    @ApiOperation("添加附件")
    @PostMapping(value = "add")
    public CommonResult add(MultipartFile file, AttachmentTbl attachmentTbl) throws IOException {
        String upload = obsUtil.upload(file);
        if (!"".equals(upload)) {
            if(attachmentTbl.getAttachmentName() != null && !attachmentTbl.getAttachmentName().equals("")){
            }else {
                attachmentTbl.setAttachmentName(file.getOriginalFilename());
            }
            attachmentTbl.setAttachmentPath(upload);
            boolean save = attachmentTblService.save(attachmentTbl);
            if (save) {
                return CommonResponse.makeRsp(CommonCode.SUCCESS, attachmentTbl.getId().toString());
            }
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }

}

