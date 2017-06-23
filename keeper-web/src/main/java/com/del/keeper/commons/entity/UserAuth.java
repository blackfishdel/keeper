package com.del.keeper.commons.entity;

import java.io.Serializable;
import java.util.Date;

public class UserAuth implements Serializable {
    private Integer id;

    private Integer version;

    private Date createTime;

    private Date updateTime;

    private String appIdentifier;

    private Byte appIdentifierType;

    private String appCredential;

    private String deviceToken;

    private String latestIp;

    private Date latestTime;

    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppIdentifier() {
        return appIdentifier;
    }

    public void setAppIdentifier(String appIdentifier) {
        this.appIdentifier = appIdentifier == null ? null : appIdentifier.trim();
    }

    public Byte getAppIdentifierType() {
        return appIdentifierType;
    }

    public void setAppIdentifierType(Byte appIdentifierType) {
        this.appIdentifierType = appIdentifierType;
    }

    public String getAppCredential() {
        return appCredential;
    }

    public void setAppCredential(String appCredential) {
        this.appCredential = appCredential == null ? null : appCredential.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public String getLatestIp() {
        return latestIp;
    }

    public void setLatestIp(String latestIp) {
        this.latestIp = latestIp == null ? null : latestIp.trim();
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserAuth other = (UserAuth) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getVersion() == null ? other.getVersion() == null
                        : this.getVersion().equals(other.getVersion()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null
                        : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null
                        : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getAppIdentifier() == null ? other.getAppIdentifier() == null
                        : this.getAppIdentifier().equals(other.getAppIdentifier()))
                && (this.getAppIdentifierType() == null ? other.getAppIdentifierType() == null
                        : this.getAppIdentifierType().equals(other.getAppIdentifierType()))
                && (this.getAppCredential() == null ? other.getAppCredential() == null
                        : this.getAppCredential().equals(other.getAppCredential()))
                && (this.getDeviceToken() == null ? other.getDeviceToken() == null
                        : this.getDeviceToken().equals(other.getDeviceToken()))
                && (this.getLatestIp() == null ? other.getLatestIp() == null
                        : this.getLatestIp().equals(other.getLatestIp()))
                && (this.getLatestTime() == null ? other.getLatestTime() == null
                        : this.getLatestTime().equals(other.getLatestTime()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAppIdentifier() == null) ? 0 : getAppIdentifier().hashCode());
        result = prime * result + ((getAppIdentifierType() == null) ? 0 : getAppIdentifierType().hashCode());
        result = prime * result + ((getAppCredential() == null) ? 0 : getAppCredential().hashCode());
        result = prime * result + ((getDeviceToken() == null) ? 0 : getDeviceToken().hashCode());
        result = prime * result + ((getLatestIp() == null) ? 0 : getLatestIp().hashCode());
        result = prime * result + ((getLatestTime() == null) ? 0 : getLatestTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", appIdentifier=").append(appIdentifier);
        sb.append(", appIdentifierType=").append(appIdentifierType);
        sb.append(", appCredential=").append(appCredential);
        sb.append(", deviceToken=").append(deviceToken);
        sb.append(", latestIp=").append(latestIp);
        sb.append(", latestTime=").append(latestTime);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}