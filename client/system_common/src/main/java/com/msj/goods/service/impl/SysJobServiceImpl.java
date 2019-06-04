package com.msj.goods.service.impl;

import com.msj.goods.entity.SysJob;
import com.msj.goods.mapper.SysJobMapper;
import com.msj.goods.service.SysJobService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

}
