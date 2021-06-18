package com.horqian.basic.utils;

import com.google.api.client.util.IOUtils;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.ObsObject;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class ObsUtil {
    private static final String endPoint = "https://obs.cn-north-4.myhuaweicloud.com";
    private static final String ak = "E36RAKKAVTC1BSDOM3RQ";
    private static final String sk = "eKMChmxZwyRVZm1h2dNgA9AxttLLGCAOvGZgqP3m";
    private static final String bucketname = "horqianbasic";
    private static final String myhuaweicloud = "https://horqianbasic.obs.cn-north-4.myhuaweicloud.com/";

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        String objectName = "";
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        try {
            String fileName = file.getOriginalFilename();
            objectName = new SimpleDateFormat("yyyy/MM/dd/").format(new Date()) + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            obsClient.putObject(bucketname, objectName, inputStream);
            obsClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectName;
    }


    //    获取临时访问路径
    public String getTemporaryUrl(String objectName) {
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        // URL有效期，3600秒
        long expireSeconds = 3600L;
        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.GET, expireSeconds);
        request.setBucketName(bucketname);
        request.setObjectKey(objectName);
        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);
        return response.getSignedUrl();
    }


    /**
     * @param fileName     minio地址
     * @param locationName 本地地址
     */
    public void downloadLocation(String fileName, String locationName) {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        try {
            ObsObject obsObject = obsClient.getObject(bucketname, "2021/06/10/af65a64140a0427b98a10df7b4441f73.png");
            InputStream input = obsObject.getObjectContent();
            byte buf[] = new byte[1024];
            int length = 0;
            FileOutputStream downloadFile = new FileOutputStream(locationName);
            while ((length = input.read(buf)) > 0) {
                downloadFile.write(buf, 0, length);
            }
            downloadFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public  void download(String objectName){
//         try {
//            // 创建ObsClient实例
//            String endPoint = "https://obs.cn-north-4.myhuaweicloud.com";
//            String ak = "E36RAKKAVTC1BSDOM3RQ";
//            String sk = "eKMChmxZwyRVZm1h2dNgA9AxttLLGCAOvGZgqP3m";
//            String bucketname = "horqianbasic";
//
//            ObsClient obsClient = new ObsClient(ak, sk, endPoint);
//            ObsObject obsObject = obsClient.getObject(bucketname, objectName);
//            InputStream inputStream = obsObject.getObjectContent();
//            // 缓冲文件输出流
//            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
////            // 为防止 文件名出现乱码
////            final String userAgent = request.getHeader("USER-AGENT");
////            // IE浏览器
////            if (StringUtils.contains(userAgent, "MSIE")) {
////                objectName = URLEncoder.encode(objectName, "UTF-8");
////            } else {
////                // google,火狐浏览器
////                if (StringUtils.contains(userAgent, "Mozilla")) {
////                    objectName = new String(objectName.getBytes(), "ISO8859-1");
////                } else {
////                    // 其他浏览器
////                    objectName = URLEncoder.encode(objectName, "UTF-8");
////                }
////            }
//            response.reset();
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(attachmentName, "UTF-8"));
//            response.setContentType("application/octet-stream");
//            response.setCharacterEncoding("UTF-8");
//            IOUtils.copy(inputStream, outputStream);
//            outputStream.flush();
//            outputStream.close();
//            inputStream.close();
//            return null;
//        } catch (IOException | ObsException e) {
//            return CommonResponse.makeRsp(CommonCode.FAIL, "文件下载失败！");
//        }
//    }
}
