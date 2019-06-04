package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.IntegralApproval;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.SysUserDeptPost;
import com.msj.goods.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author sun li
 * @Date 2018/11/7 8:50
 * @Description
 */
@RestController
@RequestMapping("/rank")
@Api(description = "积分榜排名")
public class IntegralBangController {

    @Autowired
    private IntegralService integralService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysPostService sysPostService;

    @Autowired
    private IntegralTypeService integralTypeService;

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysUserDeptPostService sysUserDeptPostService;

    @PostMapping(value = "/index")
    @Log(title = "积分榜首页展示本部门所有的员工信息")
    @ApiOperation(value="积分榜首页", notes="积分榜展示数据")
    @ApiImplicitParam(name = "pageModelParams",value = "json对象",required = true)
    public JsonResult index(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum,
                            @RequestParam(value = "times",required=false) Integer times, @RequestParam(value = "deptId", required=false) String deptId,
                            @RequestParam(value = "postId" ,required=false) String postId, @RequestParam(value ="typeId", required=false) String typeId,
                            @RequestParam(value = "spTime1" ,required=false) String spTime1, @RequestParam(value ="spTime2", required=false) String spTime2,
                            @RequestParam(value = "search" ,required=false) String search){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<Map> pageInfo = integralService.selectAllList(user,pageSize,pageNum,times,deptId,postId,typeId,spTime1,spTime2,search);
        return JsonResult.success(pageInfo  , JsonResultConstants.SUCCESS);
    }
    /**
     *  根据不同级别的审批人 拥有不同不同的权限
     *  查看不同的部门
     */
    @PostMapping(value = "/selectDept")
    @ApiOperation(value="根据不同级别的审批人", notes="查看不同的部门")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectDept(){
        SysUser user =  ShiroUtils.getUserEntity();
        if(user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        List<Map> dept = null;

        /** 超级管理员 查询所有的部门  */
        if(user.getIsApprover() == UserConstants.SUPER_ADMIN ||
                user.getIsApprover() == UserConstants.DIRECTOR ||
                user.getIsApprover() == UserConstants.GENERAL_MANAGE ||
                user.getIsApprover() == UserConstants.MANAGER ||
                user.getIsApprover()==UserConstants.GENERAL_MANAGE ||
                user.getIsApprover() == UserConstants.FUNCTION){
            dept = sysDeptService.selectDeptList();
        }else
        /** 普通员工 查询所有的部门  */
        if(user.getIsApprover() == UserConstants.COMMON || user.getIsApprover() == UserConstants.COMPETENT_LEVEL){
            dept = new ArrayList<>();
        }
        return JsonResult.success(dept , JsonResultConstants.SUCCESS);
    }
    /**  去除重复的值 */
    private List<Map> deptToHeavy(List<Map> deptList) {

        for (int i = 0; i < deptList.size() - 1; i++) {
            for (int j = deptList.size() - 1; j > i; j--) {
                if (deptList.get(j).equals(deptList.get(i))) {
                    deptList.remove(j);
                }
            }
        }

        return deptList;
    }

    /**
     *  根据不同级别的审批人 拥有不同不同的权限
     *  查询不同职位做检索条件
     */

    @PostMapping(value = "/selectPost")
    @ApiOperation(value="不同职位做检索条件", notes="不同职位做检索条件")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectPost(){
        SysUser user =  ShiroUtils.getUserEntity();
        if(user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        List<Map>  postList = null;
        /** 超级管理员查看所有的职位 */
        if(user.getIsApprover() == UserConstants.SUPER_ADMIN ||
                user.getIsApprover() == UserConstants.MANAGER ||
                user.getIsApprover() == UserConstants.DIRECTOR ||
                user.getIsApprover() == UserConstants.GENERAL_MANAGE ||
                user.getIsApprover() == UserConstants.FUNCTION){
            postList =  sysPostService.selectPostList();
        }else
        /** 普通员工 查询所有的职位  */
        if(user.getIsApprover() == UserConstants.COMMON || user.getIsApprover() == UserConstants.COMPETENT_LEVEL){
            postList = new ArrayList<>();
        }
//        /** 经理 级别 查询所有的职位  */
//        if(){
//            postList = sysPostService.selectManagerPostList();
//        }
//        /** 总监 级别 查询所有的职位  */
//        if(){
//            postList = sysPostService.selectDirectorPostList();
//        }
//        /** 总经理 级别 查询所有的职位  */
//        if(user.getIsApprover() == UserConstants.GENERAL_MANAGE){
//            postList = sysPostService.selectGeneralManagePostList();
//        }
//        /** 职能总监 级别 查询所有的职位  todo  */
//        if(user.getIsApprover() == UserConstants.FUNCTION){
//            postList = sysPostService.selectFunctionPostList();
//        }

        return JsonResult.success(postList, JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/selectType")
    @ApiOperation(value="查询所有积分类型", notes="展示所有积分类型数据")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectType(){
        List<Map>  typeList =  integralTypeService.selectIntegralTypeList();
        return JsonResult.success(typeList , JsonResultConstants.SUCCESS);
    }


    @PostMapping(value = "/selectPersonalList")
    @Log(title = "积分榜查看一个人所的日志列表" )
    @ApiOperation(value="积分榜查看一个人所的日志列表", notes="积分榜查看一个人所的日志列表")
    @ApiImplicitParam(name = "userId",value = "json对象",required = true)
    public JsonResult userLogin(@RequestParam("userId") String userId,@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectPersonalList(userId,pageSize,pageNum);
        return JsonResult.success(pageInfo  , JsonResultConstants.SUCCESS);
    }

}
