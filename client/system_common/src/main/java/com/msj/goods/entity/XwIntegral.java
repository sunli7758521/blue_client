package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 行为c积分管理
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class XwIntegral extends Model<XwIntegral> {

    private static final long serialVersionUID = 1L;

    /**
     * 行为积分主键
     */
    @TableId(value = "behavior_id", type = IdType.AUTO)
    private Integer behaviorId;

    /**
     * 类别
     */
    private String behaviorCategory;

    /**
     * 行为标题
     */
    private String behaviorTitle;

    /**
     * 行为内容
     */
    private String behaviorContent;

    /**
     * 申请方式(1.一天  2一周 3.一月)
     */
    private String shenQingFangShi;

    /**
     * 类型
     */
    private Integer typeId;

    /**
     * 最多奖励
     */
    private String zuiDuoIntegral;

    /**
     * 最少奖励积分
     */
    private String zuiShaoIntegral;

    /**
     * 积分分级
     */
    private Integer integralFenJi;

    /**
     * 已完成次数
     */
    private Integer yiWanChengCiShu;

    /**
     * 使用转态 0,使用中  1.禁用   2 设置审核人
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 职位名称
     */
    private Integer postId;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     * 积分餐单id
     */
    private Integer menuId;

    /**
     * 备注
     */
    private String remark;

    /** 职位名称 */
    private String postName;
    /** 部门名称 */
    private String deptName;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Integer behaviorId) {
        this.behaviorId = behaviorId;
    }
    public String getBehaviorCategory() {
        return behaviorCategory;
    }

    public void setBehaviorCategory(String behaviorCategory) {
        this.behaviorCategory = behaviorCategory;
    }
    public String getBehaviorTitle() {
        return behaviorTitle;
    }

    public void setBehaviorTitle(String behaviorTitle) {
        this.behaviorTitle = behaviorTitle;
    }
    public String getBehaviorContent() {
        return behaviorContent;
    }

    public void setBehaviorContent(String behaviorContent) {
        this.behaviorContent = behaviorContent;
    }
    public String getShenQingFangShi() {
        return shenQingFangShi;
    }

    public void setShenQingFangShi(String shenQingFangShi) {
        this.shenQingFangShi = shenQingFangShi;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getZuiDuoIntegral() {
        return zuiDuoIntegral;
    }

    public void setZuiDuoIntegral(String zuiDuoIntegral) {
        this.zuiDuoIntegral = zuiDuoIntegral;
    }
    public String getZuiShaoIntegral() {
        return zuiShaoIntegral;
    }

    public void setZuiShaoIntegral(String zuiShaoIntegral) {
        this.zuiShaoIntegral = zuiShaoIntegral;
    }
    public Integer getIntegralFenJi() {
        return integralFenJi;
    }

    public void setIntegralFenJi(Integer integralFenJi) {
        this.integralFenJi = integralFenJi;
    }
    public Integer getYiWanChengCiShu() {
        return yiWanChengCiShu;
    }

    public void setYiWanChengCiShu(Integer yiWanChengCiShu) {
        this.yiWanChengCiShu = yiWanChengCiShu;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.behaviorId;
    }

    @Override
    public String toString() {
        return "XwIntegral{" +
        "behaviorId=" + behaviorId +
        ", behaviorCategory=" + behaviorCategory +
        ", behaviorTitle=" + behaviorTitle +
        ", behaviorContent=" + behaviorContent +
        ", shenQingFangShi=" + shenQingFangShi +
        ", typeId=" + typeId +
        ", zuiDuoIntegral=" + zuiDuoIntegral +
        ", zuiShaoIntegral=" + zuiShaoIntegral +
        ", integralFenJi=" + integralFenJi +
        ", yiWanChengCiShu=" + yiWanChengCiShu +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", postId=" + postId +
        ", deptId=" + deptId +
        ", menuId=" + menuId +
        ", remark=" + remark +
        ", deptName=" + deptName +
        ", postName=" + postName +
        "}";
    }
}
