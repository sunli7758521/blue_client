package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.annotation.Log;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.enums.BusinessType;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.*;
import com.msj.goods.service.*;
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
 * @Date 2018/11/8 9:29
 * @Description
 */
@RestController
@RequestMapping("/work")
@Api(description = "工作台")
public class WorkbenchController {


    @Autowired
    private GzdService gzdService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private XwIntegralService xwIntegralService;

    @Autowired
    private PdIntegralService pdIntegralService;

    @Autowired
    private YjIntegralService yjIntegralService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysDeptService sysDeptService;

//    /** 工作台
//     *  经理以上所展示的界面
//     *  普通员工所展示的页面
//     *  不同
//     */
//
//    @PostMapping(value = "/index")
//    @ApiOperation(value="展示工作台所有的数据", notes="所有的数据")
//    @ApiImplicitParam(name = "",value = "json对象",required = true)
//    public JsonResult selectWorkbench(){
//        SysUser user = ShiroUtils.getUserEntity();
//        if (user ==null){
//            return JsonResult.failure(2018,"请重新登录");
//        }
//        EntityWrapper<SysUserRole> ew1 = new EntityWrapper<>();
//        ew1.eq("user_id",user.getUserId());
//        SysUserRole userRloe = sysUserRoleService.selectOne(ew1);
//        SysRole role =  sysRoleService.selectById(userRloe.getRoleId());
//        EntityWrapper<Gzd> ew = new EntityWrapper<>();
//        List<Gzd> gzt = null;
//        if(role.getRoleKey().equalsIgnoreCase("superAdmin")){
//            gzt  =  gzdService.selectList(ew);
//        }else if(role.getRoleKey().equalsIgnoreCase("admin")){
//            gzt  =  gzdService.selectList(ew);
//        } else if(role.getRoleKey().equalsIgnoreCase("common")){
//            ew.eq("yy_type","员工应用");
//            gzt = gzdService.selectList(ew);
//        }else{
//            JsonResult.failure(JsonResultConstants.FAIL);
//        }
//        return JsonResult.success(gzt , JsonResultConstants.SUCCESS);
//    }
    /** ===========================行为积分 查询本部门 和所有 通用的菜单项============================================= */
    @PostMapping(value = "/declareBehavior")
    @Log(title = "行为积分")
    @ApiOperation(value="行为积分", notes="查询行为积分项")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult declareBehavior(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum,
                                      @RequestParam(value = "search",required = false) String search){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<XwIntegral> pageInfo = xwIntegralService.selectDeptIntegral(user,pageSize,pageNum,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    @GetMapping(value = "/declareBehaviorDetail/{behaviorId}")
    @Log(title = "行为积分详情页")
    @ApiOperation(value = "行为积分详情页")
    @ApiImplicitParam(name = "behaviorId", value = "行为积分唯一标识", required = true)
    public JsonResult declareBehaviorDetail(@PathVariable("behaviorId") String behaviorId) {
        return JsonResult.success(xwIntegralService.selectById(behaviorId) , JsonResultConstants.SUCCESS);
    }

    /**
     *   查询所有的用户   接口   todo
     */
    @PostMapping(value = "/declareBehaviorDetail/selectAllUser")
    @Log(title = "查询所有的用户")
    @ApiOperation(value="查询所有的用户", notes="查询所有的用户")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectAllUser(@RequestParam(value = "pageSize",required = false) String pageSize,
                                    @RequestParam(value = "pageNum",required = false) String pageNum,
                                    @RequestParam(value = "search",required = false) String search
                                   ){
        PageInfo<SysUser> pageInfo = sysUserService.selectAllUser(pageSize,pageNum,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    /**
     *   查询所有本部门的用户   接口
     */
    @PostMapping(value = "/declareBehaviorDetail/selectAllDeptUser")
    @Log(title = "查询本部门所有的用户")
    @ApiOperation(value="查询本部门所有的用户", notes="查询本部门所有的用户")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectAllDeptUser(@RequestParam(value = "pageSize",required = false) String pageSize,
                                    @RequestParam(value = "pageNum",required = false) String pageNum,
                                    @RequestParam(value = "search",required = false) String search){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<SysUser> pageInfo = sysUserService.selectAllDeptUser(user,pageSize,pageNum,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/declareBehaviorDetail/approverPel")
    @Log(title = "查询审批人 默认是自己本部门的领导")
    @ApiOperation(value="查询审批人 默认是自己本部门的领导", notes="查询审批人 默认是自己本部门的领导")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectApproverPel(){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        Map userList = sysUserService.selectApproverPel(user);
        return JsonResult.success(userList , JsonResultConstants.SUCCESS);
    }


    /**
     *  申请人默认是当前对象
     * */
    @PostMapping(value = "/selectSysUser")
    @Log(title = "查询当前登录用户")
    @ApiOperation(value="查询当前登录用户", notes="查询当前登录用户")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectSysUser(){
     SysUser user =   ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        return JsonResult.success(user , JsonResultConstants.SUCCESS);
    }

    /**
     *  from 申请人  to 抄送人    apps 审批人
     * */
    @PostMapping(value = "/addIntegralApprover")
    @Log(title = "添加一条数据的审批",businessType = BusinessType.INSERT)
    @ApiOperation(value="添加一条数据的审批", notes="添加一条数据的审批数据")
    @ApiImplicitParam(name = "integralApproval",value = "json对象",required = true)
    public JsonResult addIntegralApprover(
                                          @RequestParam(value = "addIntegral" ,required = false) String addIntegral,
                                          @RequestParam(value = "approvalImg" ,required = false) String[]  approvalImg1,
                                          @RequestParam(value = "spRemark",required = false) String spRemark,
                                          @RequestParam(value = "typeId",required = false) String typeId,
                                          @RequestParam(value = "from",required = false) String[] from,
                                          @RequestParam(value = "to",required = false) String[] to,
                                          @RequestParam(value = "approvalContent",required = false) String approvalContent,
                                          @RequestParam(value = "approvalTitle",required = false) String approvalTitle,
                                          @RequestParam(value = "approvalId",required = false) String approvalId,
                                          @RequestParam(value = "apps",required = false) String [] apps ){
        SysUser sysUser = ShiroUtils.getUserEntity();
        if (sysUser ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
          boolean result =  integralApprovalService.insertIntegralApprover(sysUser,addIntegral,
                  spRemark,typeId,from,to,approvalImg1,approvalContent,approvalTitle,approvalId,apps);
        return result ? JsonResult.success(result,JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }

    @PostMapping(value = "/approverLog")
    @Log(title = "工作台审批日志" ,businessType = BusinessType.OTHER)
    @ApiOperation(value="工作台审批日志", notes="工作台审批日志")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult approverLog(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectIntegralApprovalLog(user,pageSize,pageNum);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }
    @GetMapping(value = "/approverLogDetail/{approvalId}")
    @Log(title = "审批日志详情页")
    @ApiOperation(value = "审批日志详情页")
    @ApiImplicitParam(name = "behaviorId", value = "审批日志唯一标识", required = true)
    public JsonResult approverLogDetail(@PathVariable("approvalId") String approvalId) {
        IntegralApproval ia =  integralApprovalService.selectById(approvalId);
        Map map = new HashMap();
        map.put("addIntegral",ia.getSqIntegral());
        if(ia.getkIntegral()>0){
            map.put("kIntegral",ia.getkIntegral());
        } else{
            map.put("kIntegral",0);
        }
        map.put("approvalContent",ia.getApprovalContent());
        map.put("userName",ia.getUserName());
        map.put("userImg",ia.getUserImg());
      SysUser use =  sysUserService.selectById(ia.getUserId());
        map.put("userDept",sysDeptService.selectById(use.getDeptId()).getDeptName());
        map.put("sqTime",DateUtils.getStringDate(ia.getSqTime()));
        if (ia.getSpTime() !=null){
            map.put("spTime",DateUtils.getStringDate(ia.getSpTime()));
        }
        map.put("approvalNum",ia.getApprovalNum());
        map.put("approvalImg",ia.getApprovalImg1().split(","));
        map.put("approvalTitle",ia.getApprovalTitle());
        map.put("status",ia.getStatus());
        /*如果数据库是数组 就切割   */
        String [] ids = ia.getShenPiRenIds().split(",");

        for (String id : ids) {
         SysUser user =  sysUserService.selectById(ia.getShenPiRenIds());
            map.put("appName",user.getUserName());
            map.put("appImg",user.getAvatar());
            map.put("appDept",sysDeptService.selectById(user.getDeptId()).getDeptName());
        }
        return JsonResult.success(map , JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/countLogNun")
    @Log(title = "审批日志数量以及抄送我的数量")
    @ApiOperation(value="审批日志", notes="审批日志数量")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult countLogMun(){
        SysUser user = ShiroUtils.getUserEntity();

        if(user !=null){
            Map<String,Object>  map = new HashMap<String,Object>();
            Integer counNum =  integralApprovalService.selectCountNum(user.getUserId());
            /** 查询有多少抄送给自己的数量 */
            Integer csrs = integralApprovalService.selectCsrs(user.getUserId());
            Integer dwsp =  integralApprovalService.selectCountDwsp(user.getUserId());
            map.put("dwsp",dwsp);
            map.put("counNum",counNum);
            map.put("counNum1",counNum);
            map.put("csrs",csrs);
            return JsonResult.success(map , JsonResultConstants.SUCCESS);
        }
        return JsonResult.failure(2018,"请重新登录");
    }

    @PostMapping(value = "/selectApproverLog")
    @Log(title = "审批日志列表")
    @ApiOperation(value="审批日志", notes="查询审批日志列表")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectApproverLog(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectApproverLog(user,pageSize,pageNum);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    @PostMapping(value = "/selectMyFq")
    @Log(title = "我发起的列表审核中")
    @ApiOperation(value="我发起的列表审核中", notes="我发起的列表审核中")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectMyFq(@RequestParam(value = "pageSize" ,required = false) Integer pageSize,
                                 @RequestParam(value = "pageNum" ,required = false) Integer pageNum,
                                 @RequestParam(value = "status" ,required = false) String status,
                                 @RequestParam(value = "search" ,required = false) String search){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectMyFq(user,pageSize,pageNum,status,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }


    @PostMapping(value = "/selectCswdList")
    @Log(title = "抄送我的审核列表")
    @ApiOperation(value="抄送我的审核列表", notes="抄送我的未审核列表")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult selectCswdList(@RequestParam("pageSize") String pageSize,
                                     @RequestParam("pageNum") String pageNum,
                                     @RequestParam("search") String search,
                                     @RequestParam("status") String status){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<IntegralApproval> pageInfo = integralApprovalService.selectCswdList(user,pageSize,pageNum,search,status);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }


/** ===========================品德积分  查询本部门 和所有 通用的菜单项============================================= */
    @PostMapping(value = "/declareMoral")
    @Log(title = "品德积分")
    @ApiOperation(value="品德积分", notes="查询品德积分项")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult declareMoral(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum,
                                   @RequestParam(value = "search",required = false) String search){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<XwIntegral> pageInfo = pdIntegralService.selectDeclareMoral(user,pageSize,pageNum,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    @GetMapping(value = "/declareMoralDetail/{behaviorId}")
    @Log(title = "品德积分详情页")
    @ApiOperation(value = "品德积分详情页")
    @ApiImplicitParam(name = "behaviorId", value = "品德积分唯一标识", required = true)
    public JsonResult declareMoralDetail(@PathVariable("behaviorId") String behaviorId) {
        return JsonResult.success(pdIntegralService.selectById(behaviorId) , JsonResultConstants.SUCCESS);
    }


    /** ===========================业绩积分 查询本部门 和所有 通用的菜单项============================================= */
    @PostMapping(value = "/declareResults")
    @Log(title = "业绩积分")
    @ApiOperation(value="业绩积分", notes="查询业绩积分项")
    @ApiImplicitParam(name = "pageSize,pageNum",value = "json对象",required = true)
    public JsonResult declareResults(@RequestParam("pageSize") String pageSize, @RequestParam("pageNum") String pageNum,
                                     @RequestParam(value = "search",required = false) String search){
        SysUser user = ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        PageInfo<XwIntegral> pageInfo = yjIntegralService.selectDeclareResults(user,pageSize,pageNum,search);
        return JsonResult.success(pageInfo , JsonResultConstants.SUCCESS);
    }

    @GetMapping(value = "/declareResultsDetail/{behaviorId}")
    @Log(title = "业绩积分详情页")
    @ApiOperation(value = "业绩积分详情页")
    @ApiImplicitParam(name = "behaviorId", value = "业绩积分唯一标识", required = true)
    public JsonResult ddeclareResultsDetail(@PathVariable("behaviorId") String behaviorId) {
        return JsonResult.success(yjIntegralService.selectById(behaviorId) , JsonResultConstants.SUCCESS);
    }
}
