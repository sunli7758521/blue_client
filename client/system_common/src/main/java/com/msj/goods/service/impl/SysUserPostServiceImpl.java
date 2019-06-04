package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.msj.goods.entity.SysUserPost;
import com.msj.goods.mapper.SysUserPostMapper;
import com.msj.goods.service.SysUserPostService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements SysUserPostService {

    @Override
    public SysUserPost findByUserId(Integer userId) {
        if(userId==null){
            return null;
        }
        /**  通过用户id 查询职位添加职位id*/
        EntityWrapper<SysUserPost> ew = new EntityWrapper<SysUserPost>();
        ew.eq("user_id",userId);
        return this.selectOne(ew);
    }
}
