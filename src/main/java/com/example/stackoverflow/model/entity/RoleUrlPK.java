//package com.example.stackoverflow.model.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.util.Objects;
//
//public class RoleUrlPK implements Serializable {
//    private int roleId;
//    private int urlId;
//
//    @Column(name = "role_id", nullable = false)
//    @Id
//    public int getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(int roleId) {
//        this.roleId = roleId;
//    }
//
//    @Column(name = "url_id", nullable = false)
//    @Id
//    public int getUrlId() {
//        return urlId;
//    }
//
//    public void setUrlId(int urlId) {
//        this.urlId = urlId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RoleUrlPK roleUrlPK = (RoleUrlPK) o;
//        return roleId == roleUrlPK.roleId &&
//                urlId == roleUrlPK.urlId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(roleId, urlId);
//    }
//}
