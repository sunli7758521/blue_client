package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.XwIntegral;
import com.msj.goods.mapper.XwIntegralMapper;
import com.msj.goods.service.XwIntegralService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行为c积分管理 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class XwIntegralServiceImpl extends ServiceImpl<XwIntegralMapper, XwIntegral> implements XwIntegralService {


    /**
     *  查询个人部门下所有的行为积分项 以及公共的菜单项
     */
    @Override
    public PageInfo<XwIntegral> selectDeptIntegral(SysUser user,String pageSize,String pageNum,String search) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));
        Integer typeId =3;
        List<XwIntegral>  list = this.baseMapper.selectDeptIntegralParentId(user.getDeptId(),search,typeId);

        return new PageInfo<>(list);
    }
}
