package com.msj.goods.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.msj.goods.entity.IntegralLog;
import com.msj.goods.mapper.IntegralLogMapper;
import com.msj.goods.service.IntegralLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分日志表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class IntegralLogServiceImpl extends ServiceImpl<IntegralLogMapper, IntegralLog> implements IntegralLogService {
}
