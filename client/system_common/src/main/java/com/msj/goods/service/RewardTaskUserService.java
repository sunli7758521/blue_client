package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.msj.goods.entity.RewardTaskUser;
import com.msj.goods.entity.TaskUserList;

import java.util.List;

/**
 * <p>
 * 任务标题员工表 服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
public interface RewardTaskUserService extends IService<RewardTaskUser> {

    /** 根据任务id查询所关联的用户 */
    List<TaskUserList> selectRTUList(String rtId);
}
