package com.msj.goods.controller;

import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.AssessmentState;
import com.msj.goods.service.AssessmentStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sun li
 * @Date 2019/4/23 17:41
 * @Description
 */
@RestController
@RequestMapping("/AssessmentState")
@Api(description = "水平考核以及考核过的科目")
public class AssessmentController {


    @Autowired
    private AssessmentStateService assessmentStateService ;


    @PostMapping(value = "/AssessmentStateController")
    @Log(title = "展示所有的水平考核科目列表已考完的")
    @ApiOperation(value="水平考核完的科目", notes="水平考核展示数据已考完的")
   /* @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)*/
    public JsonResult AssessmentStatePage(){
        /* String a	= ShiroUtils.getUserId();*/
      /*  int deptIds = ShiroUtils.getDeptId();*//** 获取到登录人的 部门id  根据部门id 去查找到 相关的 考试科目/
         /*int b=Integer.valueOf(a);*/
         String  deng=  ShiroUtils.getUserId(); /**我需要获取到 登录人的id*/
          Integer  userid=Integer.valueOf(deng);/** 根据获取到登录人的id  进行 转类型*/
         /** 当再次 登录的时候 就根据 用户的id 查出 已经考过的*/
        PageInfo<AssessmentState> pageInfo =assessmentStateService.selectAssessmentState(userid);

        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

}
