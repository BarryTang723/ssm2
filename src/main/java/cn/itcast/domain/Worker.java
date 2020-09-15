package cn.itcast.domain;

public class Worker {
    private Integer id;
    private  String username;
    private  String password;
    private  String realname;
    private  String sex;
    private  String phoneNum;
    private  String email;
    private  String education;
    private  String intention;
    private  String resume ;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealname() {
        return realname;
    }

    public String getSex() {
        return sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }

    public String getIntention() {
        return intention;
    }

    public String getResume() {
        return resume;
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

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                ", education='" + education + '\'' +
                ", intention='" + intention + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }
}
