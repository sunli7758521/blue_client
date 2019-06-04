package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 水平考核表
 * </p>
 *
 * @author zhaoyan
 * @since 2019-03-20
 */
public class LevelAss extends Model<LevelAss> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "level_id", type = IdType.AUTO)
    private Integer levelId;

    /**
     * 考核名称
     */
    private String assName;

    /**
     * 部门ids
     */
    private String deptIds;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 0 使用中  1 禁用
     */
    private String state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 申请方式
     */
    private String shenQingFangShi;

    /**
     * 用户id  谁发题谁的id
     */
    private Long userIds;
        /**时间*/
    private Date carateTime;
     /** 部门表 */
     private SysDept sysDept;
     /** 用户表*/
     private SysUser sysyuser;

     /** 添加考核的个数*/
     private  Integer numberid;

    public Integer getNumberid() {
        return numberid;
    }

    public void setNumberid(Integer numberid) {
        this.numberid = numberid;
    }

    public Long getUserIds() {
        return userIds;
    }

    public void setUserIds(Long userIds) {
        this.userIds = userIds;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public SysUser getSysyuser() {
        return sysyuser;
    }

    public void setSysyuser(SysUser sysyuser) {
        this.sysyuser = sysyuser;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
    public String getAssName() {
        return assName;
    }

    public void setAssName(String assName) {
        this.assName = assName;
    }
    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getShenQingFangShi() {
        return shenQingFangShi;
    }

    public void setShenQingFangShi(String shenQingFangShi) {
        this.shenQingFangShi = shenQingFangShi;
    }

    public Date getCarateTime() {
        return carateTime;
    }

    public void setCarateTime(Date carateTime) {
        this.carateTime = carateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.levelId;
    }

    @Override
    public String toString() {
        return "LevelAss{" +
        "levelId=" + levelId +
        ", assName=" + assName +
        ", deptIds=" + deptIds +
        ", typeId=" + typeId +
        ", state=" + state +
        ", remark=" + remark +
        ", shenQingFangShi=" + shenQingFangShi +
        ", userIds=" + userIds +
        ", carateTime=" + carateTime +
                ", sysDept=" + sysDept +
                ", sysyuser=" + sysyuser +
                ", numberid=" + numberid +
        "}";
    }
}
