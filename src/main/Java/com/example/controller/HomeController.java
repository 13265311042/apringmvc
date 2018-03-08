package com.example.controller;
import com.example.pojo.Manager;
import com.example.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/***
 *@author  hyt
 */
    @Controller("HomeController")
    @RequestMapping("/home/")
    public class HomeController extends BaseController{
        private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
        @RequestMapping("index")
        public  String index(){
            //输出日志文件
            logger.info("the first jsp pages");
            System.out.println("8888");
            //返回一个index.jsp这个视图
            return "index";
        }

    @Resource
    private ManagerService managerSerice;

    @RequestMapping("name")
    public  String getNameById(){
        String id = request.getParameter("id");
        //查出数据库中的总条数
        Manager x =  managerSerice.getNameById(Long.valueOf(id));
        logger.info(x.getId()+x.getName()+x.getSex());
        return "name";
    }
}
