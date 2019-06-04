package com.msj.goods.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 审批流程人员设置
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralSplc extends Model<IntegralSplc> {

    private static final long serialVersionUID = 1L;

    /**
     * 审批流程 主键
     */
    @TableId(value = "lc_id", type = IdType.AUTO)
    private Integer lcId;

    /**
     * 审批流程名称
     */
    private String spName;

    /**
     * 部门关联的id
     */
    private Integer deptId;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    public Integer getLcId() {
        return lcId;
    }

    public void setLcId(Integer lcId) {
        this.lcId = lcId;
    }
    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }
    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.lcId;
    }

    @Override
    public String toString() {
        return "IntegralSplc{" +
        "lcId=" + lcId +
        ", spName=" + spName +
        ", deptId=" + deptId +
        ", creatTime=" + creatTime +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
