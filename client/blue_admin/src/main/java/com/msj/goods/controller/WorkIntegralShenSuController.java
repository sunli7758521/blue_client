package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.enums.BusinessType;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.IntegralApproval;
import com.msj.goods.entity.SysConfig;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.IntegralApprovalService;
import com.msj.goods.service.SysConfigService;
import com.msj.goods.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sun li
 * @Date 2018/11/10 11:42
 * @Description 积分申诉
 */
@RestController
@RequestMapping("/complaint")
@Api(description = "积分申诉")
public class WorkIntegralShenSuController {

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     *  积分申诉    time 1.通过  2.审批不通过，和撤销审批的
     */
    @PostMapping(value = "/selectComplaintList")
    @Log(title = "积分申诉")
    @ApiOperation(value="积分申诉列表", notes="积分申诉列表")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectIntegralList(@RequestParam("pageSize") String pageSize,
                                         @RequestParam("pageNum") String pageNum,
                                         @RequestParam("time") Integer time){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectIntegralListYes(user,pageSize,pageNum,time);

        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    /**
     *  积分申诉  审批通过 、撤销审批、审批不通过详情页
     */
    @GetMapping(value = "/selectComplaintDetails/{approvalId}")
    @Log(title = "积分申诉详情页")
    @ApiOperation(value="积分申诉详情页列表", notes="积分申诉详情页列表")
    @ApiImplicitParam(name = "approvalId",value = "json对象",required = true)
    public JsonResult selectComplaintDetails(@PathVariable( value = "approvalId" ,required = true) String approvalId ){

      IntegralApproval integralApproval = integralApprovalService.selectComplaintDetails(approvalId);
        return JsonResult.success(integralApproval , JsonResultConstants.SUCCESS);
    }
    /**
     *  积分申诉  查询申诉人
     */
    @GetMapping(value = "/selectComplaintPel")
    @Log(title = "积分申诉人查询")
    @ApiOperation(value="积分申诉人查询", notes="积分申诉人查询")
    @ApiImplicitParam(name = "approvalId",value = "json对象",required = true)
    public JsonResult selectComplaintPel(){
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.eq("integral_complainant","1");
        SysUser user =   sysUserService.selectOne(ew);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("user",user);
        return JsonResult.success(maps , JsonResultConstants.SUCCESS);
    }


    /**
     *  积分申诉  添加提交一条审批不通过 或者 撤销审批
     */
    @PostMapping(value = "/addComplaint")
    @Log(title = "添加积分申诉", businessType = BusinessType.INSERT)
    @ApiOperation(value="添加积分申诉", notes="添加积分申诉")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult addComplaint(@RequestParam(value = "approvalId",required = false)   String approvalId,
                              @RequestParam(value = "appReason",required = false) String appReason,
                              @RequestParam(value = "appPelId",required = false) String appPelId,
                              @RequestParam(value = "pic",required = false) String[] pic,
                             @RequestParam(value = "to" ,required = false) String[] to){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }

        boolean result = integralApprovalService.addComplaint(approvalId,appReason, appPelId,pic,to,user);
        return result ? JsonResult.success(result,JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }

    /** 积分申诉拒绝理由 */

    @GetMapping(value = "/groundsRefusal")
    @Log(title = "积分申诉理由查询")
    @ApiOperation(value="积分申诉理由查询", notes="积分申诉理由查询")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult groundsRefusal(){
            EntityWrapper<SysConfig> ew = new  EntityWrapper<SysConfig>();
                            ew.eq("config_type", UserConstants.CONFIG_TYPE);
            List<SysConfig> configList = sysConfigService.selectList(ew);

        return JsonResult.success(configList , JsonResultConstants.SUCCESS);
    }



}
