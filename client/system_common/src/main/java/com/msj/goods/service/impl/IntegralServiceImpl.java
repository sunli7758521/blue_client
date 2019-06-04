package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.constants.UserConstants;
import com.msj.goods.common.utils.StringUtils;
import com.msj.goods.entity.*;
import com.msj.goods.mapper.IntegralApprovalMapper;
import com.msj.goods.mapper.IntegralMapper;
import com.msj.goods.service.IntegralService;
import com.msj.goods.service.SysRoleService;
import com.msj.goods.service.SysUserDeptPostService;
import com.msj.goods.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IntegralService {

    @Autowired
    private  IntegralMapper integralMapper;

    @Autowired
    private IntegralApprovalMapper integralApprovalMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserDeptPostService sysUserDeptPostService;



    /**
     *  查询所有积分榜展示数据
     *  以及所有的检索条件
     */

    @Override
    public PageInfo<Map> selectAllList(SysUser user,Integer pageSize, Integer pageNum, Integer times, String deptId, String postId, String typeId, String spTime1, String spTime2, String search) {
        List<Map> integralBangs = null;
        PageHelper.startPage(pageNum, pageSize);

        Integer deptIds = null;
        if(StringUtils.isNotBlank(deptId)){
            deptIds = Integer.parseInt(deptId);
        }else{
            deptIds =  user.getDeptId();
        }
        if (StringUtils.isNotNull(times)) {
                /**
                 *  查询本部门当天数据
                 */
                if (times == 1) {
                    integralBangs = integralApprovalMapper.selectAllDayData(deptIds, postId, typeId, spTime1, spTime2, search);
                }

                /** 查询本部门 当月数据*/
                else if (times == 2) {
                    integralBangs = this.integralApprovalMapper.selectAllMonthData(deptIds, postId, typeId, spTime1, spTime2, search);
                }

                /** 查询本部门当季数据*/
                else if (times == 3) {
                    integralBangs = this.integralApprovalMapper.selectAllJiData(deptIds, postId, typeId, spTime1, spTime2, search);
                }

                /** 查询本部门当年数据*/
                else if (times == 4) {
                    integralBangs = this.integralApprovalMapper.selectAllYearData(deptIds, postId, typeId, spTime1, spTime2, search);
                }
            }
        return new PageInfo<>(integralBangs);
    }
//
//
//        List<Map> integralBangList = new ArrayList<>();
//
//        EntityWrapper<SysUserRole> ew = new EntityWrapper<SysUserRole>();
//        ew.eq("user_id", user.getUserId());
//        SysUserRole userRole = sysUserRoleService.selectOne(ew);
//        SysRole role = sysRoleService.selectById(userRole.getRoleId());
//        String rolekey = role.getRoleKey();
//        PageHelper.startPage(pageNum, pageSize);
//        /** 超级管理员   */
//        if (rolekey.equalsIgnoreCase(UserConstants.ROLE_SUPER_ADMIN)) {
//            String deptIds = deptId;
//            if (StringUtils.isNotNull(times)) {
//                /**
//                 *  查询本部门当天数据
//                 */
//
//                if (times == 1) {
//                    integralBangs = integralApprovalMapper.selectAllDayData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//
//                /** 查询本部门 当月数据*/
//                else if (times == 2) {
//                    integralBangs = this.integralApprovalMapper.selectAllMonthData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//
//                /** 查询本部门当季数据*/
//                else if (times == 3) {
//                    integralBangs = this.integralApprovalMapper.selectAllJiData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//
//                /** 查询本部门当年数据*/
//                else if (times == 4) {
//                    integralBangs = this.integralApprovalMapper.selectAllYearData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//            }
//        }
//
//        /** 普通员工 主管 管理员 */
//        else if (rolekey.equalsIgnoreCase(UserConstants.ROLE_COMPETENT) || rolekey.equalsIgnoreCase(UserConstants.ROLE_ADMIN) || rolekey.equalsIgnoreCase(UserConstants.ROLE_COMMON)) {
//            String commonDeptId = user.getDeptId() + "";
//            if (StringUtils.isNotNull(times)) {
//                /**  查询本部门当天数据  */
//                if (times == 1) {
//                    integralBangs = integralApprovalMapper.selectCommonDayData(commonDeptId, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//                /** 查询本部门 当月数据*/
//                else if (times == 2) {
//                    integralBangs = this.integralApprovalMapper.selectCommonMonthData(commonDeptId, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//                /** 查询本部门当季数据*/
//                else if (times == 3) {
//                    integralBangs = this.integralApprovalMapper.selectCommonJiData(commonDeptId, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//                /** 查询本部门当年数据*/
//                else if (times == 4) {
//                    integralBangs = this.integralApprovalMapper.selectCommonYearData(commonDeptId, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//            }
//        }
//        /** 经理级  事业部总经理 */
//        else if (rolekey.equalsIgnoreCase(UserConstants.ROLE_MANAGER) || rolekey.equalsIgnoreCase(UserConstants.ROLE_FUNCTION)) {
//            String deptIds = null;
//            if (deptId.equals("")) {
//                deptIds = user.getDeptId() + "";
//            }
//            else {
//                deptIds = deptId;
//            }
//
//            if (StringUtils.isNotNull(times)) {
//
//                /**  查询本部门当天数据*/
//
//                if (times == 1) {
//                    integralBangs = integralApprovalMapper.selectManagerDayData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//                /** 查询本部门 当月数据*/
//                else if (times == 2) {
//                    integralBangs = this.integralApprovalMapper.selectManagerMonthData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                }
//                /** 查询本部门当季数据*/
//                else if (times == 3) {
//                    integralBangs = this.integralApprovalMapper.selectManagerJiData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//                /** 查询本部门当年数据*/
//                else if (times == 4) {
//                    integralBangs = this.integralApprovalMapper.selectManagerYearData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                    return new PageInfo<>(integralBangs);
//                }
//            }
//
//        }
//
//        EntityWrapper<SysUserDeptPost> ew1 = new EntityWrapper<SysUserDeptPost>();
//        List<SysUserDeptPost> list = null;
//        /** 总监级审批  总经理级  */
//        if (rolekey.equalsIgnoreCase(UserConstants.ROLE_DIRECTOR) || rolekey.equalsIgnoreCase(UserConstants.ROLE_GENERAL_MANAGE)) {
//
//            if (StringUtils.isNotNull(times)) {
//                ew1.eq("user_id", user.getUserId());
//                list = sysUserDeptPostService.selectList(ew1);
//                /**  查询本部门当天数据*/
//                if (times == 1) {
//                    if (deptId.equals("")) {
//                        for (SysUserDeptPost userDeptPost : list) {
//                            String deptIds = userDeptPost.getDeptId() + "";
//                            integralBangs = integralApprovalMapper.selectDirectorDayData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                            integralBangList.addAll(integralBangs);
//                        }
//                        return new PageInfo<>(integralBangList);
//                    }
//                    if (!deptId.equals("")) {
//                        String deptIds = deptId;
//                        integralBangs = integralApprovalMapper.selectDirectorDayData(deptIds, postId, typeId, spTime1, spTime2, search);
//                        return new PageInfo<>(integralBangs);
//                    }
//
//                }
//                /** 查询本部门 当月数据*/
//                else if (times == 2) {
//                    if (deptId.equals("")) {
//                        for (SysUserDeptPost userDeptPost : list) {
//                            String deptIds = userDeptPost.getDeptId() + "";
//                            integralBangs = this.integralApprovalMapper.selectDirectorMonthData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                            integralBangList.addAll(integralBangs);
//                        }
//                        return new PageInfo<>(integralBangList);
//                    }
//                    if (!deptId.equals("")) {
//                        String deptIds = deptId;
//                        integralBangs = this.integralApprovalMapper.selectDirectorMonthData(deptIds, postId, typeId, spTime1, spTime2, search);
//                        return new PageInfo<>(integralBangs);
//                    }
//                }
//                /** 查询本部门当季数据*/
//                else if (times == 3) {
//                    if (deptId.equals("")) {
//                        for (SysUserDeptPost userDeptPost : list) {
//                            String deptIds = userDeptPost.getDeptId() + "";
//                            integralBangs = this.integralApprovalMapper.selectDirectorJiData(deptIds, postId, typeId, spTime1, spTime2, search);
//                            integralBangList.addAll(integralBangs);
//                        }
//                        return new PageInfo<>(integralBangList);
//                    }
//                    if (!deptId.equals("")) {
//                        String deptIds = deptId;
//                        integralBangs = this.integralApprovalMapper.selectDirectorJiData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                        return new PageInfo<>(integralBangs);
//                    }
//                }
//                /** 查询本部门当年数据*/
//                else if (times == 4) {
//                    if (deptId.equals("")) {
//                        for (SysUserDeptPost userDeptPost : list) {
//                            String deptIds = userDeptPost.getDeptId() + "";
//                            integralBangs = this.integralApprovalMapper.selectDirectorYearData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                            integralBangList.addAll(integralBangs);
//                        }
//                        return new PageInfo<>(integralBangList);
//                    }
//                    else if (!deptId.equals("")) {
//                        String deptIds = deptId;
//                        integralBangs = this.integralApprovalMapper.selectDirectorYearData(deptIds, postId, typeId, spTime1, spTime2, search);
//
//                        return new PageInfo<>(integralBangs);
//                    }
//                }
//
//            }
//
//        }
//        return new PageInfo<>(integralBangs);
  //  }

    @Override
    public Integral selectdan(Integer userId) {
        //分页
        /* PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));*/
        /*EntityWrapper<LevelAss> ew = new  EntityWrapper<LevelAss>();*/
        //   List<LevelAss> level= this.baseMapper.selectLevelAssList();
        return integralMapper.selectdan(userId);
    }


    @Override
    public int updatexiugai(Integer integralId,Integer countIntegral,Integer addIntegral, Integer goodCountIntegral) {
        //分页
        /* PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));*/
        /*EntityWrapper<LevelAss> ew = new  EntityWrapper<LevelAss>();*/
        //   List<LevelAss> level= this.baseMapper.selectLevelAssList();
             int rows= integralMapper.updatexiugai(integralId,countIntegral,addIntegral,goodCountIntegral);
             return  rows;
    }

}


