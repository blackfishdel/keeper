package com.del.keeper.commons.entity;

import java.io.Serializable;
import java.util.Date;

public class AccountLog implements Serializable {
    private Long id;

    private Integer version;

    private Date createTime;

    private String currentIp;

    private String deviceToken;

    private Integer goldId;

    private String goldNum;

    private String handleCode;

    private String handleType;

    private String handleParams;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp == null ? null : currentIp.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public Integer getGoldId() {
        return goldId;
    }

    public void setGoldId(Integer goldId) {
        this.goldId = goldId;
    }

    public String getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(String goldNum) {
        this.goldNum = goldNum == null ? null : goldNum.trim();
    }

    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode == null ? null : handleCode.trim();
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType == null ? null : handleType.trim();
    }

    public String getHandleParams() {
        return handleParams;
    }

    public void setHandleParams(String handleParams) {
        this.handleParams = handleParams == null ? null : handleParams.trim();
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
        AccountLog other = (AccountLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getVersion() == null ? other.getVersion() == null
                        : this.getVersion().equals(other.getVersion()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null
                        : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCurrentIp() == null ? other.getCurrentIp() == null
                        : this.getCurrentIp().equals(other.getCurrentIp()))
                && (this.getDeviceToken() == null ? other.getDeviceToken() == null
                        : this.getDeviceToken().equals(other.getDeviceToken()))
                && (this.getGoldId() == null ? other.getGoldId() == null : this.getGoldId().equals(other.getGoldId()))
                && (this.getGoldNum() == null ? other.getGoldNum() == null
                        : this.getGoldNum().equals(other.getGoldNum()))
                && (this.getHandleCode() == null ? other.getHandleCode() == null
                        : this.getHandleCode().equals(other.getHandleCode()))
                && (this.getHandleType() == null ? other.getHandleType() == null
                        : this.getHandleType().equals(other.getHandleType()))
                && (this.getHandleParams() == null ? other.getHandleParams() == null
                        : this.getHandleParams().equals(other.getHandleParams()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCurrentIp() == null) ? 0 : getCurrentIp().hashCode());
        result = prime * result + ((getDeviceToken() == null) ? 0 : getDeviceToken().hashCode());
        result = prime * result + ((getGoldId() == null) ? 0 : getGoldId().hashCode());
        result = prime * result + ((getGoldNum() == null) ? 0 : getGoldNum().hashCode());
        result = prime * result + ((getHandleCode() == null) ? 0 : getHandleCode().hashCode());
        result = prime * result + ((getHandleType() == null) ? 0 : getHandleType().hashCode());
        result = prime * result + ((getHandleParams() == null) ? 0 : getHandleParams().hashCode());
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
        sb.append(", currentIp=").append(currentIp);
        sb.append(", deviceToken=").append(deviceToken);
        sb.append(", goldId=").append(goldId);
        sb.append(", goldNum=").append(goldNum);
        sb.append(", handleCode=").append(handleCode);
        sb.append(", handleType=").append(handleType);
        sb.append(", handleParams=").append(handleParams);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}