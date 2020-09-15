package cn.itcast.domain;

import cn.itcast.util.DateUtils;

import java.util.Date;

public class Boss {
    private Integer id;
    private String username;
    private String password;
    private String firmName;
    private String phoneNum;
    private String email;
    private String industry;
    private Date vipDate;
    private String vipDateStr;
    private Integer isVip;
    private Integer isValid;
    private String checkCode;

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getVipDateStr() {
        if(vipDate!=null){
            vipDateStr= DateUtils.date2String(vipDate,"yyyy-MM-dd");
        }
        return vipDateStr;
    }

    public void setVipDateStr(String vipDateStr) {
        this.vipDateStr = vipDateStr;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getIndustry() {
        return industry;
    }



    public Integer getIsVip() {
        return isVip;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getVipDate() {
        return vipDate;
    }

    public void setVipDate(Date vipDate) {
        this.vipDate = vipDate;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firmName='" + firmName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", industry='" + industry + '\'' +
                ", vipDate=" + vipDate +
                ", isVip=" + isVip +
                ", isValid=" + isValid +
                '}';
    }
}
