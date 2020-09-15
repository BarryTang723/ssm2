package cn.itcast.domain;

public class Job {
    private Integer id;
    private String firmName;
    private String jobName;
    private String bUserName;
    private String jobRequire;
    private String treatment;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", firmName='" + firmName + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobRequire='" + jobRequire + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }

    public String getbUserName() {
        return bUserName;
    }

    public void setbUserName(String bUserName) {
        this.bUserName = bUserName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return jobRequire;
    }

    public void setJobRequire(String jobRequire) {
        this.jobRequire = jobRequire;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
