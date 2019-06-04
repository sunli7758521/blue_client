package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.common.utils.PageBean;
import com.msj.goods.entity.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 审批管理 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface IntegralApprovalService extends IService<IntegralApproval> {

    /**
     *  积分榜查看一个人所的日志列表
     * */
    PageInfo<IntegralApproval> selectgly(Integer tiJiaoId, String pageSize, String pageNum);




    /**
     * 添加一条水平考核信息
     * @return IntegralApproval
     *
     * */
    int addxinxi(Integer num,String Phone,Integer userId,String shenPiRenIds, String tiJiaoRenIds,Integer tiJiaoId, Integer status, Integer integralTypeId, String userName, Integer userDeptId);


    /**
     * 查询冠军
     * @return IntegralApproval
     *
     * */
    List<Map> selectGJ(Integer deptIds);

    /**
     * 首页显示所有用户的信息
     * @param
     * @param deptId
     */
    PageInfo<AppLog> selectOneIntegralApproval(SysUser user,  String pageSize, String pageNum);

    /**
     *  查询审批日志列表
     * @param  pageSize, pageNum
     */
    PageInfo<IntegralApproval> selectIntegralApprovalLog(SysUser user,String pageSize,String pageNum);

    /**
     *  查询有多少人给自己抄送数量
     */
    Integer selectCsrs(Integer userId);

    /**
     *  查看审批日志列表
     * @param  pageSize, pageNum
     */
    PageInfo<IntegralApproval> selectApproverLog(SysUser user,String pageSize,String pageNum);

    /**
     *  查询我发起的列表日志
     * @param  pageSize, pageNum
     */
    PageInfo<IntegralApproval> selectMyFq(SysUser user,Integer pageSize,Integer pageNum,String status,String search);


    /**
     *  抄送我的未审核列表
     * @param  pageSize, pageNum
     */
    PageInfo<IntegralApproval> selectCswdList(SysUser user,String pageSize,String pageNum,String search,String status);

    /**
     *  积分申诉通过列表
     * @param  pageSize, pageNum
     */
    PageInfo<IntegralApproval> selectIntegralListYes(SysUser user,String pageSize,String pageNum,Integer time);

    /**
     *  往审批管理添加待审批记录
     */
    boolean insertIntegralApprover(SysUser sysUser,String addIntegral, String spRemark, String typeId, String[] from, String[] to, String[] approvalImg1, String approvalContent, String approvalTitle, String approvalId, String[] apps);

    /**
     *   查询待我审批和已审批列表
     */
    PageInfo<App> selectApproversPel(SysUser user, Integer pageSize, Integer pageNum, Integer status, String search);

    /**
     *  管理员自由奖扣
     */
    boolean freeIntegral(SysUser sysUser,String addIntegral,String delIntegral, String spRemark, String typeId, String[] from,  String[] approvalImg1, String approvalContent, String approvalTitle,String dateTime);

    /**
     * 自由奖扣申请
     */
    boolean freeIntegralApprover(SysUser user,String addIntegral, String delIntegral, String spRemark, String typeId, String[] from, String[] to, String[] approvalImg1, String approvalContent, String approvalTitle, String approvalId, String[] apps);

    /**
     * 申请  通过、 不通过、撤销、
     */
    int approversYesNo(String approvalId, String status,String keyWord,String app);



    /** 管理员显示待我审批的数量 */
    Integer selectCountDwsp(Integer userId);

    /**
     *   领导表扬加分  from 给员工申请
     */
    boolean leaderIntegral(SysUser user,String approvalContent, String approvalTitle, String typeId,  String[] approvalImg11, String addIntegral, String spRemark, String[] from,String dateTime);

    /**
     *  积分榜查看一个人所的日志列表
     * */
    PageInfo<IntegralApproval> selectPersonalList(String userId, String pageSize, String pageNum);

  /*  *//** 根据当前用户部门id 查询他是什么样的级别审批  显示什么样的冠军 *//*
    List<Map> selectAllGJ(Integer roleId);*/

    /** 根据当前用户部门id 查询他是什么样的级别审批  显示什么样的冠军 */
    List<Map> selectCommonGJ(Integer deptId);


    /**
     *   添加一条 挑战任务
     */
    boolean addChallenges(String taskId, String integralTypeId, String appWay, String releaseUserId, String[] pic, String[] to, SysUser user,String remark);

    /** 统计总积分 */
    Long selectCountIntegral(Integer userId);

    /** 个人中心Echarts显示数量  */
    Map selectAddAndCountDelIntegral(Integer userId);

    /**
     *  积分申诉  审批通过 、撤销审批、审批不通过详情页
     */
    IntegralApproval selectComplaintDetails(String approvalId);

    /**
     *  积分申诉  添加提交一条审批不通过 或者 撤销审批
     */
    boolean addComplaint(String approvalId, String appReason, String appPelId, String[] pic, String[] to, SysUser user);

    /** 通过用户 审批日志  */
    Integer selectCountNum(Integer userId);
}
