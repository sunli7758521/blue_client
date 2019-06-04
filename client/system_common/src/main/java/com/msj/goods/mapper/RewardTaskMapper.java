package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.RewardTask;
import com.msj.goods.entity.TashAndUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 悬赏任务表 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-12-25
 */
public interface RewardTaskMapper extends BaseMapper<RewardTask> {
    /**
     *  日常任务 抢单任务 挑战任务
     */
    List<TashAndUser> selectTask(@Param("deptId") Integer deptId, @Param("times")Integer times, @Param("roleId") String roleId);
     /**
     *   悬赏任务 显示数量  抢单，挑战
     */
    Integer selectCountTaskDeptNum(@Param("deptId") Integer deptId,@Param("roleId") String roleId);

    /**
     *  通过部门id 关联查询发布任务的状态
     */
    RewardTask selectTaskStatus(@Param("deptId") Integer deptId);

    /**
     *
     *   悬赏任务 查询每天最新发布的日常任务
     */
    List<TashAndUser> selectDayTask(@Param("deptId") Integer deptId, @Param("times") Integer times, @Param("roleId") String roleId);
}
