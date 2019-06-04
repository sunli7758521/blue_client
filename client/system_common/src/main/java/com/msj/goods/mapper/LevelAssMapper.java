package com.msj.goods.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.msj.goods.entity.LevelAss;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 水平考核表 Mapper 接口
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-20
 */
public interface LevelAssMapper extends BaseMapper<LevelAss> {
    /** 查询出来 全部的科目表*/

    List<LevelAss> selectLevelassList(@Param("deptIds") Integer deptIds );

    LevelAss selectLevelAssid(@Param("levelId") Integer levelId );

    public int insertLevelAss(@Param("levelId") Integer levelId,@Param("numberid") Integer numberid);

}
