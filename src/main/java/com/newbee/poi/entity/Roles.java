package com.newbee.poi.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by major on 2016/11/17.
 */
public class Roles {

    private Integer id;

    /**
     * 是否禁用角色，1表示禁用，2表示不禁用
     */
    private Integer enable;
    private String name;
    private String roleKey;
    private String descrition;
    private Set<Resources> resources = new HashSet<>(0);

    public Roles() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public Set<Resources> getResources() {
        return resources;
    }

    public void setResources(Set<Resources> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", enable=" + enable +
                ", name='" + name + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", descrition='" + descrition + '\'' +
                ", resources=" + resources +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roles roles = (Roles) o;

        if (id != null ? !id.equals(roles.id) : roles.id != null) return false;
        if (enable != null ? !enable.equals(roles.enable) : roles.enable != null) return false;
        if (name != null ? !name.equals(roles.name) : roles.name != null) return false;
        if (roleKey != null ? !roleKey.equals(roles.roleKey) : roles.roleKey != null) return false;
        if (descrition != null ? !descrition.equals(roles.descrition) : roles.descrition != null) return false;
        return resources != null ? resources.equals(roles.resources) : roles.resources == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enable != null ? enable.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (roleKey != null ? roleKey.hashCode() : 0);
        result = 31 * result + (descrition != null ? descrition.hashCode() : 0);
        result = 31 * result + (resources != null ? resources.hashCode() : 0);
        return result;
    }
}
