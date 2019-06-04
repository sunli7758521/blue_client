package com.msj.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AppLog {

    private Integer approvalId;
    /**
     * 审批标题
     */
    private String approvalTitle;

    /**
     * 审批内容
     */
    private String approvalContent;

    /**
     * 关联用户id
     */
    private Integer userId;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 员工头像
     */
    private String userImg;

    /**
     * 积分类型
     */
    private Integer integralTypeId;

    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date sqTime;

    /**
     * 审批时间
     */
    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private Date spTime;

    /**
     * 审批状态(0,审批中 1审批通过，2审批不通过，3撤销审批 )
     */
    private Integer status;

    /**
     * 申请积分
     */
    private Long sqIntegral;
    /**
     * 上传图片1
     */
    private String approvalImg1;

    /**
     * 扣除积分
     */
    private Integer kIntegral;
    /**
     *  角色 唯一标识
     */
    private  String roleKey;

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalTitle() {
        return approvalTitle;
    }

    public void setApprovalTitle(String approvalTitle) {
        this.approvalTitle = approvalTitle;
    }

    public String getApprovalContent() {
        return approvalContent;
    }

    public void setApprovalContent(String approvalContent) {
        this.approvalContent = approvalContent;
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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Integer getIntegralTypeId() {
        return integralTypeId;
    }

    public void setIntegralTypeId(Integer integralTypeId) {
        this.integralTypeId = integralTypeId;
    }

    public Date getSqTime() {
        return sqTime;
    }

    public void setSqTime(Date sqTime) {
        this.sqTime = sqTime;
    }

    public Date getSpTime() {
        return spTime;
    }

    public void setSpTime(Date spTime) {
        this.spTime = spTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSqIntegral() {
        return sqIntegral;
    }

    public void setSqIntegral(Long sqIntegral) {
        this.sqIntegral = sqIntegral;
    }

    public String getApprovalImg1() {
        return approvalImg1;
    }

    public void setApprovalImg1(String approvalImg1) {
        this.approvalImg1 = approvalImg1;
    }

    public Integer getkIntegral() {
        return kIntegral;
    }

    public void setkIntegral(Integer kIntegral) {
        this.kIntegral = kIntegral;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
}
