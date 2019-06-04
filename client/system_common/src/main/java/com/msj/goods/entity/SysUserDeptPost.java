package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户属于那个部门管理那个部门
 * </p>
 *
 * @author sun li
 * @since 2018-12-30
 */
public class SysUserDeptPost extends Model<SysUserDeptPost> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ud_id", type = IdType.AUTO)
    private Integer udId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getUdId() {
        return udId;
    }

    public void setUdId(Integer udId) {
        this.udId = udId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.udId;
    }

    @Override
    public String toString() {
        return "SysUserDeptPost{" +
        "udId=" + udId +
        ", userId=" + userId +
        ", deptId=" + deptId +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
