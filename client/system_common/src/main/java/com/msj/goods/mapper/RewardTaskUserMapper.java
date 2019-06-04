package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.RewardTaskUser;
import com.msj.goods.entity.TaskUserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务标题员工表 Mapper 接口
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
public interface RewardTaskUserMapper extends BaseMapper<RewardTaskUser> {

    /** 根据任务id查询所关联的用户 */
    List<TaskUserList> selectRTUList(@Param("rtId") String rtId);
}
