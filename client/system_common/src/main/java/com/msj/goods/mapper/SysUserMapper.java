package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.ManageUser;
import com.msj.goods.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表 Mapper 接口
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过手机号查询当前用户 return user
     */
    SysUser selectPhoneByUser(@Param("phone") String phone);


    /****
     * 通过 主键id 查询当前的 登录人
     *
     * */
  /*  insertUser*/
    int insertUser(@Param("userId") Integer userId,@Param("quan") Integer quan);


    SysUser selectuserid(@Param("userId") Integer userId);

    /** 查询所有的员工 return user */
    // List<Map> selectAllList();

    /**
     * 每周日 23点 修改loveIntegral变量为50
     *
     * @param love_integral
     */
    void updateUser(@Param("love_integral") Integer love_integral);

    /**
     * 根据被点赞用户id，查询点赞用户信息
     */
    List<SysUser> findByBenefitId(
            @Param("benefit_id") Integer benefit_id,
            @Param("mondayDate") String mondayDate,
            @Param("sundayDate") String sundayDate);

    /**
     * 查询所用属于本部门的员工
     */
    List<Map> selectDeptAllUser(
            @Param("userId") Integer userId,
            @Param("deptId") Integer deptId,
            @Param("search") String search);

    /**
     * 按照条件查询用户名、用户主键，爱心点赞积分，角色
     *
     * @param deptId
     * @param userId
     * @param roleId
     * @return
     */
    List<SysUser> findUserByDepId(
            @Param("deptId") Integer deptId,
            @Param("userId") Integer userId,
            @Param("roleId") Integer roleId);

    /**
     * 普通用户查询所有的部门人员
     */
    List<Map> selectCommonDept(@Param("deptId") Integer deptId, @Param("search") String search);

    List<ManageUser> selectCommonDept1(@Param("deptId") Integer deptId, @Param("search") String search);
    /**
     * 经理级别的奖励人员查询
     */
    List<ManageUser> selectManagerDept(
            @Param("deptId") Integer deptId,
            @Param("userId") Integer userId,
            @Param("search") String search);

    /**
     * 总监级 查询所管理部门 经理级别的人员
     */
    List<ManageUser> selectDirectorDept(
            @Param("deptId") Integer deptId,
            @Param("userId") Integer userId,
            @Param("search") String search,
            @Param("status") Integer status);

    List<ManageUser> selectGeneralManagerDept(
            @Param("deptId") Integer deptId,
            @Param("userId") Integer userId,
            @Param("search") String search);

    /** 医美 市场部查询经理角色 */
    ManageUser selectRole( @Param("deptId") Long deptId,  @Param("status") Integer status);
}
