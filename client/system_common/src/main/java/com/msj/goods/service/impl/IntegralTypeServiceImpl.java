package com.msj.goods.service.impl;

import com.msj.goods.entity.IntegralType;
import com.msj.goods.mapper.IntegralTypeMapper;
import com.msj.goods.service.IntegralTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 积分类别表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class IntegralTypeServiceImpl extends ServiceImpl<IntegralTypeMapper, IntegralType> implements IntegralTypeService {

    /**
     * 查询所有积分类型
     * */
    @Override
    public List<Map> selectIntegralTypeList() {
        return this.baseMapper.selectIntegralTypeList();
    }
}
