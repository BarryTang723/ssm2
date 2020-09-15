package cn.itcast.test;

import cn.itcast.dao.BossDao;
import cn.itcast.dao.JobDao;
import cn.itcast.domain.Boss;
import cn.itcast.domain.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestJobDao {
    @Autowired
    private JobDao jobDao;


    @Test
    public void testFindAll() {
        Integer page=2;
        Integer limit=5;
        List<Job> jobList = jobDao.findAllPage((page - 1) * limit, limit);
        for (Job job : jobList) {
            System.out.println(job);
        }
    }


}
