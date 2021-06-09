package com.horqian.basic.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.BizLicenseOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.BizLicenseOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;

;

/**
 * 腾讯图像识别
 *
 * @author macro
 */
public class Distinguish {
    private static final String secretId = "AKIDOceU3OA2Jn2I5nDWv60B83i6JEuJ7ywd";
    private static final String secretKey = "ay70cFcw0nOkrhaPzMclzxEF2avVqwlf";

    /**
     * 身份证识别
     *
     * @param imageBase64
     * @return
     */
    public static String getIdMessage(String imageBase64) {
        try {
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            IDCardOCRRequest req = new IDCardOCRRequest();
            req.setCardSide("FRONT");
            req.setImageBase64(imageBase64);
            IDCardOCRResponse resp = client.IDCardOCR(req);
            System.out.println(IDCardOCRResponse.toJsonString(resp));
            return IDCardOCRResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * 企业执照识别
     *
     * @param imageBase64
     * @return
     */
    public static String getEnterpriseLicenseOCR(String imageBase64) {
        try {
            Credential cred = new Credential(secretId, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);
            BizLicenseOCRRequest req = new BizLicenseOCRRequest();
            req.setImageBase64(imageBase64);
            BizLicenseOCRResponse resp = client.BizLicenseOCR(req);
            return IDCardOCRResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return null;
    }


}