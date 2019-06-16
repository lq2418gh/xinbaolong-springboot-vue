package com.bit.sc.module.other.controller;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.gimpy.RippleGimpyRenderer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
import cn.apiclub.captcha.text.renderer.WordRenderer;
import com.bit.base.exception.BusinessException;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.controller.ApplyController;
import com.bit.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;

/**
 * 图片验证码生成
 */
@Controller
@RequestMapping("/checkCode/")
public class PictureCheckCodeController {

    private static final Logger logger = LoggerFactory.getLogger(PictureCheckCodeController.class);

    private static int captchaW = 100;  //图片验证码的宽
    private static int captchaH = 40;   //图片验证码的高
    private static int codeCount = 4;   //值得位数
    private static char[] codeNumber = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Autowired
    private CacheUtil cacheUtil;

    @RequestMapping("getCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //创建验证码模型，指定图片的宽和高
        Captcha.Builder captcha = new Captcha.Builder(captchaW, captchaH);
        //设置字体的颜色和大小，若list内放置多个颜色或字体，则每次生成随机使用其中一个
        List<Font> fonts = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        fonts.add(new Font("Arial", Font.BOLD, 32));
        colors.add(Color.BLUE);
        WordRenderer wr = new DefaultWordRenderer(colors, fonts);
        //将数字放入到图片上
        captcha.addText(new DefaultTextProducer(codeCount, codeNumber), wr);
        //为验证码值设置边框效果---无
        captcha.gimp(new RippleGimpyRenderer());
        //图片验证码生成
        captcha.build();
        Captcha captchas = captcha.build();
        //将验证码以<key,value>形式缓存到redis
        cacheUtil.set(Const.REDIS_KEY_CAPTCHA + captchas.getAnswer(), captchas.getAnswer(), 60);
        OutputStream out = response.getOutputStream();
        try {
            ImageIO.write(captchas.getImage(), "jpg", out);
        } catch (Exception e) {
            throw new BusinessException("图片验证码生成出错");
        }finally {
            out.close();
        }

    }
}
