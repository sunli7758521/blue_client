package com.msj.goods.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.common.constants.JsonResultConstants;
import com.msj.goods.common.utils.DateUtils;
import com.msj.goods.common.utils.ShiroUtils;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.common.web.base.JsonResult;
import com.msj.goods.entity.*;
import com.msj.goods.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

/**
 * @author zhaoyan @Date 2019/1/22 17:35
 */
@RestController
@RequestMapping("/releaseTask")
@Api(description = "发布任务")
@Transactional
public class ReleaseTaskController {

    @Autowired
    public SysDeptService sysDeptService;
    @Autowired
    public SysUserService sysUserService;
    @Autowired
    public SysRoleService sysRoleService;
    @Autowired
    public TaskTypeService taskTypeService;

    @Autowired
    public IntegralTypeService integralTypeService;

    @Autowired
    public IntegralApprovalService integralApprovalService;

    @Autowired
    public RewardTaskService rewardTaskService;

    @PostMapping(value = "/releaseTaskPage")
    @ApiOperation(value = "发布任务页面", notes = "数据")
    @ApiImplicitParam(name = "", value = "json对象", required = true)
    public JsonResult releaseTaskPage() {
        /** 获取当前用户 */
        SysUser user = ShiroUtils.getUserEntity();
        SysDept dept = sysDeptService.selectById(user.getDeptId());
        /** 任务类型 */
        List<TaskType> tasks = taskTypeService.selectList(new EntityWrapper<TaskType>());
        List<SysRole> sysRoles = sysRoleService.selectList(new EntityWrapper<SysRole>());
        /** 积分类型 */
        List<SysDept> list = new ArrayList<SysDept>();
        list.add(dept);
        List<SysDept> depts = sysDeptService.findDeptAndSubUsers(dept, list);
        List<IntegralType> its = integralTypeService.selectList(new EntityWrapper<IntegralType>());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getUserId());
        map.put("deptId", user.getDeptId());
        map.put("ancestors", dept.getAncestors() + "," + user.getDeptId());
        map.put("userName", null);
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("tasks", tasks);
        maps.put("its", its);
        maps.put("depts", depts);
        maps.put("sysRoles", sysRoles);
        return JsonResult.success(maps, JsonResultConstants.SUCCESS);
    }

    /**
     * 保存发布任务
     */
    @PostMapping(value = "/saveReleTask")
    public JsonResult saveReleTask(RewardTask rt) {
        try {
            SysUser user = ShiroUtils.getUserEntity();
            if (user == null) {
                return JsonResult.failure(2018, "请重新登录");
            }
            String depId = rt.getDeptId();
            String remark = rt.getRemark();
            String sort = rt.getSort();
            String end = rt.getStatus();
            if (StringUtils.isNotEmpty(sort) && StringUtils.isNotEmpty(end)) {
                Date startTime = DateUtils.getStringToDate(sort + " 00:01:01");
                Date endDate = DateUtils.getStringToDate(end + " 23:59:59");
                rt.setStartTime(startTime);
                rt.setEndTime(endDate);
            }
            rt.setSort(null);
            rt.setRemark(null);
            rt.setDeptId(String.valueOf(user.getDeptId()));
            rt.setStatus("0");
            rt.setReleaseUserId(user.getUserId());
            rt.setRoleIds(remark);
            rt.setCreateTime(DateUtils.getNowDate());
            rewardTaskService.insert(rt);
            int userCount =
                    rewardTaskService.saveTaskUserAndDept(rt.getRtId(), depId, remark, user.getDeptId());
            Integer manAward = user.getManAward();
            Integer ti = rt.getTaskIntegral();
            Integer pelNum =  rt.getPeopleNum();
            synchronized (user){
                if(pelNum !=null){
                    int total =  pelNum * ti;
                    manAward = manAward - total;
                    user.setManAward(manAward);
                    sysUserService.updateById(user);
                }else {
                    // 日常任务
                    int total = userCount * ti;
                    manAward = manAward - total;
                    user.setManAward(manAward);
                    sysUserService.updateById(user);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return JsonResult.success();
  }
}
