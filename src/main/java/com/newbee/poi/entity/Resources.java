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

    /**
     * 权限key是唯一的，新增时需要注意
     */
    private String resKsy;

    private Integer leval;

    /**
     * 权限类型，0表示目录，1表示菜单，2表示按钮
     */
    private String type;

    private String description;

    private Set<Roles> roles = new HashSet<>(0);

    private Set<Resources> childs = new HashSet<>(0);

    public Resources() {

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

    public String getResKsy() {
        return resKsy;
    }

    public void setResKsy(String resKsy) {
        this.resKsy = resKsy;
    }

    public Integer getLeval() {
        return leval;
    }

    public void setLeval(Integer leval) {
        this.leval = leval;
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

    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", resKsy='" + resKsy + '\'' +
                ", leval=" + leval +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", roles=" + roles +
                ", childs=" + childs +
                '}';
    }
}
