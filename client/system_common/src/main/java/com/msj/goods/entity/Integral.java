package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 积分表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class Integral extends Model<Integral> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分主键
     */
    @TableId(value = "integral_id", type = IdType.AUTO)
    private Integer integralId;

    /**
     * 关联那个员工
     */
    private Integer userId;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工手机号
     */
    private String userPhone;

    /**
     * 总积分
     */
    private Integer countIntegral;

    /**
     * 扣除积分
     */
    private Integer delIntegral;

    /**
     * 奖励积分
     */
    private Integer addIntegral;

    /**
     * 类型
     */
    private Integer typeId;

    /**
     * 职位id
     */
    private Integer postId;

    /**
     * 部门id
     */
    private Integer deptId;

    /**
     *  商城显示显示总积分
     */
    private  Integer goodCountIntegral;

    public Integer getGoodCountIntegral() {
        return goodCountIntegral;
    }

    public void setGoodCountIntegral(Integer goodCountIntegral) {
        this.goodCountIntegral = goodCountIntegral;
    }

    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public Integer getCountIntegral() {
        return countIntegral;
    }

    public void setCountIntegral(Integer countIntegral) {
        this.countIntegral = countIntegral;
    }
    public Integer getDelIntegral() {
        return delIntegral;
    }

    public void setDelIntegral(Integer delIntegral) {
        this.delIntegral = delIntegral;
    }
    public Integer getAddIntegral() {
        return addIntegral;
    }

    public void setAddIntegral(Integer addIntegral) {
        this.addIntegral = addIntegral;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    @Override
    protected Serializable pkVal() {
        return this.integralId;
    }

    @Override
    public String toString() {
        return "Integral{" +
        "integralId=" + integralId +
        ", userId=" + userId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", countIntegral=" + countIntegral +
        ", delIntegral=" + delIntegral +
        ", addIntegral=" + addIntegral +
        ", typeId=" + typeId +
        ", postId=" + postId +
        ", deptId=" + deptId +
        ", goodCountIntegral=" + goodCountIntegral +
        "}";
    }
}
