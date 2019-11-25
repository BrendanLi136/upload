package com.demo.qiniu.controller;

import com.demo.qiniu.util.UploadUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Brendan Li
 * @description 上传的Controller
 * @Date: 2019/11/25/15:34
 */
@Controller
public class UploadController {

    private static final Logger logger= LogManager.getLogger(UploadController.class);

    @Value("${qiniu.url}")
    private String url;

    @Autowired
    private UploadUtils up;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String saveImg(@RequestParam("file") MultipartFile multipartFile){

        String upload = null;
        try {
            upload = up.upload(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("fileName : "+upload);
        //将数据存在数据库中
        return url+upload;
    }

}
