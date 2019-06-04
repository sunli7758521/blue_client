package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户登录记录
 * </p>
 *
 * @author zhaoyan
 * @since 2019-05-21
 */
public class UserLoginLog extends Model<UserLoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户登录日志记录 主键
     */
    @TableId(value = "user_log_id", type = IdType.AUTO)
    private Long userLogId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户登录时间
     */
    private Date userLoginTime;

    public Long getUserLogId() {
        return userLogId;
    }

    public void setUserLogId(Long userLogId) {
        this.userLogId = userLogId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Date getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(Date userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.userLogId;
    }

    @Override
    public String toString() {
        return "UserLoginLog{" +
        "userLogId=" + userLogId +
        ", userId=" + userId +
        ", userLoginTime=" + userLoginTime +
        "}";
    }
}
