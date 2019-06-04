package com.msj.goods.entity;

/**
 * @author sun li
 * @Date 2019/2/17 15:30
 * @Description
 */
public class ManageUser {

    private Integer userId;
    private String userName;
    private String avatar;
    private String deptName;
    private String roleKey;

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "ManageUser{" + "userId=" + userId + ", userName='" + userName + '\'' + ", avatar='" + avatar + '\'' + ", deptName='" + deptName + '\'' + ", roleKey='" + roleKey + '\'' + '}';
    }
}
