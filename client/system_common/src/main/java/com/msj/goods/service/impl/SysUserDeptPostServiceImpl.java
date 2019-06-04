package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.entity.*;
import com.msj.goods.mapper.SysUserDeptPostMapper;
import com.msj.goods.service.SysRoleService;
import com.msj.goods.service.SysUserDeptPostService;
import com.msj.goods.service.SysUserRoleService;
import com.msj.goods.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户属于那个部门管理那个部门 服务实现类
 *
 * @author sun li
 * @since 2018-12-30
 */
@Service
public class SysUserDeptPostServiceImpl extends ServiceImpl<SysUserDeptPostMapper, SysUserDeptPost>
        implements SysUserDeptPostService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserDeptPostMapper sysUserDeptPostMapper;



    /**
     * 查询每个领导所管理的部门的人员
     */
    @Override
    public PageInfo<ManageUser> selectLeaderdepts(
            String search, SysUser user, String pageSize, String pageNum) {


        EntityWrapper<SysUserRole> ew1 = new EntityWrapper<SysUserRole>();
        ew1.eq("user_id", user.getUserId());
        SysUserRole userRole = sysUserRoleService.selectOne(ew1);
        SysRole role = sysRoleService.selectById(userRole.getRoleId());
        String rolekey = role.getRoleKey();

        List<ManageUser> listMap = new ArrayList<>();
        listMap.clear();

        List<ManageUser> userList = null;
        /** 总监和总经理级查询和总裁级选择直属领导人员 */
        EntityWrapper<SysUserDeptPost> ew = new EntityWrapper<SysUserDeptPost>();
        ew.eq("user_id", user.getUserId());
        List<SysUserDeptPost> list = this.baseMapper.selectList(ew);
        Integer status = null;

        /** 总监级 查询所管理部门 经理级别的人员 */
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        /** 普通用户查询所有的部门人员 */
        if (rolekey.equalsIgnoreCase("common")) {
            userList = sysUserService.selectCommonDept1(user.getDeptId(), search);
            listMap.addAll(userList);
        }
        /** 经理级别的奖励人员查询 */
        else if (rolekey.equalsIgnoreCase("manager") || rolekey.equalsIgnoreCase("manager1")) {
            userList = sysUserService.selectManagerDept(user.getDeptId(), user.getUserId(), search);
            listMap.addAll(userList);
        }

        // 事业部总监查询自己管理部门经理 和 普通员工怒
       else if (rolekey.equalsIgnoreCase("director")) {
            status = 4;
            for (SysUserDeptPost userDeptPost : list) {
                userList =
                        sysUserService.selectDirectorDept(
                                userDeptPost.getDeptId() , userDeptPost.getUserId(), search, null);
                listMap.addAll(userList);
            }
            //  医美市场部  146
            Long deptId = 146L;
            ManageUser user1 =   sysUserService.selectRole(deptId,status);
            listMap.add(user1);
        }
        /** 事业部总经理 查询所管理部门 经理级别的人员 */
      else  if (rolekey.equalsIgnoreCase("generalManager")) {
            for (SysUserDeptPost userDeptPost : list) {
                userList =
                        sysUserService.selectGeneralManagerDept(
                                userDeptPost.getDeptId(), userDeptPost.getUserId(), search);
                for (ManageUser map : userList) {
                    if (map.getRoleKey().equals("director")) {
                        listMap.add(map);
                        break;
                    }else
                      if (map.getRoleKey().equals(UserConstants.ROLE_MANAGER)
                            || map.getRoleKey().equals("manager1")) {
                        listMap.add(map);
                        break;
                    }
                }
                if (listMap.size() == 0) {
                    listMap.addAll(userList);
                }
            }
        }

        /** 超级管理员 */
      else  if (rolekey.equalsIgnoreCase(UserConstants.ROLE_SUPER_ADMIN)) {
            for (SysUserDeptPost userDeptPost : list) {
                userList =    sysUserService.selectGeneralManagerDept(
                                userDeptPost.getDeptId(), userDeptPost.getUserId(), search);
                for (ManageUser map : userList) {
                    // 事业部总监级
                    if (map.getRoleKey().equals(UserConstants.ROLE_FUNCTION)) {
                        listMap.add(map);
                        break;
                    }else
                    // 总经理
                    if (map.getRoleKey().equals(UserConstants.ROLE_GENERAL_MANAGE)) {
                        listMap.add(map);
                        break;
                    }
                    //  总监级
                   else if (map.getRoleKey().equals(UserConstants.ROLE_DIRECTOR)) {
                        listMap.add(map);
                        break;
                    }
                    // 经理审批
                   else if (map.getRoleKey().equals(UserConstants.ROLE_MANAGER)
                            || map.getRoleKey().equals("manager1")) {
                        listMap.add(map);
                        break;
                    }
                }
            }
        }
        /** 职能总监 */
    else    if (rolekey.equalsIgnoreCase(UserConstants.ROLE_FUNCTION)) {
            userList = sysUserService.selectCommonDept1(user.getDeptId(), search);
            for (ManageUser map : userList) {
                if (map.getRoleKey().equals(UserConstants.ROLE_COMMON)
                        || map.getRoleKey().equals(UserConstants.ROLE_COMPETENT)) {
                    listMap.addAll(userList);
                    break;
                }
            }
        }

        return new PageInfo<>(listMap);
    }

    @Override
    public List<SysUserDeptPost> findSysUserDeptPost() {
        return baseMapper.findSysUserDeptPost();
    }

    @Override
    public List<SysUserDeptPost> findSudpByUserId(Integer userId) {
        return baseMapper.findSudpByUserId(userId);
    }

    @Override
    public void departOwnerPoints(List<SysUserDeptPost> listsp, Integer userId) {
        List<Integer> sb = new ArrayList<Integer>();
        sb.add(3);
        sb.add(4);
        sb.add(5);
        sb.add(6);
        sb.add(7);
        boolean falg = true;
        Integer total = 0;
        for (SysUserDeptPost sp : listsp) {
            List<SysUser> sysUsers = sysUserService.findUserByDepId(sp.getDeptId(), null, null);
            Integer depCount = 0;
            Integer contCount = 0;
            for (SysUser sysUser : sysUsers) {
                Integer is = sysUser.getIntegralStatus();
                Long jci = sysUser.getJiChuIntegral();
                if (sb.contains(is)) {
                    falg = false;
                    contCount = jci.intValue();
                }
                depCount = depCount + jci.intValue();
            }
            if (falg) {
                total = total + depCount;
            } else {
                total = total + contCount;
            }
        }
        SysUser su = sysUserService.selectById(userId);
        su.setManAward(total);
        sysUserService.updateById(su);
    }
    /** 通过部门id 查询谁管理本部门*/
    @Override
    public SysUserDeptPost selectDeptIdUserId(Integer deptId) {

        return sysUserDeptPostMapper.selectDeptIdUserId(deptId);
    }


    /** 通过部门id 查询谁管理本部门*/
    @Override
    public int findSudpByUserId2(Integer userId) {

        return sysUserDeptPostMapper.findSudpByUserId2(userId);
    }

    /** 通过部门id 查询谁管理本部门*/
    @Override
    public int findSudpByUserId3(Integer userId) {

        return sysUserDeptPostMapper.findSudpByUserId3(userId);
    }


}
