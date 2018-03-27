package com.example.controller;
import com.example.pojo.Manager;
import com.example.service.ManagerService;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import untils.BufferedImageLuminanceSource;
import untils.MatrixToImageWriter;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/***
 *@author  hyt
 */
    @Controller("HomeController")
    @RequestMapping("/home/")
    public class HomeController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Resource
    private ManagerService managerSerice;

        @RequestMapping("index")
        public  String index(){
            //输出日志文件
            logger.info("the first jsp pages");
            System.out.println("8888");
            //返回一个index.jsp这个视图
            return "index";
        }

    @RequestMapping(value = "name",produces="text/html;charset=UTF-8")
    public  ModelAndView  getNameById(){
        String id = request.getParameter("id");
        //查出数据库中的总条数
        Manager x =  managerSerice.getNameById(Long.valueOf(id));
        //她只是我的妹妹
        return  new ModelAndView("name","message",x);
    }


    /**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @param width  宽
     * @param height 高
     * @param format 图片格式
     * @return 生成的二维码图片路径
     * @throws Exception
     */
    @RequestMapping(value = "generateQRCode",produces="text/html;charset=UTF-8")
    private static String generateQRCode(String text, int width, int height, String format) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        String pathName = "F:/new.png";
        File outputFile = new File(pathName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        return pathName;
    }


    /**
     * 解析指定路径下的二维码图片
     *
     * @param filePath 二维码图片路径
     * @return
     */
    @RequestMapping(value = "parseQRCode",produces="text/html;charset=UTF-8")
    private static String parseQRCode(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);

            System.out.println("result 为：" + result.toString());
            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
            System.out.println("resultText 为：" + result.getText());
            //设置返回值
            content = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    /**
     * 随机生成指定长度的验证码
     *
     * @param length 验证码长度
     * @return 生成的验证码
     */
    private static String generateNumCode(int length) {
        String val = "";
        String charStr = "char";
        String numStr = "num";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
            //输出字母还是数字
            if (charStr.equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if (numStr.equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    /**
     * 利用Google的ZXing工具包，生成和解析二维码图片
     * <p>
     * Created by Eric on 2017/2/15.
     */
    @RequestMapping(value = "code",produces="text/html;charset=UTF-8")
    private  void code() {
        //String text = generateNumCode(12);  //随机生成的12位验证码
        String text ="http://testimages.morsearch.com/images/201711191708203734.jpg";
        System.out.println("随机生成的12位验证码为： " + text);
        int width = 300;    //二维码图片的宽
        int height = 300;   //二维码图片的高
        String format = "png";  //二维码图片的格式
        try {
            //生成二维码图片，并返回图片路径
            String pathName = generateQRCode(text, width, height, format);
            System.out.println("生成二维码的图片路径： " + pathName);

            String content = parseQRCode(pathName);
            System.out.println("解析出二维码的图片的内容为： " + content);
        } catch (Exception e) {
            e.printStackTrace();




            //这都检查不出来
        }
    }
}
