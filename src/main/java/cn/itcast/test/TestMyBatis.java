package cn.itcast.test;

import cn.itcast.dao.*;
import cn.itcast.domain.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMyBatis {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private BossDao bossDao;
    @Autowired
    private ApplyAndJobDao applyAndJobDao;
    @Test
    public void testFindAll() {
        //到时候去session里拿用户名
        Apply apply = new Apply();
        apply.setJobId(1);
        apply.setuMessage("去玩儿去玩儿去玩儿去玩儿");
        apply.setwUsername("tom");
        apply.setApplyDate(new Date());
        //要去bosses里面查一下bUsername
        Job job = jobDao.findJobById(apply.getJobId());
        apply.setbUsername(job.getbUserName());
        System.out.println(apply);
        applyDao.addApply(apply);

    }


//    @Test
//    public void testLogin() {
//
//        Worker worker = new Worker();
//        worker.setUsername("worker1");
//        worker.setPassword("123");
//        workerDao.addWorker(worker);
//    }
//    @Test
//    public void testWorker3() {
//
//        Worker worker = new Worker();
//        worker.setUsername("worker1");
//        worker.setResume("擅长胸口碎大石");
//        workerDao.addWorkerResume(worker);
//    }
    //测试申请
    @Test
    public void testJob1() {
        Apply apply = new Apply();
        apply.setJobId(1);
        apply.setuMessage("abab");


        //到时候去session里拿用户名
        apply.setwUsername("tom");
        //先判断有没有申请过
        List<Apply> applyList= applyDao.findApplyByWUsernameJoBId(apply);
        if(applyList.size()>=1){
            System.out.println("已有申请");
        }else{
            apply.setApplyDate(new Date());
            //要去bosses里面查一下bUsername
            Job job = jobDao.findJobById(apply.getJobId());
            String bUserName = job.getbUserName();
            apply.setbUsername(bUserName);
    //        apply.setbUsername("jackMa");
            System.out.println(apply);
            applyDao.addApply(apply);
            System.out.println("申请成功");
        }
    }
    @Test
    public void testApply1() {
        Worker worker = new Worker();
//        worker.setUsername("mea");
        worker.setPassword("123");
        worker.setResume("我很可爱,请亏我全");
        workerDao.updateResume(worker);
    }
    @Test
    public void testApply2() {
        List<ApplyAndJob> toms = applyAndJobDao.findApplyJobByWUsername("tom");
        for (ApplyAndJob tom : toms) {
            System.out.println(tom);
        }
    }
    @Test
    public void testAdmin() {
        String username="jackMa";
        Integer vipType=1;
        Boss boss= bossDao.findBossByUsername(username).get(0);
        Integer isVip = boss.getIsVip();
        if(isVip==null || isVip==0){
            Boss boss1 = new Boss();
            boss1.setUsername(username);
            boss1.setIsVip(1);
            boss1.setVipDate(new Date());
            bossDao.updateVipInfo(boss1);
        }
//        //加上天数
        Integer ret = bossDao.updateVipDateByUsername(username, vipType);
        System.out.println("ret="+ret);
//        Integer ret = bossDao.updateVipDateByUsername("jackMa", 30);
//        System.out.println(ret);


    }
    @Test
    public void testApply21() {
       bossDao.updateVipDateByUsername("jackMa",0);
    }
}
