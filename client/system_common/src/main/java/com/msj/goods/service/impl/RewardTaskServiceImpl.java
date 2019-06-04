package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.entity.*;
import com.msj.goods.mapper.RewardTaskMapper;
import com.msj.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 悬赏任务表 服务实现类
 *
 * @author sun li
 * @since 2018-12-25
 */
@Service
public class RewardTaskServiceImpl extends ServiceImpl<RewardTaskMapper, RewardTask>
        implements RewardTaskService {
    @Autowired
    public SysDeptService sysDeptService;
    @Autowired
    public SysUserService sysUserService;
    @Autowired
    public RewardTaskDeptService rewardTaskDeptService;
    @Autowired
    public RewardTaskUserService rewardTaskUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 1.今日任务 2.抢单任务 3.挑战任务
     */
    @Override
    public PageInfo<TashAndUser> selectDayTask(Integer pageSize, Integer pageNum, Integer times, SysUser user) {

        EntityWrapper<SysUserRole> ew = new EntityWrapper<SysUserRole>();
        ew.eq("user_id", user.getUserId());
        SysUserRole userRole = sysUserRoleService.selectOne(ew);
        SysRole role = sysRoleService.selectById(userRole.getRoleId());
        String roleId = role.getRoleId().toString();
        String roleKey = role.getRoleKey();
        List<TashAndUser> lastMap = new ArrayList<>();
        lastMap.clear();
        List<TashAndUser> listMap = new ArrayList<>();
        lastMap.clear();
        List<TashAndUser> list = null;

        // 关联查询那个用户谁抢了这条任务
        List<TaskUserList> rtuList =null;
        //  分页
        PageHelper.startPage(pageNum, pageSize);
        if(times == 1) {
            if (roleKey.equalsIgnoreCase(UserConstants.ROLE_SUPER_ADMIN) || roleKey.equalsIgnoreCase(UserConstants.ROLE_ADMIN) || roleKey.equalsIgnoreCase(UserConstants.ROLE_COMMON) || roleKey.equalsIgnoreCase(UserConstants.ROLE_MANAGER) || roleKey.equalsIgnoreCase(UserConstants.ROLE_GENERAL_MANAGE) || roleKey.equalsIgnoreCase(UserConstants.ROLE_FUNCTION) || roleKey.equalsIgnoreCase(UserConstants.ROLE_DIRECTOR) || roleKey.equalsIgnoreCase(UserConstants.ROLE_SPECIAL) || roleKey.equalsIgnoreCase(UserConstants.ROLE_COMPETENT)) {
                list = this.baseMapper.selectDayTask(user.getDeptId(), times, roleId);
                lastMap.addAll(list);
                for (TashAndUser tashAndUser : lastMap) {
                    rtuList = rewardTaskUserService.selectRTUList(tashAndUser.getRtId() + "");
//                    // 添加 当前登录用户 id
//                    tashAndUser.setUserId(user.getUserId()+"");
                    if (rtuList.size() > 0) {

                        tashAndUser.setTaskAndUserList(rtuList);
                        for (TaskUserList userList : rtuList) {
                            if (userList.getUserId().equals(user.getUserId())) {
                                tashAndUser.setUserState(userList.getState());
                            }
                            else {
                                tashAndUser.setUserState("0");
                            }
                        }
                    }
                    else {
                        tashAndUser.setUserState("0");
                    }
                }
            }
        }else if(times == 2){
                  if(roleKey.equalsIgnoreCase(UserConstants.ROLE_SUPER_ADMIN) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_ADMIN) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_COMMON) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_MANAGER) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_GENERAL_MANAGE) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_FUNCTION) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_DIRECTOR) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_SPECIAL) ||
                    roleKey.equalsIgnoreCase(UserConstants.ROLE_COMPETENT)
                 ) {
                      list = this.baseMapper.selectTask(user.getDeptId(), times, roleId);
                      lastMap.addAll(list);
                      for (TashAndUser tashAndUser : lastMap) {
                          rtuList = rewardTaskUserService.selectRTUList(tashAndUser.getRtId() + "");
//                    // 添加 当前登录用户 id
//                    tashAndUser.setUserId(user.getUserId()+"");
                          if (rtuList.size() > 0) {


                              tashAndUser.setTaskAndUserList(rtuList);
                              for (TaskUserList userList : rtuList) {
                                  if (userList.getUserId().equals(user.getUserId())) {
                                      tashAndUser.setUserState(userList.getState());
                                  } else {
                                      tashAndUser.setUserState("0");
                                  }
                              }
                          } else {
                              tashAndUser.setUserState("0");
                          }
                      }
                  }
            }else if(times ==3){
                if(roleKey.equalsIgnoreCase(UserConstants.ROLE_SUPER_ADMIN) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_ADMIN) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_COMMON) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_MANAGER) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_GENERAL_MANAGE) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_FUNCTION) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_DIRECTOR) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_SPECIAL) ||
                        roleKey.equalsIgnoreCase(UserConstants.ROLE_COMPETENT)
                ) {
                    list = this.baseMapper.selectTask(user.getDeptId(), times, roleId);
                    lastMap.addAll(list);

                    for (TashAndUser tashAndUser : lastMap) {
//                        // 添加 当前登录用户 id
//                        tashAndUser.setUserId(user.getUserId()+"");

                        rtuList  = rewardTaskUserService.selectRTUList(tashAndUser.getRtId()+"");
                        if(rtuList.size()>0){

                            tashAndUser.setTaskAndUserList(rtuList);
                            for (TaskUserList userList : rtuList) {
                                if(userList.getUserId().equals(user.getUserId())){
                                    tashAndUser.setUserState(userList.getState());
                                }else{
                                    tashAndUser.setUserState("0");
                                }
                            }
                        }else{
                            tashAndUser.setUserState("0");
                        }
                    }
                }
            }

        return new PageInfo<>(lastMap);
    }

    @Override
    public Integer saveTaskUserAndDept(Integer rtId, String depId, String roleIds, Integer dId) {
        if (StringUtils.isEmpty(depId)) {
            depId = this.findDeptById(dId);
        }
        String[] depts = depId.split(",");
        List<SysUser> users = new ArrayList<SysUser>();
        List<SysUser> userlist = null;
        List<RewardTaskDept> rtds = new ArrayList<RewardTaskDept>();
        for (String deptId : depts) {
            Integer id = Integer.valueOf(deptId);
            if (StringUtils.isNotEmpty(roleIds)) {
                String[] roles = roleIds.split(",");
                for (String roleId : roles) {
                    userlist = sysUserService.findUserByDepId(id, null, Integer.valueOf(roleId));
                    users.addAll(userlist);
                }
            } else {
                userlist = sysUserService.findUserByDepId(id, null, null);
                users.addAll(userlist);
            }
            RewardTaskDept rtd = new RewardTaskDept();
            rtd.setdId(id);
            rtd.setRtId(rtId);
            rtd.setCreateDate(new Date());
            rtds.add(rtd);
        }
        rewardTaskDeptService.insertBatch(rtds);
        return users.size();
    }

    /**
     * 查询当前所在部门以及下级部门的所有主键
     *
     * @param depid
     * @return
     */
    private String findDeptById(Integer depid) {
        StringBuffer sb = new StringBuffer();
        SysDept dept = sysDeptService.selectById(depid);
        List<SysDept> depts = new ArrayList<SysDept>();
        depts.add(dept);
        depts = sysDeptService.findDeptAndSubUsers(dept, depts);
        if (depts.isEmpty()) return null;
        for (SysDept sd : depts) {
            Integer id = sd.getDeptId();
            sb.append(String.valueOf(id));
            sb.append(",");
        }
        String str = sb.substring(0, sb.length() - 1);
        return str;
    }

    /**
     * 悬赏任务 显示数量 日常任务，抢单，挑战
     */
    @Override
    public Integer selectCountTaskDeptNum(Integer deptId,String roleId) {
        return this.baseMapper.selectCountTaskDeptNum(deptId,roleId);
    }

    /**
     * 通过部门id 关联查询发布任务的状态
     *//*
    @Override
    public RewardTask selectTaskStatus(Integer deptId) {
        return this.baseMapper.selectTaskStatus(deptId);
    }*/
}
