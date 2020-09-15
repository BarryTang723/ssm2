package cn.itcast.test;

import cn.itcast.dao.ApplyDao;
import cn.itcast.dao.BossDao;
import cn.itcast.domain.Boss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestBossDao {

    @Autowired
    private BossDao bossDao;
    @Autowired
    private ApplyDao applyDao;
    @Test
    public void testFindAll() {
        Boss boss = new Boss();
        boss.setUsername("ponyMa");
        boss.setPassword("123");
        boss.setFirmName("腾讯");
        boss.setVipDate(new Date());
        boss.setIsVip(1);
        bossDao.updateVipInfo(boss);
    }

//    @Test
//    public void testFindAll() {
//        List<Worker> workers = workerDao.findAll();
//        for (Worker worker : workers) {
//            System.out.println(worker);
//        }
//    }
    @Test
    public void testLogin() {
        Integer switchAddProcess=1;
        if(switchAddProcess>=0 && switchAddProcess<=5){
            //传几就加几
            applyDao.updateApplyProcessById(switchAddProcess,14);
        }
    }
    @Test
    public void testWorker3() {
        Boss boss = new Boss();
        boss.setUsername("1");
        boss.setPassword("1");
        boss.setIsValid(0);
        bossDao.addBossPlus(boss);
    }
//    @Test
//    public void testJob1() {
//        List<Job> all = jobDao.findByFirm("阿里巴巴");
//        for (Job job : all) {
//            System.out.println(job);
//        }
//    }
//    @Test
//    public void testApply1() {
//        Apply apply = new Apply();
//        apply.setwUsername("tom");
//        apply.setbUsername("jackMa");
//        apply.setApplyProcess(0);
//        apply.setApplyDate(new Date());
//        applyDao.addApplyByUsername(apply);
//    }
//    @Test
//    public void testApply2() {
//        Apply apply = new Apply();
//        apply.setId(2);
//
//        apply.setuMessage("求求你了,给我这个工作吧,我可以做任何事情");
//        applyDao.updateApplyByUsername(apply);
//    }
//    @Test
//    public void testApply1() {
//        Worker worker = new Worker();
//        worker.setEducation("天津");
//
//        List<Worker> workers = workerDao.findWorkerByEdu(worker);
//        for (Worker worker1 : workers) {
//            System.out.println(worker1);
//        }
//    }
}
