package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 积分奖扣表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralJk extends Model<IntegralJk> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "jk_id", type = IdType.AUTO)
    private Integer jkId;

    /**
     * 奖扣编号
     */
    private String jkNum;

    /**
     * 奖扣标题
     */
    private String jkTitle;

    /**
     * 奖扣员工姓名
     */
    private String jkName;

    /**
     * 奖扣人员姓名
     */
    private String jkPhone;

    /**
     * 奖励员工部门
     */
    private Integer deptId;

    /**
     * 奖励人员所属部门
     */
    private String deptName;

    /**
     * 奖扣员工头像
     */
    private String jkImg;

    /**
     * 奖励积分
     */
    private Integer jIntegral;

    /**
     * 扣除积分
     */
    private Integer kIntegral;

    /**
     * 奖扣时间
     */
    private Date jkTime;

    /**
     * 积分奖扣描述
     */
    private String jkDescribe;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String remark;

    /**
     * 奖励类型 abc
     */
    private Integer typeId;

    public Integer getJkId() {
        return jkId;
    }

    public void setJkId(Integer jkId) {
        this.jkId = jkId;
    }
    public String getJkNum() {
        return jkNum;
    }

    public void setJkNum(String jkNum) {
        this.jkNum = jkNum;
    }
    public String getJkTitle() {
        return jkTitle;
    }

    public void setJkTitle(String jkTitle) {
        this.jkTitle = jkTitle;
    }
    public String getJkName() {
        return jkName;
    }

    public void setJkName(String jkName) {
        this.jkName = jkName;
    }
    public String getJkPhone() {
        return jkPhone;
    }

    public void setJkPhone(String jkPhone) {
        this.jkPhone = jkPhone;
    }
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getJkImg() {
        return jkImg;
    }

    public void setJkImg(String jkImg) {
        this.jkImg = jkImg;
    }
    public Integer getjIntegral() {
        return jIntegral;
    }

    public void setjIntegral(Integer jIntegral) {
        this.jIntegral = jIntegral;
    }
    public Integer getkIntegral() {
        return kIntegral;
    }

    public void setkIntegral(Integer kIntegral) {
        this.kIntegral = kIntegral;
    }
    public Date getJkTime() {
        return jkTime;
    }

    public void setJkTime(Date jkTime) {
        this.jkTime = jkTime;
    }
    public String getJkDescribe() {
        return jkDescribe;
    }

    public void setJkDescribe(String jkDescribe) {
        this.jkDescribe = jkDescribe;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.jkId;
    }

    @Override
    public String toString() {
        return "IntegralJk{" +
        "jkId=" + jkId +
        ", jkNum=" + jkNum +
        ", jkTitle=" + jkTitle +
        ", jkName=" + jkName +
        ", jkPhone=" + jkPhone +
        ", deptId=" + deptId +
        ", deptName=" + deptName +
        ", jkImg=" + jkImg +
        ", jIntegral=" + jIntegral +
        ", kIntegral=" + kIntegral +
        ", jkTime=" + jkTime +
        ", jkDescribe=" + jkDescribe +
        ", status=" + status +
        ", remark=" + remark +
        ", typeId=" + typeId +
        "}";
    }
}
