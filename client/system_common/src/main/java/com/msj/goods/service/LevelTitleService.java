package com.msj.goods.service;

import com.msj.goods.entity.LevelTitle;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 水平考核题目表 服务类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-21
 */
public interface LevelTitleService extends IService<LevelTitle> {
    /** 查询出来 全部的科目考试内容表*/

    PageInfo <LevelTitle> selectByLevelId(Integer levelId,String pageSize, String pageNum);

    LevelTitle  leveltitleshuai(Integer tId);
}
