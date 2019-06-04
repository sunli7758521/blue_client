package com.msj.goods.service.impl;

import com.msj.goods.entity.Gzd;
import com.msj.goods.mapper.GzdMapper;
import com.msj.goods.service.GzdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 工作台应用管理 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
@Transactional
public class GzdServiceImpl extends ServiceImpl<GzdMapper, Gzd> implements GzdService {

}
