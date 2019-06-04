package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.LevelAss;

/**
 * <p>
 * 水平考核表 服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-20
 */
public interface LevelAssService extends IService<LevelAss> {


    /** 查询出来 全部的科目表*/

    PageInfo <LevelAss> selectLevelAssList(Integer deptIds, String pageSize, String pageNum);


    LevelAss selectLevelAssid(Integer levelId);

    public int insertLevelAss(Integer  levelId, Integer  numberid );


}
