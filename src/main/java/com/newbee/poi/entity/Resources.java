package com.newbee.poi.entity;

/**
 * Created by major on 2016/11/17.
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源实体类
 */
public class Resources implements Serializable {

    private Integer id;

    private String name;

    private Integer parentId;

    private String parentName;

    private String resUrl;

    /**
     * 权限key是唯一的，新增时需要注意
     */
    private String resKey;

    private Integer level;

    /**
     * 权限类型，0表示目录，1表示菜单，2表示按钮
     */
    private String type;

    private String description;

    private Set<Roles> roles = new HashSet<>(0);

    private Set<Resources> childs = new HashSet<>(0);

    public Resources() {

    }

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", resKey='" + resKey + '\'' +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", roles=" + roles +
                ", childs=" + childs +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Set<Resources> getChilds() {
        return childs;
    }

    public void setChilds(Set<Resources> childs) {
        this.childs = childs;
    }
}
