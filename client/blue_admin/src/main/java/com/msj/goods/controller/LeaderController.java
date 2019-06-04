package com.msj.goods.controller;

import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.enums.BusinessType;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.ManageUser;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.IntegralApprovalService;
import com.msj.goods.service.SysUserDeptPostService;
import com.msj.goods.service.SysUserService;
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
 * @Date 2018/11/27 15:26
 * @Description 领导表扬
 */
@RestController
@RequestMapping("/leader")
@Api(description = "领导表扬相当于积分支票")
public class LeaderController {

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysUserDeptPostService sysUserDeptPostService;

    @Autowired
    private SysUserService sysUserService;

    /**
     *   领导积分支票加分  from 给员工申请
     */
    @PostMapping(value = "/leaderIntegral")
    @Log(title = "领导表扬加分",businessType = BusinessType.INSERT)
    @ApiOperation(value="领导表扬加分", notes="领导表扬加分")
    @ApiImplicitParam(name = "addIntegral",value = "json对象",required = true)
    public JsonResult leaderIntegral(
            @RequestParam(value = "approvalContent",required = false) String approvalContent,
            @RequestParam(value = "approvalTitle",required = false) String approvalTitle,
            @RequestParam(value = "typeId",required = false) String typeId,
            @RequestParam(value = "approvalImg",required = false) String[] approvalImg1,
            @RequestParam(value = "addIntegral" ,required = false) String addIntegral,
            @RequestParam(value = "spRemark",required = false) String spRemark,
            @RequestParam(value = "from",required = false) String[] from,
            @RequestParam(value = "dateTime",required = false) String dateTime){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        boolean result =  integralApprovalService.leaderIntegral(user,approvalContent,
                approvalTitle,typeId,approvalImg1,addIntegral,spRemark,from,dateTime);
        return result ? JsonResult.success(result, JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }

    /**
     *   查询领导可用表扬积分  积分支票
     */
    @PostMapping(value = "/leaderAvailableIntegral")
    @Log(title = "查询领导可用表扬积分",businessType = BusinessType.INSERT)
    @ApiOperation(value="查询领导可用表扬积分", notes="查询领导可用表扬积分")
    @ApiImplicitParam(name = "addIntegral",value = "json对象",required = true)
    public JsonResult leaderAvailableIntegral(){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        SysUser u =    sysUserService.selectById(user.getUserId());

        return JsonResult.success(u.getBiaoIntegral(), JsonResultConstants.ADD_SUCCESS );
    }

    /**
     *     管理奖扣分值
     */
    @PostMapping(value = "/leaderManageIntegral")
    @Log(title = "查询领导可用表扬积分",businessType = BusinessType.INSERT)
    @ApiOperation(value="查询领导可用表扬积分", notes="查询领导可用表扬积分")
    @ApiImplicitParam(name = "addIntegral",value = "json对象",required = true)
    public JsonResult manageIntegral(){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        SysUser u =    sysUserService.selectById(user.getUserId());
        return JsonResult.success(u.getManAward(), JsonResultConstants.ADD_SUCCESS );
    }


    /**
     *   查询每个领导所管理的部门
     */
    @PostMapping(value = "/selectLeaderdepts")
    @Log(title = "查询每个领导所管理的部门",businessType = BusinessType.OTHER)
    @ApiOperation(value="查询每个领导所管理的部门", notes="查询每个领导所管理的部门")
    @ApiImplicitParam(name = "selectLeaderdepts",value = "json对象",required = true)
    public JsonResult selectLeaderdepts(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum,@RequestParam("search") String search){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<ManageUser> pageInfo = sysUserDeptPostService.selectLeaderdepts(search,user,pageSize,pageNum);
        return JsonResult.success(pageInfo, JsonResultConstants.ADD_SUCCESS );
    }

}
