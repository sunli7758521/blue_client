package com.msj.goods.service;

import com.msj.goods.entity.ChaoSong;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 抄送表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-12-20
 */
public interface ChaoSongService extends IService<ChaoSong> {
     /** 查询有多少人给自己抄送未审批的数量*/
    Integer selectNotAppCount(Integer userId);
}
