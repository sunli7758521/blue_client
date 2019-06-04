package com.msj.goods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

public class App {

    private String approvalId;
    private String approvalTitle;
    private String approvalContent;
    private String userName;
    private String userImg;
    private String integralTypeId;

    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private String sqTime;

    @JsonFormat(pattern="yyyy/MM/dd HH/mm/ss",timezone = "GMT+8")
    private String spTime;
    private String sqIntegral;
    private String status;
    private String approvalImg1;
    private String kIntegral;

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
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

    public String getIntegralTypeId() {
        return integralTypeId;
    }

    public void setIntegralTypeId(String integralTypeId) {
        this.integralTypeId = integralTypeId;
    }

    public String getSqTime() {
        return sqTime;
    }

    public void setSqTime(String sqTime) {
        this.sqTime = sqTime;
    }

    public String getSpTime() {
        return spTime;
    }

    public void setSpTime(String spTime) {
        this.spTime = spTime;
    }

    public String getSqIntegral() {
        return sqIntegral;
    }

    public void setSqIntegral(String sqIntegral) {
        this.sqIntegral = sqIntegral;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalImg1() {
        return approvalImg1;
    }

    public void setApprovalImg1(String approvalImg1) {
        this.approvalImg1 = approvalImg1;
    }

    public String getkIntegral() {
        return kIntegral;
    }

    public void setkIntegral(String kIntegral) {
        this.kIntegral = kIntegral;
    }

    @Override
    public String toString() {
        return "App{" +
                "approvalId='" + approvalId + '\'' +
                ", approvalTitle='" + approvalTitle + '\'' +
                ", approvalContent='" + approvalContent + '\'' +
                ", userName='" + userName + '\'' +
                ", userImg='" + userImg + '\'' +
                ", integralTypeId='" + integralTypeId + '\'' +
                ", sqTime='" + sqTime + '\'' +
                ", spTime='" + spTime + '\'' +
                ", sqIntegral='" + sqIntegral + '\'' +
                ", status='" + status + '\'' +
                ", approvalImg1='" + approvalImg1 + '\'' +
                ", kIntegral='" + kIntegral + '\'' +
                '}';
    }
}
