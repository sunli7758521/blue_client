package com.msj.goods.controller;

import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.service.IntegralApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sun li
 * @Date 2018/11/19 11:18
 * @Description 查询工作台抄送我列表
 */
@RestController
@RequestMapping("/copy")
@Api(description = "工作台查询工作台抄送我")
public class WorkbenchChaoSongController {

    @Autowired
    private IntegralApprovalService integralApprovalService;

   /* @PostMapping(value = "/list")
    @ApiOperation(value="抄送我列表", notes="抄送我列表")
    @ApiImplicitParam(name = "pageSize , pageNum",value = "json对象",required = true)
    public JsonResult selectCopyList(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectCopyList(user,pageSize,pageNum);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }*/

    @GetMapping(value = "/selectCopyDetail/{approvalId}")
    @Log(title = "抄送我的详情页")
    @ApiOperation(value="抄送我的详情页", notes="抄送我的详情页")
    @ApiImplicitParam(name = "approvalId",value = "json对象",required = true)
    public JsonResult selectIntegralGoodsDetail(@PathVariable("approvalId") String approvalId){
        return JsonResult.success(integralApprovalService.selectById(approvalId) , JsonResultConstants.SUCCESS);
    }
}
