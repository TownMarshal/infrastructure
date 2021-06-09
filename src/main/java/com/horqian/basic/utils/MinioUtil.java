package com.horqian.basic.utils;

import com.horqian.basic.config.MinioConfig;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author macro
 * @create 2021-03-18-10:04
 **/
@Component
public class MinioUtil {
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String upload(MultipartFile file) {
        String objectName = "";
        try {
            String fileName = file.getOriginalFilename();
            objectName = new SimpleDateFormat("yyyy/MM/dd/").format(new Date()) + UUID.randomUUID().toString().replaceAll("-", "")
                    + fileName.substring(fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(minioConfig.getBucketName(), objectName, inputStream, MimeTypeUtils.ALL_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectName;
    }

    /**
     * 文件路径；如：2020/03/04/test.doc
     *
     * @param fileName
     * @return
     */
    public Boolean delete(String fileName) {
        try {
            minioClient.removeObject(minioConfig.getBucketName(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param fileName
     * @param httpServletResponse
     */
    public void download(String fileName, HttpServletResponse httpServletResponse) {
        try {
            InputStream object = minioClient.getObject(minioConfig.getBucketName(), fileName);
            byte buf[] = new byte[1024];
            int length = 0;
            httpServletResponse.reset();
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            httpServletResponse.setContentType("application/octet-stream");
            httpServletResponse.setCharacterEncoding("UTF-8");
            OutputStream outputStream = httpServletResponse.getOutputStream();
            while ((length = object.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName     minio地址
     * @param locationName 本地地址
     */
    public void downloadLocation(String fileName, String locationName) {
        try {
            InputStream object = minioClient.getObject(minioConfig.getBucketName(), fileName);
            byte buf[] = new byte[1024];
            int length = 0;
            FileOutputStream downloadFile = new FileOutputStream(locationName);
            while ((length = object.read(buf)) > 0) {
                downloadFile.write(buf, 0, length);
            }
            downloadFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
