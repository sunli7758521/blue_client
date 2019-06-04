package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.LevelAss;
import com.msj.goods.mapper.LevelAssMapper;
import com.msj.goods.service.LevelAssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 水平考核表 服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-20
 */
@Service
@Transactional
public class LevelAssServiceImpl extends ServiceImpl<LevelAssMapper, LevelAss> implements LevelAssService {


    @Autowired
    private LevelAssMapper  levelAssMapper;


    @Override
    public PageInfo<LevelAss> selectLevelAssList( Integer deptIds,  String pageSize,String pageNum) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        /*EntityWrapper<LevelAss> ew = new  EntityWrapper<LevelAss>();*/
        List<LevelAss> level=levelAssMapper.selectLevelassList( deptIds);

        //   List<LevelAss> level= this.baseMapper.selectLevelAssList();
        return new PageInfo<>(level);
    }


    @Override
    public LevelAss selectLevelAssid( Integer levelId) {
        //分页
       /* PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));*/
        /*EntityWrapper<LevelAss> ew = new  EntityWrapper<LevelAss>();*/
        //   List<LevelAss> level= this.baseMapper.selectLevelAssList();
        return levelAssMapper.selectLevelAssid( levelId);
    }


    @Override
    public int insertLevelAss(Integer  levelId, Integer  numberid)
    {

        return levelAssMapper.insertLevelAss(levelId,numberid);
    }
}
