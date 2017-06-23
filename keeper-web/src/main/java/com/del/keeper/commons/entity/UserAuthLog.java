package com.del.keeper.commons.entity;

import java.io.Serializable;
import java.util.Date;

public class UserAuthLog implements Serializable {
    private Long id;

    private Integer userId;

    private String appIdentifier;

    private Byte appIdentifierType;

    private String lastIp;

    private Date lastTime;

    private String currentIp;

    private Date currentTime;

    private Byte authType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp == null ? null : currentIp.trim();
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Byte getAuthType() {
        return authType;
    }

    public void setAuthType(Byte authType) {
        this.authType = authType;
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
        UserAuthLog other = (UserAuthLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getAppIdentifier() == null ? other.getAppIdentifier() == null
                        : this.getAppIdentifier().equals(other.getAppIdentifier()))
                && (this.getAppIdentifierType() == null ? other.getAppIdentifierType() == null
                        : this.getAppIdentifierType().equals(other.getAppIdentifierType()))
                && (this.getLastIp() == null ? other.getLastIp() == null : this.getLastIp().equals(other.getLastIp()))
                && (this.getLastTime() == null ? other.getLastTime() == null
                        : this.getLastTime().equals(other.getLastTime()))
                && (this.getCurrentIp() == null ? other.getCurrentIp() == null
                        : this.getCurrentIp().equals(other.getCurrentIp()))
                && (this.getCurrentTime() == null ? other.getCurrentTime() == null
                        : this.getCurrentTime().equals(other.getCurrentTime()))
                && (this.getAuthType() == null ? other.getAuthType() == null
                        : this.getAuthType().equals(other.getAuthType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAppIdentifier() == null) ? 0 : getAppIdentifier().hashCode());
        result = prime * result + ((getAppIdentifierType() == null) ? 0 : getAppIdentifierType().hashCode());
        result = prime * result + ((getLastIp() == null) ? 0 : getLastIp().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getCurrentIp() == null) ? 0 : getCurrentIp().hashCode());
        result = prime * result + ((getCurrentTime() == null) ? 0 : getCurrentTime().hashCode());
        result = prime * result + ((getAuthType() == null) ? 0 : getAuthType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", appIdentifier=").append(appIdentifier);
        sb.append(", appIdentifierType=").append(appIdentifierType);
        sb.append(", lastIp=").append(lastIp);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", currentIp=").append(currentIp);
        sb.append(", currentTime=").append(currentTime);
        sb.append(", authType=").append(authType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}