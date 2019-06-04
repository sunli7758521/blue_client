package com.msj.goods.service.impl;

import com.msj.goods.entity.UserLoginLog;
import com.msj.goods.mapper.UserLoginLogMapper;
import com.msj.goods.service.UserLoginLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录 服务实现类
 * </p>
 *
 * @author zhaoyan
 * @since 2019-05-21
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
