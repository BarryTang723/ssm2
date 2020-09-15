package cn.itcast.controller;

import cn.itcast.dao.ApplyAndJobDao;
import cn.itcast.dao.ApplyDao;
import cn.itcast.dao.BossDao;
import cn.itcast.dao.JobDao;
import cn.itcast.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/boss")
public class BossController {
    @Autowired
    private BossDao bossDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private ApplyAndJobDao applyAndJobDao;
    @Autowired
    private HttpSolrClient client;

    //根据id修改一个boss
    @RequestMapping("updateBoss.do")
    public String upateBoss( Model model) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {

//            System.out.println("用户名:" + ((UserDetails) principal).getUsername());
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
        Boss boss = bossDao.findBossByUsername(username).get(0);
        model.addAttribute("boss",boss);
        return "boss/updateBoss";
    }
    //提交对boss的修改
    @RequestMapping("submitBoss.do")
    @ResponseBody
    public String submitBoss(@RequestBody Boss boss){
        System.out.println(boss);
        bossDao.updateBossInfo(boss);
        return "1";
    }
    //增加一个Job
    @RequestMapping("addJob.do")
    @ResponseBody
    public String addWorker(@RequestBody Job job) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {

//            System.out.println("用户名:" + ((UserDetails) principal).getUsername());
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
        job.setbUserName(username);
        System.out.println("job!!!!!!"+job);
        Boss boss = bossDao.findBossByUsername(username).get(0);
        Integer isValid = boss.getIsValid();
        if(isValid==1){
            Integer ret = jobDao.addJob(job);
            return "1";
        }else {
            return "0";
        }
    }
    //企业查看所有申请checkApplies
    @RequestMapping("checkApplies.do")
    @ResponseBody
    public List<ApplyAndJob> checkApplies() throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {

//            System.out.println("用户名:" + ((UserDetails) principal).getUsername());
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
//        String username="jackMa";
        //按企业用户名查找
        List<ApplyAndJob> applyList = applyAndJobDao.findApplyJobByBUsername(username);

        return applyList;
    }
    //企业管理招聘进度(ajax),和留言功能
    @RequestMapping("changeProcess.do")
    @ResponseBody
    public String apply(Integer id,String bMessage,Integer switchAddProcess){



        //改进度
        if(switchAddProcess>=0 && switchAddProcess<=3){
            //传几就加几
            applyDao.updateApplyProcessById(switchAddProcess,id);
        }
        Apply apply = new Apply();
        apply.setId(id);
        apply.setbMessage(bMessage);
        applyDao.updateApplyBMessageById(apply);
        return "1";
    }
    //提交vip申请(ajax)
    @RequestMapping("submitVip.do")
    @ResponseBody
    public String submitVip(Integer vipType) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {

//            System.out.println("用户名:" + ((UserDetails) principal).getUsername());
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
        //不是vip的先变为vip
        Boss boss= bossDao.findBossByUsername(username).get(0);
        Integer isVip = boss.getIsVip();
        if(isVip==null || isVip==0){
            Boss boss1 = new Boss();
            boss1.setUsername(username);
            boss1.setIsVip(1);
            boss1.setVipDate(new Date());
            bossDao.updateVipInfo(boss1);
        }
        //加上天数
        System.out.println("viptype="+vipType);
        Integer ret = bossDao.updateVipDateByUsername(username, vipType);
//        System.out.println("ret="+ret);

        return "1";
    }
    //展示所有worker json
    @RequestMapping("findAllWorkers.do")
    @ResponseBody
    public List<SolrDocument> findAllJobs2(String education,String intention,String resume) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.set("q","*:*");
        if(education!=null && ! education.equals("")){
            query.set("fq","education:"+education);
            System.out.println("education部分执行了!");
        }
        if(intention!=null && ! intention.equals("")){
            query.set("fq","intention:"+intention);
            System.out.println("intention部分执行了!");
        }
        if(resume!=null && !resume.equals("")){
            query.set("fq","resume:"+resume);
            System.out.println("resume部分执行了!");
        }
        QueryResponse res = client.query(query);
        SolrDocumentList rlt = res.getResults();
//        for (SolrDocument doc : rlt) {
////            System.out.println(doc.get("bookName")+"---"+doc.get("author"));
//            System.out.println(doc);
//        }
        return rlt;
    }
}
