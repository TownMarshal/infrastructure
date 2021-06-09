package com.horqian.basic.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
 * @author macro
 * @create 2020-11-09-14:08
 **/
public class WxUtil {
    public static final String appid = "wxc186f3edefd9ee6a";
    public static final String secret = "9867850f81cc3c0ed583a67b4dcdaa35";
    public static final String authApi = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String AES = "AES";
    public static final String AES_CBC_PADDING = "AES/CBC/PKCS7Padding";
    public static final String getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxe22654251585b3d7&secret=0050c8a93b486434291553f6842e5442";

    public static JSONObject wxDecrypt(String encrypted, String code, String iv) {
        String result = null;
        final JSONObject loginInfo = getLoginInfo(code);
        String session_key = (String) loginInfo.get("session_key");
        byte[] encrypted64 = Base64.decodeBase64(encrypted);
        if (null != session_key) {
            byte[] key64 = Base64.decodeBase64(session_key);
            byte[] iv64 = Base64.decodeBase64(iv);
            try {
                init();
                result = new String(decrypt(encrypted64, key64, generateIV(iv64)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = JSON.parseObject(result);
            return jsonObject;
        }
        return null;
    }


    public static String getAccessToken() {
        String url = getAccessToken;
        String result = HttpUtil.createGet(url)
                .execute()
                .charset("utf-8")
                .body();
        return result;
    }

    /**
     * * 初始化密钥
     */
    private static void init() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator.getInstance(AES).init(128);
    }

    /**
     * * 生成iv
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        // iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        // Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(AES);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * * 生成解密
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv)
            throws Exception {
        Key key = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
        // 设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(encryptedData);
    }

    /**
     * 根据code获取
     *
     * @param code
     * @return
     */
    public static JSONObject getLoginInfo(String code) {
        String url = authApi + "?" +
                "appid=" + appid +
                "&secret=" + secret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        String result = HttpUtil.createGet(url)
                .execute()
                .charset("utf-8")
                .body();
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject;
    }
}
