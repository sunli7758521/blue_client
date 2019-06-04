package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msj.goods.entity.SysUser;
import com.msj.goods.entity.XwIntegral;
import com.msj.goods.entity.YjIntegral;
import com.msj.goods.mapper.XwIntegralMapper;
import com.msj.goods.mapper.YjIntegralMapper;
import com.msj.goods.service.YjIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业绩B积分管理 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class YjIntegralServiceImpl extends ServiceImpl<YjIntegralMapper, YjIntegral> implements YjIntegralService {

    @Autowired
    private XwIntegralMapper xwIntegralMapper;

    @Override
    public PageInfo<XwIntegral> selectDeclareResults(SysUser user,String pageSize,String pageNum,String search) {
        //分页
        PageHelper.startPage(Integer.parseInt(pageNum) ,Integer.parseInt(pageSize));

        Integer typeId = 2 ;
        List<XwIntegral> list = xwIntegralMapper.selectDeptIntegralParentId(user.getDeptId(),search,typeId);
        return new PageInfo<>(list);
    }
}
