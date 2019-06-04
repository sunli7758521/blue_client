package com.msj.goods.controller;

import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.App;
import com.msj.goods.entity.IntegralApproval;
import com.msj.goods.entity.SysDept;
import com.msj.goods.entity.SysUser;
import com.msj.goods.service.IntegralApprovalService;
import com.msj.goods.service.SysDeptService;
import com.msj.goods.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sun li
 * @Date 2018/11/14 10:11
 * @Description 工作台 审批人和高级审批人
 */
@RestController
@RequestMapping("/approversPel")
@Api(description = "工作台 审批人和高级审批人")
public class WorkIsNotApproversPelController {

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping(value = "/selectApproversList")
    @Log(title = "是审批人  查询待审批")
    @ApiOperation(value="查询待我审批和已审批列表", notes="查询我审批和已审批列表")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectIntegralList(@RequestParam(value = "pageSize" ,required = false) Integer pageSize,
                                         @RequestParam(value = "pageNum" ,required = false) Integer pageNum,
                                         @RequestParam(value = "status" ,required = false) Integer status,
                                         @RequestParam(value = "search" ,required = false) String search){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<App> pageInfo = integralApprovalService.selectApproversPel(user,pageSize,pageNum,status,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }
        /**
         *   待审和已审批详情页
         *
         * */

    @GetMapping(value = "/selectApproversDetail/{approvalId}")
    @Log(title = "待审和已审批详情页")
    @ApiOperation(value = "待审和已审批详情页")
    @ApiImplicitParam(name = "approvalId", value = "待审和已审批详情页", required = true)
    public JsonResult selectApproversDetail(@PathVariable("approvalId") String approvalId) {

        IntegralApproval ia =  integralApprovalService.selectById(approvalId);
        Map map = new HashMap();
        if(ia !=null){
            map.put("addIntegral",ia.getSqIntegral());
            map.put("approvalContent",ia.getApprovalContent());
            map.put("userName",ia.getUserName());
            map.put("userImg",ia.getUserImg());
            map.put("sqTime", DateUtils.getStringDate(ia.getSqTime()));
        }


        SysUser use =  sysUserService.selectById(ia.getUserId());
        if(use !=null){
            SysDept    sysDept =   sysDeptService.selectById(use.getDeptId());
            if(sysDept !=null){
                map.put("userDept",sysDept.getDeptName());
            }else{
                map.put("userDept","");
            }

        }else {
            map.put("userDept","");
        }


        if (ia.getSpTime() !=null){
            map.put("spTime",DateUtils.getStringDate(ia.getSpTime()));
        }
        map.put("approvalNum",ia.getApprovalNum());
        map.put("approvalImg",ia.getApprovalImg1().split(","));
        map.put("approvalTitle",ia.getApprovalTitle());
        map.put("status",ia.getStatus());
        map.put("integralTypeId",ia.getIntegralTypeId());
        /** 审批不通 和 撤销审批不通的 理由   */
        if(ia.getApprovalImg9() !=null){
            map.put("disapproveUndo",ia.getApprovalImg9());
        }else{
            map.put("disapproveUndo","");
        }
        /** 申诉理由 */
        if(ia.getApprovalImg7() != null){
            map.put("appReason",ia.getApprovalImg7());
        }else{
            map.put("appReason","");
        }
        if(ia.getShenPiRenIds() !=null){
        String [] ids = ia.getShenPiRenIds().split(",");
            //ia.getShenPiRenIds
         for (String id : ids) {
            SysUser user =  sysUserService.selectById(id);
            map.put("appName",user.getUserName());
            map.put("appImg",user.getAvatar());
          SysDept dept = sysDeptService.selectById(user.getDeptId());
          if(dept != null){
              map.put("appDept",dept.getDeptName());
          }
         }
      }else{
            SysUser user =  sysUserService.selectById(ia.getTiJiaoId());
            if(user !=null){
                map.put("appName",user.getUserName());
                map.put("appImg",user.getAvatar());
                map.put("appDept",sysDeptService.selectById(user.getDeptId()).getDeptName());
            }

        }
        return JsonResult.success(map , JsonResultConstants.SUCCESS);
    }

    /** 审批不通过 和撤销审批理由
     *  approvalId
     *  status
     *  disapproveUndo   审批不通过 和撤销审批理由
     *  /{approvalId}/{status}
     *
     *
     * */
    @GetMapping(value = "/approversYesNo/{approvalId}/{status}")
    @Log(title = "前端传来 approvalId 和 状态来判断 status ")
    @ApiOperation(value = "待审和已审批详情页")
    @ApiImplicitParam(name = "approvalId", value = "待审和已审批详情页", required = true)
    public JsonResult approversYesNo(@PathVariable("approvalId") String approvalId,
                                      @PathVariable("status") String status,
                                      String disapproveUndo,
                                     String appReason) {
        String keyWord =null;

        if(disapproveUndo !=null){
            try {
                keyWord =  URLDecoder.decode(disapproveUndo, "utf-8");
                System.out.println(keyWord+"=============");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            keyWord = disapproveUndo;
        }

        String app =null;

        if(appReason !=null){
            try {
                keyWord =  URLDecoder.decode(appReason, "utf-8");
                System.out.println(keyWord+"=============");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            app = appReason;
        }


        return JsonResult.success(integralApprovalService.approversYesNo(approvalId,status,keyWord,app) , JsonResultConstants.SUCCESS);
    }
}
