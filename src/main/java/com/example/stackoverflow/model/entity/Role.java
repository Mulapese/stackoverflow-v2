//package com.example.stackoverflow.model.entity;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Collection;
//import java.util.Objects;
//
//@Entity
//public class Role {
//    private int roleId;
//    private String name;
//    private String description;
//    private Timestamp createdTime;
//    private Timestamp updatedTime;
//    private Collection<RoleUrl> roleUrls;
//
//    @Id
//    @Column(name = "role_id", nullable = false)
//    public int getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(int roleId) {
//        this.roleId = roleId;
//    }
//
//    @Basic
//    @Column(name = "name", nullable = true, length = -1)
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Basic
//    @Column(name = "description", nullable = true, length = -1)
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Basic
//    @Column(name = "created_time", nullable = true)
//    public Timestamp getCreatedTime() {
//        return createdTime;
//    }
//
//    public void setCreatedTime(Timestamp createdTime) {
//        this.createdTime = createdTime;
//    }
//
//    @Basic
//    @Column(name = "updated_time", nullable = true)
//    public Timestamp getUpdatedTime() {
//        return updatedTime;
//    }
//
//    public void setUpdatedTime(Timestamp updatedTime) {
//        this.updatedTime = updatedTime;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Role role = (Role) o;
//        return roleId == role.roleId &&
//                Objects.equals(name, role.name) &&
//                Objects.equals(description, role.description) &&
//                Objects.equals(createdTime, role.createdTime) &&
//                Objects.equals(updatedTime, role.updatedTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(roleId, name, description, createdTime, updatedTime);
//    }
//
//    @OneToMany(mappedBy = "role")
//    public Collection<RoleUrl> getRoleUrls() {
//        return roleUrls;
//    }
//
//    public void setRoleUrls(Collection<RoleUrl> roleUrlsByRoleId) {
//        this.roleUrls = roleUrlsByRoleId;
//    }
//}
