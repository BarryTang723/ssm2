package cn.itcast.domain;

import cn.itcast.util.DateUtils;

import java.util.Date;

public class ApplyAndJob {
    private Integer id;
    private String wUserName;
    private String firmName;
    private String jobName;
    private String JobRequire;
    private String treatment;
    private Date applyDate;
    private String applyDateString;
    private String uMessage;
    private String bMessage;
    private Integer applyProcess;
    private String  applyProcessString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getwUserName() {
        return wUserName;
    }

    public void setwUserName(String wUserName) {
        this.wUserName = wUserName;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobRequire() {
        return JobRequire;
    }

    public void setJobRequire(String jobRequire) {
        JobRequire = jobRequire;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApplyProcess() {
        return applyProcess;
    }

    public void setApplyProcess(Integer applyProcess) {
        this.applyProcess = applyProcess;
    }

    public String getApplyDateString() {
        if(applyDate!=null){
            applyDateString= DateUtils.date2String(applyDate,"yyyy-MM-dd");
        }
        return applyDateString;
    }

    public void setApplyDateString(String applyDateString) {
        this.applyDateString = applyDateString;
    }

    public String getApplyProcessString() {
        switch (applyProcess){
            case 0:
                applyProcessString="未查看";
                break;
            case 1:
                applyProcessString="已查看";
                break;
            case 2:
                applyProcessString="预约面试";
                break;
            case 3:
                applyProcessString="申请成功";
                break;
            case 4:
                applyProcessString="申请失败";
                break;
        }
        return applyProcessString;
    }

    public void setApplyProcessString(String applyProcessString) {
        this.applyProcessString = applyProcessString;
    }

    @Override
    public String toString() {
        return "ApplyAndJob{" +
                "id=" + id +
                ", wUserName='" + wUserName + '\'' +
                ", firmName='" + firmName + '\'' +
                ", jobName='" + jobName + '\'' +
                ", JobRequire='" + JobRequire + '\'' +
                ", treatment='" + treatment + '\'' +
                ", applyDate=" + applyDate +
                ", applyDateString='" + applyDateString + '\'' +
                ", uMessage='" + uMessage + '\'' +
                ", bMessage='" + bMessage + '\'' +
                ", applyProcess=" + applyProcess +
                ", applyProcessString='" + applyProcessString + '\'' +
                '}';
    }
}
