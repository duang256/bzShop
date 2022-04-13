package com.bjsxt.frontend.portal.controller;

import com.bjsxt.frontend.portal.service.ContentService;
import com.bjsxt.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页内容管理Controller
 */
@RestController
@RequestMapping("/frontend/content")
public class ContentController {

    private Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private ContentService contentService;

    /**
     * 查询首页大广告位
     */
    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        System.out.println("大广告位被调用拉！");
        try{
            return this.contentService.selectFrontendContentByAD();
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return Result.build(500,"error");
    }
}
