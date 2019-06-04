package com.msj.goods.controller;

import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.enums.BusinessType;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.SysUser;
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
 * @Date 2018/11/14 14:30
 * @Description  自由奖扣
 */
@RestController
@RequestMapping("/free")
@Api(description = "管理员自由奖扣")
public class FreeController {

    @Autowired
    private IntegralApprovalService integralApprovalService;

    /**
     *   管理员加分  from 给员工申请
     */
    @PostMapping(value = "/freeIntegral")
    @Log(title = "管理员自由奖扣",businessType = BusinessType.INSERT)
    @ApiOperation(value="管理员自由奖扣", notes="管理员自由奖扣")
    @ApiImplicitParam(name = "addIntegral",value = "json对象",required = true)
    public JsonResult freeIntegral(
            @RequestParam(value = "addIntegral" ,required = false) String addIntegral,
            @RequestParam(value = "delIntegral",required = false) String delIntegral,
            @RequestParam(value = "spRemark",required = false) String spRemark,
            @RequestParam(value = "typeId",required = false) String typeId,
            @RequestParam(value = "from",required = false) String[] from,
            @RequestParam(value = "approvalImg",required = false) String[] approvalImg1,
            @RequestParam(value = "approvalContent",required = false) String approvalContent,
            @RequestParam(value = "approvalTitle",required = false) String approvalTitle,
            @RequestParam(value = "dateTime",required = false) String dateTime){

        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        boolean result =  integralApprovalService.freeIntegral(user,addIntegral,delIntegral,
                spRemark,typeId,from,approvalImg1,approvalContent,approvalTitle,dateTime);
        return result ? JsonResult.success(result, JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }

    /**
     *  from 申请人  to 抄送人    apps 审批人
     * */
    @PostMapping(value = "/freeIntegralApprover")
    @Log(title = "自由奖扣申请" , businessType = BusinessType.INSERT)
    @ApiOperation(value="自由奖扣申请", notes="自由奖扣申请")
    @ApiImplicitParam(name = "addIntegral",value = "json对象",required = true)
    public JsonResult freeIntegralApprover(
            @RequestParam(value = "addIntegral" ,required = false) String addIntegral,
            @RequestParam(value = "delIntegral",required = false) String delIntegral,
            @RequestParam(value = "spRemark",required = false) String spRemark,
            @RequestParam(value = "typeId",required = false) String typeId,
            @RequestParam(value = "from",required = false) String[] from,
            @RequestParam(value = "to",required = false) String[] to,
            @RequestParam(value = "approvalImg",required = false) String[] approvalImg1,
            @RequestParam(value = "approvalContent",required = false) String approvalContent,
            @RequestParam(value = "approvalTitle",required = false) String approvalTitle,
            @RequestParam(value = "approvalId",required = false) String approvalId,
            @RequestParam(value = "apps",required = false) String [] apps){

        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        boolean result =  integralApprovalService.freeIntegralApprover(user,addIntegral,delIntegral,
                spRemark,typeId,from,to,approvalImg1,approvalContent,approvalTitle,approvalId,apps);
        return result ? JsonResult.success(result,JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }

    /**
     *   查询 管理奖扣 还剩都少管理奖扣分数
     *//*
    @PostMapping(value = "/managementIntegral")
    @Log(title = "查询领导可用管理奖扣",businessType = BusinessType.INSERT)
    @ApiOperation(value="查询领导可用管理奖扣", notes="查询领导可用管理奖扣")
    @ApiImplicitParam(name = "addIntegral",value = "json对象",required = true)
    public JsonResult leaderAvailableIntegral(){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        return JsonResult.success(user.getManAward(), JsonResultConstants.ADD_SUCCESS );
    }*/

}
