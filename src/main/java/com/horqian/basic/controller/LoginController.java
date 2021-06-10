package com.horqian.basic.controller;

import com.horqian.basic.annotation.PassToken;
import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import com.horqian.basic.utils.RandomValidateCodeUtil;
import com.horqian.basic.utils.SendSmsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(tags = "登录管理")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SendSmsUtil sendSmsUtil;

    /**
     * 生成验证码
     */
    @PassToken
    @ApiOperation("生成图片验证码")
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandomCode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 校验验证码
     */
    @PassToken
    @ApiOperation("校验图片验证码")
    @RequestMapping(value = "/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
    public boolean checkVerify(@RequestParam String verifyInput, HttpSession session) {
        try {
            //从session中获取随机数
            String inputStr = verifyInput;
            System.out.println(inputStr);
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            System.out.println(random);
            if (random == null || "".equals(random) || !random.equalsIgnoreCase(inputStr)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成验证码
     */
    @PassToken
    @ApiOperation("生成短信验证码")
    @GetMapping(value = "/getPhoneCode")
    public CommonResult getPhoneCode(@RequestParam String phone, HttpServletRequest request) {
        try {
            sendSmsUtil.sendSMS(phone,request.getSession(),"b52e893a8a834b71ad31b1eb2cbcaa18",null);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.makeRsp(CommonCode.FAIL);
        }
        return CommonResponse.makeRsp(CommonCode.SUCCESS);
    }

    /**
     * 校验验证码
     */
    @PassToken
    @ApiOperation("校验短信验证码")
    @RequestMapping(value = "/verifyPhoneCode", method = RequestMethod.POST, headers = "Accept=application/json")
    public boolean verifyPhoneCode(@RequestParam String phoneCode, HttpSession session) {
        try {
            //从session中获取随机数
            System.out.println(phoneCode);
            String code = session.getAttribute("code").toString();
            System.out.println(code);
            if (code == null || "".equals(code) || !code.equalsIgnoreCase(phoneCode)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
