package com.msj.goods.service;

import com.msj.goods.entity.SysPost;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 岗位信息表 服务类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public interface SysPostService extends IService<SysPost> {

    /**
     *  查询所有职位
     * */
    List<Map> selectPostList();

    /** 经理 级别 查询所有的职位  */
    List<Map> selectManagerPostList();

    /** 总监 级别 查询所有的职位  */
    List<Map> selectDirectorPostList();

    /** 总经理 级别 查询所有的职位  */
    List<Map> selectGeneralManagePostList();

    /** 职能总监 级别 查询所有的职位  */
    List<Map> selectFunctionPostList();
}
