package com.msj.goods.mapper;

import com.msj.goods.entity.ChaoSong;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 抄送表 Mapper 接口
 * </p>
 *
 * @author sun li
 * @since 2018-12-20
 */
public interface ChaoSongMapper extends BaseMapper<ChaoSong> {
    /** 查询有多少人给自己抄送未审批的数量*/
    Integer selectNotAppCount(@Param("userId") Integer userId);
}
