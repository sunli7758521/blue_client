package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.RewardTask;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.TashAndUser;

/**
 * 悬赏任务表 服务类
 *
 * @author sun li
 * @since 2018-12-25
 */
public interface RewardTaskService extends IService<RewardTask> {

    /**
     * 日常任务列表
     */
    PageInfo<TashAndUser> selectDayTask(
            Integer pageSize, Integer pageNum, Integer times, SysUser user);

    /**
     * 保存发布任务
     */
    Integer saveTaskUserAndDept(Integer rtId, String depId, String roleIds, Integer deptId);

    /**
     * 悬赏任务 显示数量 日常任务，抢单，挑战
     */
    Integer selectCountTaskDeptNum(Integer deptId,String roleId);

   /* *//**
     * 通过部门id 关联查询发布任务的状态
     *//*
    RewardTask selectTaskStatus(Integer deptId);*/
}
