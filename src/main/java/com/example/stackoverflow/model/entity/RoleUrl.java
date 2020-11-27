//package com.example.stackoverflow.model.entity;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Objects;
//
//@Entity
//@Table(name = "role_url", schema = "public", catalog = "stack3")
//@IdClass(RoleUrlPK.class)
//public class RoleUrl {
//    private int roleId;
//    private int urlId;
//    private Timestamp createdTime;
//    private Timestamp updatedTime;
//    private Role role;
//    private Url url;
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
//    @Id
//    @Column(name = "url_id", nullable = false)
//    public int getUrlId() {
//        return urlId;
//    }
//
//    public void setUrlId(int urlId) {
//        this.urlId = urlId;
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
//        RoleUrl roleUrl = (RoleUrl) o;
//        return roleId == roleUrl.roleId &&
//                urlId == roleUrl.urlId &&
//                Objects.equals(createdTime, roleUrl.createdTime) &&
//                Objects.equals(updatedTime, roleUrl.updatedTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(roleId, urlId, createdTime, updatedTime);
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "url_id", referencedColumnName = "url_id", nullable = false)
//    public Url getUrl() {
//        return url;
//    }
//
//    public void setUrl(Url url) {
//        this.url = url;
//    }
//}
