package cn.itcast.controller;

import cn.itcast.dao.*;
import cn.itcast.domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private ApplyAndJobDao applyAndJobDao;
    @Autowired
    private IntentionDao intentionDao;
    //展示所有职位
    @RequestMapping("findAllJobs.do")
    public String findAllJobs(ModelMap mp){
        List<Job> jobs = jobDao.findAll();
        mp.addAttribute("jobList",jobs);
        return "job-list";
    }


    //根据公司和职业名称搜索(ajax) 代码太蠢,回来再处理
//    @RequestMapping("searchByFirmNJob.do")
//    @ResponseBody
//    public List<Job> searchbyfirmandjob(String firmName,String jobName){
//        if(firmName==null||firmName.equals("")){ //1代表不空,0代表空
//            if (jobName==null||jobName.equals("")){
//                //00
//
//            }else{
//                //01
//            }
//        }else {
//            //firmName不空
//            if (jobName==null||jobName.equals("")){
//                //10
//            }else{
//                //11
//            }
//        }
//        List<Job> jobList = jobDao.findJobByJobName(jobName);
//        return jobList;
//    }


    //申请工作(ajax)
    @RequestMapping("applyForJob.do")
    @ResponseBody
    public String apply(Apply apply) throws IOException {

        //获取用户名
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
        apply.setwUsername(username);
        //先判断有没有申请过
        List<Apply> applyList= applyDao.findApplyByWUsernameJoBId(apply);
        if(applyList.size()>=1){
            return "0";
        }
        apply.setApplyDate(new Date());
        //要去bosses里面查一下bUsername
        Job job = jobDao.findJobById(apply.getJobId());
        String bUsernname=job.getbUserName();
        apply.setbUsername(bUsernname);
        System.out.println(apply.getbUsername());
//        System.out.println(apply);
        //进度要初始化
        apply.setApplyProcess(0);
        applyDao.addApply(apply);
        return "1";
    }
//    //申请工作草稿
//    @RequestMapping("beforeApplyForJob.do")
//    public String apply(Integer jobId,HttpServletRequest request){
//
//        request.setAttribute("jobId",jobId);
//        return "applyForJob";
//    }
    //查看自己的简历
    @RequestMapping("checkMyResume.do")
    public String checkMyResume(ModelMap mp) throws IOException {
        //获取用户名
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }

        //从session里拿用户名
//        String username="tom";
        List<Worker> workerList = workerDao.findWorkerByUsername(username);
        mp.addAttribute("worker",workerList.get(0));
        return "updateMyResume";
    }
    //提交自己的简历(ajax)
    @RequestMapping("submitMyResume.do")
    @ResponseBody
    public String submitMyResume(@RequestBody Worker worker){
        System.out.println(worker);
        workerDao.updateResume(worker);
        return "1";
    }
    //查看自己所有申请checkMyApplies
    @RequestMapping("checkMyApplies.do")
    @ResponseBody
    public List<ApplyAndJob> checkMyApplies() throws IOException {
        //去session里拿
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
        List<ApplyAndJob> applyList = applyAndJobDao.findApplyJobByWUsername(username);

        return applyList;
    }
//jedis缓存目标职位
//@RequestMapping("showJedis.do")
//@ResponseBody
//public String showJedis(){
//    System.out.println("show jedis..");
//    Jedis jd = new Jedis("192.168.0.102", 6379);
//    jd.auth("hello");
//    System.out.println("------"+jd.ping());
//    return "success";
//
//}
@RequestMapping("showIntention.do")
//@ResponseBody
public void showItention(HttpServletResponse response) throws Exception {
        //先从redis里面查
    Jedis jedis=new Jedis("127.0.0.1", 6379);
//    jedis.auth("hello");
    String intention_json = jedis.get("intention");
    //如果jedis里面没有,去数据库里查
    if(intention_json==null || intention_json.length()==0){
        System.out.println("jedis里面没有,去数据库里查");
        List<Intention> intentionList = intentionDao.findAll();
        ObjectMapper mapper = new ObjectMapper();
        intention_json = mapper.writeValueAsString(intentionList);
        jedis.set("intention",intention_json);
        jedis.close();
    }else {
        System.out.println("jedis里面有,jedis真是太棒了!");
    }
//    List<Intention> intentionList = intentionDao.findAll();
//    ObjectMapper mapper = new ObjectMapper();
//    String json = mapper.writeValueAsString(intentionList);
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().write(intention_json);
}
//    //注册
//    @RequestMapping("signUp.do")
//    public String su(Worker worker,String checkCode,HttpServletRequest request){
//        HttpSession session = request.getSession();
//        String checkCode_session = (String) session.getAttribute("checkCode_session");
//        if(checkCode_session.equalsIgnoreCase(checkCode)){
//            workerDao.addWorker(worker);
//            return "success";
//        }else{
//            request.setAttribute("cc_error","验证码错误!");
//            return "signUp";
//        }
//    }

}
