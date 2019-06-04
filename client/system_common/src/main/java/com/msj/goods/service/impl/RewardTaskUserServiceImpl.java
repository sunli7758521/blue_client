package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msj.goods.entity.RewardTaskUser;
import com.msj.goods.entity.TaskUserList;
import com.msj.goods.mapper.RewardTaskUserMapper;
import com.msj.goods.service.RewardTaskUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务标题员工表 服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-01-22
 */
@Service
public class RewardTaskUserServiceImpl extends ServiceImpl<RewardTaskUserMapper, RewardTaskUser> implements RewardTaskUserService {

    /** 根据任务id查询所关联的用户 */
    @Override
    public List<TaskUserList> selectRTUList(String rtId) {
        return this.baseMapper.selectRTUList(rtId);
    }
}
