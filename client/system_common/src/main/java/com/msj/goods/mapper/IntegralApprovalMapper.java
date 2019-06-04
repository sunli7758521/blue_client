package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.App;
import com.msj.goods.entity.AppLog;
import com.msj.goods.entity.IntegralApproval;
import com.msj.goods.entity.SysUserDeptPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审批管理 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralApprovalMapper extends BaseMapper<IntegralApproval> {

    /**
     * 查询冠军 本部门的积分冠军
     *
     * @return IntegralApproval
     **/
    List<Map> selectGJ(@Param("deptIds") Integer deptIds);

    /**
     * 查询积分榜当日数据
     *
     * @param deptId
     * @param postId
     * @param typeId
     * @param spTime2
     * @return IntegralApproval
     */
    List<Map> selectAllDayData(@Param("deptIds") Integer deptIds,

                               @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    List<Map> selectAllMonthData(@Param("deptIds") Integer deptIds,

                                 @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);


    List<Map> selectAllJiData(@Param("deptIds") Integer deptIds,

                              @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    List<Map> selectAllYearData(@Param("deptIds") Integer deptIds,

                                @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /**
     * 经理
     */
    List<App> selectAllList(@Param("userId") Integer userId,  @Param("search") String search,@Param("status") Integer status);


    /**
     * 抄送我的未审核列表
     */
    List<IntegralApproval> selectCswdNot(@Param("search") String search, @Param("userId") Integer userId);

    /**
     * 抄送我的审批通过和撤销审批
     */
    List<IntegralApproval> selectCswdYesAndCheXiao(@Param("search") String search, @Param("userId") Integer userId);

//    /**
//     * 查询管理员以及超级管理员的排名以及名词
//     */
//    List<Map> selectAdminGJ(@Param("isApprover") Integer isApprover);

//    /**
//     * 超级管理员的排名
//     */
//    List<Map> selectAdminAndSuperGJ();

    /**
     * 普通员工和管理员查询本部门当天数据排名
     */
//    List<Map> selectAdminAndEmpDayData(@Param("isApprover") Integer isApprover, @Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /**
     * 普通员工和管理员查询本部门当月数据排名
     */
//    List<Map> selectAdminAndEmpMonthData(@Param("isApprover") Integer isApprover, @Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);
//
    /**
     * 普通员工和管理员查询本部门当季数据排名
     */
////    List<Map> selectAdminAndEmpJiData(@Param("isApprover")Integer isApprover, @Param("deptIds")String deptIds,
//                                      @Param("postId")String postId, @Param("typeId")String typeId,
//                                      @Param("spTime1")String spTime1, @Param("spTime2")String spTime2,
//                                      @Param("search")String search);
    /**
     * 普通员工和管理员查询本部门当年数据排名
     */
//    List<Map> selectAdminAndEmpYearData(@Param("isApprover")Integer isApprover, @Param("deptIds")String deptIds,
//                                        @Param("postId")String postId, @Param("typeId")String typeId,
//                                        @Param("spTime1")String spTime1, @Param("spTime2")String spTime2,
//                                        @Param("search")String search);

    /** 超级管理员显示待我审批的数量*/
//    Integer selectSuperCount( @Param("userId") Integer userId);

    /** 管理员显示待我审批的数量 */
    Integer selectCountDwsp(@Param("userId")Integer userId);

    /** 根据当前用户部门id 在本部门的排名显示什么样的冠军 */
//    List<Map> selectAllGJ(@Param("isApprover")Integer isApprover  );

    /** 查询所有人员的排名 */
    List<Map> selectCommonGJ(@Param("deptId")Integer deptId);

    /** 查询审批不通 */
    List<App> selectAllListNo(@Param("userId") Integer userId, @Param("search")String search, @Param("status") Integer status);


    /** 普通用户 查询本部门所有的日志但不包括 经理，职能总监，总监级，总经理级 和总裁级  */
    List<AppLog> selectCommon(@Param("deptId") Integer deptId);


    /** 通用查询管理者所管理部门人员的所有信息 */
    List<AppLog>  managementAllDept(@Param("deptId") Integer deptId , @Param("roleKey") String roleKey);

    /** 超级管理员查询所有的日志 */
    List<AppLog> selectListAll();

    /** 普通员工查询本部门当天的数据排名  */
    List<Map> selectCommonDayData(@Param("commonDeptId") String commonDeptId, @Param("search")String search);

    /** 普通员工查询本部门当月的数据排名  */
    List<Map> selectCommonMonthData(@Param("commonDeptId") String commonDeptId, @Param("search")String search);

    /** 普通员工查询本部门当季的数据排名  */
    List<Map> selectCommonJiData(@Param("commonDeptId") String commonDeptId, @Param("search")String search);

    /** 普通员工查询本部门当年的数据排名  */
    List<Map> selectCommonYearData(@Param("commonDeptId") String commonDeptId, @Param("search")String search);

    /** 经理级审批 查询当天的数据 */
    List<Map> selectManagerDayData(@Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 经理级审批 查询当月的数据 */
    List<Map> selectManagerMonthData(@Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 经理级审批 查询当季的数据 */
    List<Map> selectManagerJiData(@Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 经理级审批 查询本部门当年数据*/
    List<Map> selectManagerYearData( @Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 总监级审批 查询本部门当日数据*/
    List<Map> selectDirectorDayData( @Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 总监级审批 查询本部门当月数据*/
    List<Map> selectDirectorMonthData(@Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 总监级审批 查询本部门当季数据*/
    List<Map> selectDirectorJiData(@Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 总监级审批 查询本部门当月数据 */
    List<Map> selectDirectorYearData(@Param("deptIds") String deptIds, @Param("postId") String postId, @Param("typeId") String typeId, @Param("spTime1") String spTime1, @Param("spTime2") String spTime2, @Param("search") String search);

    /** 统计总积分 */
    Long selectCountIntegral(@Param("userId")Integer userId);

    /** 个人中心Echarts显示数量  */
    Map selectAddAndCountDelIntegral(@Param("userId") Integer userId);

    int addxinxi(@Param("num") Integer num,@Param("Phone") String Phone,@Param("userId") Integer userId,@Param("shenPiRenIds") String shenPiRenIds,@Param("tiJiaoRenIds") String tiJiaoRenIds,@Param("tiJiaoId") Integer tiJiaoId,@Param("status") Integer status,@Param("integralTypeId") Integer integralTypeId,@Param("userName") String userName,@Param("userDeptId") Integer userDeptId);

    /**
     * 管理员查看 管理 奖扣 日志
     */
    List<IntegralApproval> selectgly(@Param("tiJiaoId") Integer tiJiaoId);

    /** 通过用户 审批日志  */
    Integer selectCountNum(@Param("userId") Integer userId);
}
