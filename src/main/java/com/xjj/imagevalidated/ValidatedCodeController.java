package com.xjj.imagevalidated;


import com.xjj.utils.ImgVerifyCodeUtils;
import com.xjj.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by jie on 2018/9/11.
 */
@RestController
public class ValidatedCodeController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
        /*
         //生成带有字母和数字的图片验证码
           ImgVerifyCodeUtils imgVerifyCodeUtils = new ImgVerifyCodeUtils();
           imgVerifyCodeUtils.createImageCode(request,response);*/

        //生成数字图片验证码
          ImageCode imageCode = createImageCode(request);
          sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
          ImageIO.write(imageCode.getBufferedImage(),"JPEG",response.getOutputStream());

    }


    private ImageCode createImageCode(HttpServletRequest request) {

        int width = 67;
        int height = 23;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));

        g.fillRect(0,0,width,height);
        g.setFont(new Font("Time New Roman",Font.ITALIC,20));
        g.setColor(getRandColor(160,200));

        for(int i=0;i<155;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x,y,x+x1,y+y1);
        }
        String sRand = "";
        for(int i=0;i<4;i++){
            String rand = String.valueOf(random.nextInt(10));
            sRand+=rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(rand,13*i+6,16);
        }

        g.dispose();

        return new ImageCode(image,sRand,60);
    }

    //生成条纹背景
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if(fc>255){
            fc=255;
        }
        if(bc>255){
            bc=255;
        }
        int r = fc+random.nextInt(bc-fc);
        int g = fc+random.nextInt(bc-fc);
        int b = fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

}
