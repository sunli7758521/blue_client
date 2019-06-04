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

import java.util.*;

/**
 * @author sun li @Date 2018/12/25 15:36 @Description
 */
@RestController
@RequestMapping("/task")
@Api(description = "悬赏任务管理")
public class TaskController {

    @Autowired
    private RewardTaskService rewardTaskService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RewardTaskUserService rewardTaskUserService;

    @Autowired
    private IntegralApprovalService integralApprovalService;

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping(value = "/allTask")
    @Log(title = "日常,挑战，抢单，任务列表", businessType = BusinessType.OTHER)
    @ApiOperation(value = "日常,挑战，抢单，任务列表", notes = "日常,挑战，抢单，任务列表")
    @ApiImplicitParam(name = "pageModelParams", value = "json对象", required = true)
    public JsonResult index(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam(value = "times", required = false) Integer times) {
        SysUser user = ShiroUtils.getUserEntity();
        if (user == null) {
            return JsonResult.failure(2018, "请重新登录");
        }
        PageInfo<TashAndUser> pageInfo = rewardTaskService.selectDayTask(pageSize,pageNum,times,user);

        return JsonResult.success(pageInfo, JsonResultConstants.SUCCESS );
    }

    /**
     *   悬赏任务  日常任务，挑战任务，抢单任务  详情页
     */
    @GetMapping(value = "/selectDetails/{taskId}")
    @Log(title = "悬赏任务 详情页", businessType = BusinessType.OTHER)
    @ApiOperation(value="悬赏任务 详情页", notes="悬赏任务 详情页")
    @ApiImplicitParam(name = "taskId",value = "json对象",required = true)
    public JsonResult selectDelMenu(@PathVariable("taskId") String taskId){

        RewardTask task =  rewardTaskService.selectById(taskId);
        Map map = new HashMap();
        map.put("rtId",task.getRtId());
        map.put("title",task.getTitle());
        map.put("content",task.getContent());
        map.put("integralTypeId",task.getIntegralTypeId());
        map.put("deptId",task.getDeptId());
        // 申请方式
        map.put("appWay",task.getAppWay());
        map.put("remark",task.getRemark());
        map.put("taskIntegral",task.getTaskIntegral());
        if(task.getCreateTime() !=null ){
            map.put("createTime", DateUtils.getStringDate(task.getCreateTime()));
        }
        if( task.getStartTime() !=null ){
            map.put("startTime",DateUtils.getStringDate(task.getStartTime()));
        }
        if(task.getEndTime() !=null){
            map.put("endTime",DateUtils.getStringDate(task.getEndTime()));
        }
        map.put("releaseUserId",task.getReleaseUserId());
        map.put("releaseUserName",sysUserService.selectById(task.getReleaseUserId()).getUserName());

        return JsonResult.success(map, JsonResultConstants.SUCCESS );
    }

    /**
     *   悬赏任务 显示数量 日常任务，抢单，挑战
     */
    @GetMapping(value = "/selectTaskNum")
    @Log(title = "悬赏任务 显示数量", businessType = BusinessType.OTHER)
    @ApiOperation(value="悬赏任务 显示数量", notes="悬赏任务 显示数量")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult selectTaskNum(){
      SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
        EntityWrapper<SysUserRole> ew = new EntityWrapper<SysUserRole>();
        ew.eq("user_id", user.getUserId());
        SysUserRole userRole = sysUserRoleService.selectOne(ew);
        SysRole role = sysRoleService.selectById(userRole.getRoleId());
        String roleId = role.getRoleId().toString();
        Integer num= rewardTaskService.selectCountTaskDeptNum(user.getDeptId(),null);

        return JsonResult.success(num, JsonResultConstants.SUCCESS );
    }



    /**
     *  当用户点击抢单的时候  更新这条单据的状态，更新用户对这条单据的状态
     */
    @GetMapping(value = "/updateUserAndTaskStatus/{taskId}/{taskStatus}")
    @Log(title = "更新修改用户状态和单据状态",businessType = BusinessType.OTHER)
    @ApiOperation(value="更新修改用户状态和单据状态", notes="更新修改用户状态和单据状态")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public  JsonResult updateUserAndTaskStatus(@PathVariable("taskId")   String taskId,
                                               @PathVariable("taskStatus")   String taskStatus  ){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }

        boolean flag = true;
        RewardTask rewardTask =  rewardTaskService.selectById(taskId);
        if (rewardTask.getPeopleNum() !=null) {
            if (rewardTask.getPeopleNum() == 0 || rewardTask.getPeopleNum() < 0) {
                return JsonResult.failure("本次抢单任务人数名额已满");
            } else {
                    synchronized (rewardTask) {
                        rewardTask.setPeopleNum(rewardTask.getPeopleNum() - 1);
                        flag = rewardTaskService.updateById(rewardTask);
                }
            }
        }


        if(rewardTask.getEndTime() !=null){
            Long currentDate =  new Date().getTime();
            Long rewardMill =   rewardTask.getEndTime().getTime();
            Long day =  (currentDate-rewardMill)/ 86400000;
            if(day >  UserConstants.GRAB_DAY){
                rewardTask.setStatus(UserConstants.TASK_DISABLE+"");
                flag =  rewardTaskService.updateById(rewardTask);
            }
        }
        EntityWrapper<RewardTaskUser> ews = new EntityWrapper<RewardTaskUser>();
        ews.eq("rt_id",taskId);
        ews.eq("user_id",user.getUserId());
        RewardTaskUser  rtu =  rewardTaskUserService.selectOne(ews);

        if(rtu == null){
            RewardTaskUser rtus = new RewardTaskUser();
            // 修改状态 抢到未完成
            rtus.setState(UserConstants.USER_STATUS_DISABLE + "");
            // 用户抢单这个任务的时间
            rtus.setCreateDate(DateUtils.getNowDate());
            rtus.setRtId(Integer.parseInt(taskId));
            rtus.setUserId(user.getUserId());
            flag = rewardTaskUserService.insert(rtus);

        }else{
            return JsonResult.failure("请勿重复选择任务");
        }


        return flag ? JsonResult.success(flag,JsonResultConstants.SUCCESS ) : JsonResult.failure(JsonResultConstants.FAIL);
    }
    /**
     *  他们（谁）已经抢单    2抢单任务  3挑战任务
     */
    @GetMapping(value = "/selectOtherList/{taskId}")
    @Log(title = "他们（谁）已经抢单",businessType = BusinessType.OTHER)
    @ApiOperation(value="他们（谁）已经抢单", notes="他们（谁）已经抢单")
    @ApiImplicitParam(name = "pageModelParams",value = "json对象",required = true)
    public JsonResult selectOtherList(@PathVariable("taskId") String taskId ){
       List<Map> mapList = new ArrayList<>();
        Map map = new HashMap();
        //传过来 2抢单任务  3挑战任务
        EntityWrapper<RewardTaskUser> ew = new EntityWrapper<RewardTaskUser> ();
        ew.eq("rt_id",taskId);
         List<RewardTaskUser> userList = rewardTaskUserService.selectList(ew);
            for (RewardTaskUser taskUser : userList) {
             SysUser user = sysUserService.selectById(taskUser.getUserId());
             RewardTask rt = rewardTaskService.selectById(taskUser.getRtId());
                map.put("taskIntegral",rt.getTaskIntegral());
                map.put("userId",user.getUserId());
                map.put("userName",user.getUserName());
                map.put("avatar",user.getAvatar());
                map.put("deptId",user.getDeptId());
                map.put("deptName",sysDeptService.selectById(user.getDeptId()).getDeptName());
                map.put("createTime",DateUtils.getStringDate(taskUser.getCreateDate()));
                mapList.add(map);
            }
        return JsonResult.success(mapList  , JsonResultConstants.SUCCESS);
    }

    /**
     *   添加一条 挑战任务 日常任务  抢单任务
     */
    @PostMapping(value = "/addTask")
    @Log(title = "挑战任务", businessType = BusinessType.OTHER)
    @ApiOperation(value="挑战任务", notes="挑战任务")
    @ApiImplicitParam(name = "",value = "json对象",required = true)
    public JsonResult addChallenges(@RequestParam(value ="taskId",required = false) String taskId,
                                    @RequestParam(value ="integralTypeId",required = false) String integralTypeId,
                                    @RequestParam(value ="taskTypeId" ,required = false) String taskTypeId,
                                    @RequestParam(value ="releaseUserId" ,required = false) String releaseUserId,
                                    @RequestParam(value = "pic",required = false) String[] pic,
                                    @RequestParam(value = "remark",required = false) String remark,
                                    @RequestParam( value = "to" ,required = false) String[] to
                                    ){
        SysUser user =  ShiroUtils.getUserEntity();
        if (user ==null){
            return JsonResult.failure(2018,"请重新登录");
        }
//        RewardTask rewardTask =  rewardTaskService.selectById(taskId);
//        if(taskTypeId.equalsIgnoreCase("2")){
//            if(rewardTask.getPeopleNum()==0 ){
//                return   JsonResult.failure("本次抢单任务没有人数了,敬请期待下次任务");
//            }
//        }
        EntityWrapper<IntegralApproval> ew = new EntityWrapper<>();
        ew.eq("approval_img8",taskId);
        ew.eq("user_id",user.getUserId());
        IntegralApproval ia =   integralApprovalService.selectOne(ew);
        if (ia != null){
            return JsonResult.failure("请勿重复提交");
        }

        boolean result = integralApprovalService.addChallenges(taskId,integralTypeId,taskTypeId,releaseUserId,pic,to,user,remark);

        return result ? JsonResult.success(result,JsonResultConstants.ADD_SUCCESS ) : JsonResult.failure(JsonResultConstants.ADD_FAIL);
    }
}
