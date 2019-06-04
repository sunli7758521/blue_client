package com.msj.goods.service.impl;

import com.msj.goods.entity.ChaoSong;
import com.msj.goods.mapper.ChaoSongMapper;
import com.msj.goods.service.ChaoSongService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 抄送表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-12-20
 */
@Service
@Transactional
public class ChaoSongServiceImpl extends ServiceImpl<ChaoSongMapper, ChaoSong> implements ChaoSongService {


    /** 查询有多少人给自己抄送未审批的数量*/
    @Override
    public Integer selectNotAppCount(Integer userId) {
        return this.baseMapper.selectNotAppCount(userId);
    }
}
