package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.LevelTitle;
import com.msj.goods.mapper.LevelTitleMapper;
import com.msj.goods.service.LevelTitleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 水平考核题目表 服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-21
 */
@Service
@Transactional
public class LevelTitleServiceImpl extends ServiceImpl<LevelTitleMapper, LevelTitle> implements LevelTitleService {

    @Override
    public PageInfo<LevelTitle> selectByLevelId(Integer levelId,String pageSize,String pageNum) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
       /* EntityWrapper<LevelTitle> ew = new  EntityWrapper<LevelTitle>();
        ew.eq("level_id",levelId);*/

        List<LevelTitle> level= this.baseMapper.selecttitle(levelId);

        return new PageInfo<>(level);
    }

    @Override
    public LevelTitle leveltitleshuai(Integer tId) {
        //分页
     /*   PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));*/
       /* EntityWrapper<LevelTitle> ew = new  EntityWrapper<LevelTitle>();
        ew.eq("level_id",levelId);*/

      /*  List<LevelTitle> level= this.baseMapper.selecttitle(levelId);*/
        LevelTitle a= this.baseMapper.leveltitleku(tId);

        return a;
    }


}
