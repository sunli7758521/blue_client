package com.msj.goods.service.impl;

import com.msj.goods.entity.SysPost;
import com.msj.goods.mapper.SysPostMapper;
import com.msj.goods.service.SysPostService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 岗位信息表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {

    /**
     *  查询所有职位
     * */
    @Override
    public List<Map> selectPostList() {
        return this.baseMapper.selectPostList();
    }

    /** 经理 级别 查询所有的职位  */
    @Override
    public List<Map> selectManagerPostList() {
        return this.baseMapper.selectManagerPostList();
    }

    /** 总监 级别 查询所有的职位  */
    @Override
    public List<Map> selectDirectorPostList() {
        return this.baseMapper.selectDirectorPostList();
    }

    /** 总经理 级别 查询所有的职位  */
    @Override
    public List<Map> selectGeneralManagePostList() {
        return this.baseMapper.selectGeneralManagePostList();
    }

    /** 职能总监 级别 查询所有的职位  */
    @Override
    public List<Map> selectFunctionPostList() {
        return this.baseMapper.selectFunctionPostList();
    }
}
