package com.msj.goods.service.impl;

import com.msj.goods.entity.SysJobLog;
import com.msj.goods.mapper.SysJobLogMapper;
import com.msj.goods.service.SysJobLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

}
