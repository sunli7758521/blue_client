package com.msj.goods.controller;

import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.constants.PicUrlConstants;
import com.msj.goods.common.enums.BusinessType;
import com.msj.goods.common.web.base.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author sun li
 * @Date 2018/11/21 16:20
 * @Description
 */
//@RestController
@Controller
@RequestMapping("/upload")
public class UploadController {

    @PostMapping(value = "/uploadFile")
    @Log(title = "上传图片",businessType = BusinessType.OTHER)
    @ApiOperation(value="上传图片", notes="上传图片")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    @ResponseBody
    public JsonResult uploadFile(HttpServletRequest req){


            String pic = "" ;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        List<MultipartFile> multipartfiles = multipartRequest.getFiles("file");
        String picPath = PicUrlConstants.URL;
        if(multipartfiles.size() > 0){
            for(MultipartFile file : multipartfiles) {
                try {
                     String uuid = UUID.randomUUID().toString();
                     String ff = file.getOriginalFilename();
                     String fileType = ff.substring(ff.lastIndexOf("."));
                     String fileName = uuid+fileType;
                     file.transferTo(new File( picPath + fileName));
                     pic = PicUrlConstants.HTTP_URL + fileName;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(pic + "=====================");
            return JsonResult.success(pic  , JsonResultConstants.SUCCESS);
        }else {
            return null;
        }
    }

}
