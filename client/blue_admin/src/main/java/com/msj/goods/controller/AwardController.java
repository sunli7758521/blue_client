package com.msj.goods.controller;

import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.IntegralApproval;
import com.msj.goods.service.IntegralApprovalService;
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
 * @Date 2019/4/26 10:23
 * @Description
 */


@RestController
@RequestMapping("/Award")
@Api(description = "管理奖扣日志")
public class AwardController {


    @Autowired
    private IntegralApprovalService integralApprovalService;


    @PostMapping(value = "/AwardController")
    @Log(title = "展示 管理者 显示的管理奖扣日志")
    @ApiOperation(value="管理者奖扣日志", notes="显示管理者展示的奖扣日志")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult Award(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        /* String a	= ShiroUtils.getUserId();*/
        /*  int deptIds = ShiroUtils.getDeptId();*//** 获取到登录人的 部门id  根据部门id 去查找到 相关的 考试科目/
         /*int b=Integer.valueOf(a);*/
        String  shenPiRenIds=  ShiroUtils.getUserId(); /**我需要获取到 登录人的id*/
        Integer  tiJiaoId=Integer.valueOf(shenPiRenIds);/** 根据获取到登录人的id  进行 转类型*/
        /**可能还需要根据登录人是不是管理者的身份进行查看验证*/
        /**根据 userid 查询出来  管理者 所 管理的部门*/
        PageInfo<IntegralApproval> pageInfo =integralApprovalService.selectgly(tiJiaoId,pageSize,pageNum);
        /** 当再次 登录的时候 就根据 用户的id 查出 已经考过的*/
     /*   PageInfo<AssessmentState> pageInfo =assessmentStateService.selectAssessmentState(userid);*/
        return JsonResult.success(pageInfo, JsonResultConstants.SUCCESS);
    }

}
