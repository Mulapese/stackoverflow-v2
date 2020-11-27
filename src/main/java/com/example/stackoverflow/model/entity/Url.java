//package com.example.stackoverflow.model.entity;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Collection;
//import java.util.Objects;
//
//@Entity
//public class Url {
//    private int urlId;
//    private String url;
//    private String action;
//    private Timestamp createdTime;
//    private Timestamp updatedTime;
//    private Collection<RoleUrl> roleUrls;
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
//    @Column(name = "url", nullable = true, length = -1)
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Basic
//    @Column(name = "action", nullable = true, length = -1)
//    public String getAction() {
//        return action;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
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
//        Url url1 = (Url) o;
//        return urlId == url1.urlId &&
//                Objects.equals(url, url1.url) &&
//                Objects.equals(action, url1.action) &&
//                Objects.equals(createdTime, url1.createdTime) &&
//                Objects.equals(updatedTime, url1.updatedTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(urlId, url, action, createdTime, updatedTime);
//    }
//
//    @OneToMany(mappedBy = "url")
//    public Collection<RoleUrl> getRoleUrls() {
//        return roleUrls;
//    }
//
//    public void setRoleUrls(Collection<RoleUrl> roleUrlsByUrlId) {
//        this.roleUrls = roleUrlsByUrlId;
//    }
//}
