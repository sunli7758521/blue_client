package com.msj.goods.service.impl;

import com.msj.goods.entity.GgTable;
import com.msj.goods.mapper.GgTableMapper;
import com.msj.goods.service.GgTableService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 广告图片 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-06
 */
@Service
@Transactional
public class GgTableServiceImpl extends ServiceImpl<GgTableMapper, GgTable> implements GgTableService {

}
