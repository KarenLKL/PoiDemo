package com.newbee.poi.entity;

/**
 * Created by Major on 2016/11/21.
 */
public class ResourcesRole {
    private Integer resoucesId;
    private Integer roleId;

    public Integer getResoucesId() {
        return resoucesId;
    }

    public void setResoucesId(Integer resoucesId) {
        this.resoucesId = resoucesId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ResourcesRoleDao{" +
                "resoucesId=" + resoucesId +
                ", roleId=" + roleId +
                '}';
    }
}
