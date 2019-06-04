package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 积分项申请方式 关联 用户
 * </p>
 *
 * @author zhaoyan
 * @since 2019-05-29
 */
public class IntegralAppUser extends Model<IntegralAppUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分项关联用户 申请方式 主键
     */
    @TableId(value = "user_menu_id", type = IdType.AUTO)
    private Long userMenuId;

    /**
     * 用户主键
     */
    private Long userId;

    private Long menuId;

    /**
     * 申请方式(0.一天  1一周 2.一月 3.一年)
     */
    private String appType;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getUserMenuId() {
        return userMenuId;
    }

    public void setUserMenuId(Long userMenuId) {
        this.userMenuId = userMenuId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.userMenuId;
    }

    @Override
    public String toString() {
        return "IntegralAppUser{" +
        "userMenuId=" + userMenuId +
        ", userId=" + userId +
        ", menuId=" + menuId +
        ", appType=" + appType +
        ", createTime=" + createTime +
        "}";
    }
}
