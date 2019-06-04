package com.msj.goods.entity;

import java.util.Date;

/**
 * @author sun li
 * @Date 2019/2/15 11:43
 * @Description
 */
public class TaskUserList {
    /**
     * 用户主键
     */
    private Integer userId;

    /** 用户名称 */
    private String userName;
    /**
     * 审批状态(0,审批中 1完成，2审批不通过，
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TaskUserList{" + "userId=" + userId + ", userName='" + userName + '\'' + ", state='" + state + '\'' + ", createDate=" + createDate + '}';
    }
}
