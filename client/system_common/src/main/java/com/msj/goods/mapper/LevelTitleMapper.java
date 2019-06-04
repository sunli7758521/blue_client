package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.LevelTitle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 水平考核题目表 Mapper 接口
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-21
 */
public interface LevelTitleMapper extends BaseMapper<LevelTitle> {



   List<LevelTitle> selecttitle(@Param("levelId") Integer levelId);
    LevelTitle leveltitleku(@Param("tId") Integer tId);
}
