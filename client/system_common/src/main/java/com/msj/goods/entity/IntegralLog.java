package com.msj.goods.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 积分日志表
 * </p>
 *
 * @author sun li
 * @since 2018-11-05
 */
public class IntegralLog extends Model<IntegralLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 积分日志
     */
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    /**
     * 审批编号
     */
    private String approvalNum;

    /**
     * 关联用户id
     */
    private Integer userId;

    /**
     * 积分表的主键
     */
    private Integer integralId;

    /**
     * 员工手机号
     */
    private Long userPhone;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工头像
     */
    private String userImg;

    /**
     * 员工部门
     */
    private String userDept;

    /**
     * 员工职位
     */
    private String userPost;

    /**
     * 申请积分项目标题
     */
    private String integralTitle;

    /**
     * 积分内容
     */
    private String integralContent;

    /**
     * 变动积分(所为刚才加的积分)
     */
    private Integer bianIntegral;

    /**
     * 积分类型
     */
    private Integer typeId;

    /**
     * 获取积分时间
     */
    private Date getTime;

    /**
     * 转态 （0正常    1.撤销刚才所加的积分 2.管理员添加积分）
     */
    private Integer status;

    /**
     * 扣除积分
     */
    private String remark;

    /**
     * 扣除积分
     */
    private Integer kIntegral;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(String approvalNum) {
        this.approvalNum = approvalNum;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }
    public String getUserName() {
        return userName;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }
    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }
    public String getIntegralTitle() {
        return integralTitle;
    }

    public void setIntegralTitle(String integralTitle) {
        this.integralTitle = integralTitle;
    }
    public String getIntegralContent() {
        return integralContent;
    }

    public void setIntegralContent(String integralContent) {
        this.integralContent = integralContent;
    }
    public Integer getBianIntegral() {
        return bianIntegral;
    }

    public void setBianIntegral(Integer bianIntegral) {
        this.bianIntegral = bianIntegral;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
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
    public Integer getkIntegral() {
        return kIntegral;
    }

    public void setkIntegral(Integer kIntegral) {
        this.kIntegral = kIntegral;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "IntegralLog{" +
        "logId=" + logId +
        ", approvalNum=" + approvalNum +
        ", userId=" + userId +
        ", integralId=" + integralId +
        ", userPhone=" + userPhone +
        ", userName=" + userName +
        ", userImg=" + userImg +
        ", userDept=" + userDept +
        ", userPost=" + userPost +
        ", integralTitle=" + integralTitle +
        ", integralContent=" + integralContent +
        ", bianIntegral=" + bianIntegral +
        ", typeId=" + typeId +
        ", getTime=" + getTime +
        ", status=" + status +
        ", remark=" + remark +
        ", kIntegral=" + kIntegral +
        "}";
    }
}
