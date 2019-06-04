package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.ManageUser;
import com.msj.goods.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表 服务类
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysUserService extends IService<SysUser> {



    public int insertUser(int userId,int quan);


    /****
     *
     * 通过 主键id 查询出当前的登录人
     * */
    SysUser selectuserid(Integer userId);
    /**
     * 通过手机号查询当前用户 return user
     */
    SysUser selectPhoneByUser(String phone);

    /**
     * 查询所有的员工 return user
     */
    PageInfo<SysUser> selectAllUser(String pageSize, String pageNum, String search);

    /**
     * 查询部门所用审批人
     */
    Map selectApproverPel(SysUser user);

    /**
     * 通过手机号查询当前用户 return user
     */
    SysUser selectPhoneUser(String mobile);

    /**
     * 查询本部门所有的用户
     */
    PageInfo<SysUser> selectAllDeptUser(SysUser user, String pageSize, String pageNum, String search);

    /**
     * 修改爱心点赞值
     *
     * @param loveIntegral
     */
    public void updateUser(@Param("loveIntegral") Integer loveIntegral);

    /**
     * 根据被点赞用户id，查询点赞用户信息
     *
     * @param benefit_id 点赞用户id
     */
    List<SysUser> findByBenefitId(Integer benefit_id);

    /**
     * 查询所用属于本部门的员工
     */
    List<Map> selectDeptAllUser(Integer userId, Integer deptId, String search);

    /**
     * 根据部门查询用户
     *
     * @param depId
     * @return
     */
    List<SysUser> findUserByDepId(Integer depId, Integer userId, Integer roleId);

    /**
     * 普通用户查询所有的部门人员
     */
    List<Map> selectCommonDept(Integer deptId, String search);

    List<ManageUser> selectCommonDept1(Integer deptId, String search);

    /**
     * 经理级别的奖励人员查询
     */
    List<ManageUser> selectManagerDept(Integer deptId, Integer userId, String search);

    /**
     * 总监级 查询所管理部门 经理级别的人员
     */
    List<ManageUser> selectDirectorDept(Integer deptId, Integer userId, String search, Integer status);

    /**
     * 事业部总经理 查询所管理部门 经理级别的人员
     */
    List<ManageUser> selectGeneralManagerDept(Integer deptId, Integer userId, String search);

    /** 医美 市场部查询经理角色 */
    ManageUser selectRole(Long deptId, Integer status);
}
