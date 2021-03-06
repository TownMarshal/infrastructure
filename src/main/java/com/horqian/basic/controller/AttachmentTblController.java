package com.horqian.basic.controller;


import com.google.api.client.util.IOUtils;
import com.horqian.basic.annotation.PassToken;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.entity.AttachmentTbl;
import com.horqian.basic.service.AttachmentTblService;
import com.horqian.basic.utils.ObsUtil;
import com.obs.services.exception.ObsException;
import com.obs.services.model.ObsObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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
        System.out.println(upload);
        if (!"".equals(upload)) {
            if (attachmentTbl.getAttachmentName() != null && !attachmentTbl.getAttachmentName().equals("")) {
            } else {
                attachmentTbl.setAttachmentName(file.getOriginalFilename());
            }
            attachmentTbl.setAttachmentPath(upload);
            boolean save = attachmentTblService.save(attachmentTbl);
            if (save) {
                Map<String, String> map = new HashMap<>();
                String temporaryUrl = obsUtil.getTemporaryUrl(upload);
                map.put("id", attachmentTbl.getId().toString());
                map.put("temporaryUrl", temporaryUrl);
                return CommonResponse.makeRsp(CommonCode.SUCCESS, map);
            }
        }
        return CommonResponse.makeRsp(CommonCode.FAIL);
    }


    @PassToken
    @ApiOperation("下载附件")
    @GetMapping(value = "download")

    public CommonResult fileDownload(HttpServletResponse response, @RequestParam Long id) {
        AttachmentTbl attachmentTbl = attachmentTblService.getById(id);
        String objectName = attachmentTbl.getAttachmentPath();
        String attachmentName = attachmentTbl.getAttachmentName();
        try {
            ObsObject obsObject = obsUtil.getObsObject(objectName);
            InputStream inputStream = obsObject.getObjectContent();
            // 缓冲文件输出流
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(attachmentName, "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return null;
        } catch (IOException | ObsException e) {
            return CommonResponse.makeRsp(CommonCode.FAIL, "文件下载失败！");
        }
    }


}

