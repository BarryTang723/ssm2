package cn.itcast.controller;

import cn.itcast.dao.AdminDao;
import cn.itcast.dao.BossDao;
import cn.itcast.dao.JobDao;
import cn.itcast.dao.WorkerDao;
import cn.itcast.domain.*;
import cn.itcast.other.ImageVerificationCode;
import cn.itcast.util.face.FaceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GuestController {
    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private BossDao bossDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private JobDao jobDao;
    @RequestMapping("dispatch.do")
    public String findAllJobs(HttpSession httpSession) throws IOException {
        //获取角色
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role="";
        String name="";
        if (principal instanceof UserDetails) {
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            name=userInfo.getUsername();
            role=userInfo.getRole();
        }
        //用于在头部显示用户名
        httpSession.setAttribute("wUsername",name);
        if(role.equals("user")){

//            httpSession.setAttribute("wUsername",name);
            return "redirect:job-list2.jsp";
        }else if(role.equals("boss")){
            Integer isValid = bossDao.findBossByUsername(name).get(0).getIsValid();
            if(isValid!=1){
                httpSession.setAttribute("wIsValid","未通过审核");
            }

            return "redirect:boss/index.jsp";
        }else if(role.equals("admin")){
            return "redirect:admin/index.jsp";
        }else{
            return "redirect:failure.jsp";
        }
    }
    //注册worker(ajax)
    @RequestMapping("signUp.do")
    @ResponseBody
    public String su(String user,String pwd, String checkCode, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //json转java对象
//        ObjectMapper mapper = new ObjectMapper();
//        Worker worker=mapper.readValue(workerJson,Worker.class);
//        System.out.println(workerJson);
        Worker worker = new Worker();
        worker.setUsername(user);
        worker.setPassword(pwd);
        System.out.println(worker);
        System.out.println(checkCode);
        if(checkCode_session.equalsIgnoreCase(checkCode)){
            workerDao.addWorker(worker);
            return "1";
        }else{
            //验证码错误
            return "0";
        }
    }
    //注册admin(ajax)
    @RequestMapping("signUpAdmin.do")
    @ResponseBody
    public String signUpAdmin(String user,String pwd, String checkCode, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        Admin admin = new Admin();
        admin.setUsername(user);
        admin.setPassword(pwd);
        if(checkCode_session.equalsIgnoreCase(checkCode)){
            Integer ret = adminDao.addAdmin(admin);
            System.out.println("返回值:"+ret);
            //返回1
            return ret.toString();
        }else{
            //验证码错误
            return "0";
        }
    }
    //注册Boss(ajax)
    @RequestMapping("signUpBoss.do")
    @ResponseBody
    public String signUpBoss(@RequestBody Boss boss, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        String checkCode=boss.getCheckCode();
        if(checkCode_session.equalsIgnoreCase(checkCode)){
            Integer ret = bossDao.addBoss(boss);
            System.out.println("返回值:"+ret);
            //成功,返回1
            return ret.toString();
        }else{
            //验证码错误
            return "0";
        }
//        System.out.println(boss);
////        System.out.println(checkCode);
//        return "1";
    }
    //生成验证码
    @RequestMapping("checkCode.do")
    public void ck(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageVerificationCode ivc = new ImageVerificationCode();     //用我们的验证码类，生成验证码类对象
        BufferedImage image = ivc.getImage();  //获取验证码
        request.getSession().setAttribute("checkCode_session", ivc.getText()); //将验证码的文本存在session中
        ivc.output(image, response.getOutputStream());
    }
    //验证用户名是否可用
    @RequestMapping("checkUsername.do")
    @ResponseBody
    public String cu(String user,String role,HttpServletRequest request) throws IOException {
        //用户名为空时
        int size=0;
        if(user==null || user.equals("")){
            return "2";
        }
        if(role.equals("user")){
            size = workerDao.findWorkerByUsername(user).size();
        }
        if(role.equals("boss")){
            size = bossDao.findBossByUsername(user).size();
        }
        if(role.equals("admin")){
            size = adminDao.findAdminByUsername(user).size();
        }


        if(size>=1){
            //不可用
            return "0";
        }else{
            //可用
            return "1";
        }
    }
    //人脸登录,faceLogin
    @RequestMapping("faceLogin.do")
    @ResponseBody
    public String fun1(@RequestParam("file") MultipartFile upfile, HttpServletRequest request,String username) throws Exception {
        //先存上
        System.out.println("upper1----------------------------"+upfile.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/upload");
        String fileName="login.jpg";
        File saveFile=new File(path+"/"+fileName);
        upfile.transferTo(saveFile);
        //对比
//        String path="C:/Users/WDTMD/IdeaProjects/ssm/target/ssm/upload";
        System.out.println(username);
        username="tom";
        String token="24.22dee2f3ecb1f3f02c7324dc965d2f2e.2592000.1596940299.282335-21168303";
        String path1=path+"/"+username+".jpg";
        String path2=path+"/login.jpg";
        String res_json = FaceUtil.faceMatch(token, path1, path2);

        JSONObject jsonObject = JSON.parseObject(res_json);
        String score = jsonObject.getJSONObject("result").getString("score");
        System.out.println(score);
        if(Float.parseFloat(score)>70){
            Admin admin = adminDao.findAdminByUsername(username).get(0);
            String password=admin.getPassword();
            return password;
        }else {
            return "0";
        }
    }
    //展示所有职位json
    @RequestMapping("findAllJobs2.do")
    @ResponseBody
    public Grid findAllJobs2(Integer page,Integer limit){
        List<Job> jobList = jobDao.findAllPage((page - 1) * limit, limit);
        Integer size = jobDao.findAllSize();
        return new Grid(0,"ok",size,jobList);
    }
    //根据公司名称搜索(ajax)
    @RequestMapping("searchByFirm.do")
    @ResponseBody
    public List<Job> searchbyfirm(String firmName){
        List<Job> jobList = jobDao.findJobByFirm(firmName);
        return jobList;
    }
    //根据职业名称搜索(ajax)
    @RequestMapping("searchByJob.do")
    @ResponseBody
    public List<Job> searchbyjob(String jobName){
        List<Job> jobList = jobDao.findJobByJobName(jobName);
        return jobList;
    }
}
