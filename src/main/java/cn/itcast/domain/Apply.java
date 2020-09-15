package cn.itcast.domain;

import java.util.Date;

public class Apply {
    private Integer id;
    private String wUsername;
    private String  bUsername;
    private Integer jobId;
    private Integer applyProcess;
    private Date applyDate;
    private String uMessage;
    private String bMessage;

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", wUsername='" + wUsername + '\'' +
                ", bUsername='" + bUsername + '\'' +
                ", jobId=" + jobId +
                ", applyProcess=" + applyProcess +
                ", applyDate=" + applyDate +
                ", uMessage='" + uMessage + '\'' +
                ", bMessage='" + bMessage + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getwUsername() {
        return wUsername;
    }

    public void setwUsername(String wUsername) {
        this.wUsername = wUsername;
    }

    public String getbUsername() {
        return bUsername;
    }

    public void setbUsername(String bUsername) {
        this.bUsername = bUsername;
    }

    public Integer getApplyProcess() {
        return applyProcess;
    }

    public void setApplyProcess(Integer applyProcess) {
        this.applyProcess = applyProcess;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getuMessage() {
        return uMessage;
    }

    public void setuMessage(String uMessage) {
        this.uMessage = uMessage;
    }

    public String getbMessage() {
        return bMessage;
    }

    public void setbMessage(String bMessage) {
        this.bMessage = bMessage;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
