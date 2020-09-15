package cn.itcast.dao;

import cn.itcast.domain.Job;
import cn.itcast.domain.Worker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Repository;

import java.util.List;

//交给ioc容器管理
@Repository
public interface JobDao {
    //显示所有职位
    @Select("select * from jobs")
    public List<Job> findAll();
    //显示所有职位(分页)
    @Select("select * from jobs inner join bosses on jobs.bUsername=bosses.username order by isVip desc limit #{arg0},#{arg1};")
    public List<Job> findAllPage(Integer start,Integer count);
    //显示所有职位
    @Select("select count(*) from jobs")
    public Integer findAllSize();


    //按id查找
    @Select("select * from jobs where id=#{id}")
    public Job findJobById(Integer id);
    //1按企业名称查找
    @Select("select * from jobs where firmName like '%${value}%'")
    public List<Job> findJobByFirm(String firmName);
    //1按职业名称查找
    @Select("select * from jobs where jobName like '%${value}%'")
    public List<Job> findJobByJobName(String jobName);

    //2发布招聘信息
    @Insert("insert into jobs values (null,#{jobName},#{bUserName},#{firmName},#{jobRequire},#{treatment})")
    public Integer addJob(Job job);
    //2查询所有申请
}
