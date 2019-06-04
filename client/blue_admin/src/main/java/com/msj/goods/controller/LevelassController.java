package com.msj.goods.controller;

import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.LevelAss;
import com.msj.goods.service.AssessmentStateService;
import com.msj.goods.service.LevelAssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sun li
 * @Date 2019/3/20 14:10
 * @Description
 */

@RestController
@RequestMapping("/Levelass")
@Api(description = "水平考核科目")
public class LevelassController {


    @Autowired
    private LevelAssService levelAssService;

    @Autowired
    private AssessmentStateService assessmentStateService ;

    @PostMapping(value = "/LevelAssController")
    @Log(title = "展示所有的水平考核科目列表")
    @ApiOperation(value="水平考核", notes="水平考核展示数据")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult levelassPage(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){


       /* String a	= ShiroUtils.getUserId();*/
        int deptIds =ShiroUtils.getDeptId();/** 获取到登录人的 部门id  根据部门id 去查找到 相关的 考试科目/
        /*int b=Integer.valueOf(a);*/
       /*  String  deng=  ShiroUtils.getUserId(); *//**我需要获取到 登录人的id*//*
          Integer  userid=Integer.valueOf(deng);*//** 根据获取到登录人的id  进行 转类型*//*
          *//** 当再次 登录的时候 就根据 用户的id 查出 已经考过的*//*
        List<AssessmentState> asst =assessmentStateService.selectAssessmentState(userid);
        List list = new ArrayList();
        *//** 我下边需要根据我的 list 集合 for 循环遍历  把他们存成json 的 数组字符串*//*
        for (int i=0;i<asst.size();i++){
               AssessmentState as=new AssessmentState();
                    as.setUserId(asst.get(i).getUserId() );
                    as.setLevelId(asst.get(i).getLevelId());
                    as.setShenQingFangShi(asst.get(i).getShenQingFangShi());
                    as.setState(asst.get(i).getState());
                    list.add(as);
        }
               JSONArray jsonArray =JSONArray.fromObject(list); *//** 根据 水平考核 的状态 把那些 考过的 信息 存到json 里面*/
        System.out.println(deptIds+"看我 看我 看我  撒啊啊啊啊啊啊啊阿斯达");
       PageInfo<LevelAss> pageInfo = levelAssService.selectLevelAssList(deptIds,pageSize,pageNum);/**这个是查询全部的水平考核科目类表*/
        System.out.println(pageInfo);

        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }











}
