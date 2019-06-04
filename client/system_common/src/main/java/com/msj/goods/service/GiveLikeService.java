package com.msj.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.msj.goods.entity.GiveLike;
import com.msj.goods.entity.SysUser;

import java.util.List;

/**
 * 员工爱心点赞表 服务类
 *
 * @author sun li
 * @since 2018-12-19
 */
public interface GiveLikeService extends IService<GiveLike> {

    /**
     * * 按照部门查询用户
     *
     * @param deptList 部门集合
     * @param dept     当前用户所在部门
     * @param userId   当前用户除外
     * @param userName 用户名
     * @return
     */
    List<SysUser> findUserAll(SysUser user, String userName);

    /**
     * 爱心点赞信息保存到积分审批表
     *
     * @param userId
     */
    void saveIntegralApp(String userId);

    /**
     * 当前用户获取本周已点赞的用户ID
     *
     * @param userId 当前用户ID
     * @return 返回已点赞用户ID
     */
    Integer getTswkPraiseUsers(Integer userId);

    /**
     * 被点赞用户，查询点赞用户信息
     *
     * @param userId
     * @return
     */
    String lovePraiseLogList(Integer userId);
}
