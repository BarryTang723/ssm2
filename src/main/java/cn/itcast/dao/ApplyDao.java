package cn.itcast.dao;

import cn.itcast.domain.Apply;
import cn.itcast.domain.Job;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//交给ioc容器管理
@Repository
public interface ApplyDao{
    //查看所含有申请
    @Select("select * from applies")
    public List<Apply> findAll();
    //1某人看自己的申请
    @Select("select * from applies where wUsername=#{wUsername}")
    public List<Apply> findApplyByWUsername(String wUsername);
    //1某人提交一个申请
    @Insert("insert into applies (wUsername,bUsername,jobId,applyProcess,applyDate,uMessage) values (#{wUsername},#{bUsername},#{jobId},#{applyProcess},#{applyDate},#{uMessage}) ")
    public void addApply(Apply apply);
    //1某人修改申请留言
    @Update("update applies set uMessage=#{uMessage} where id=#{id}")
    public void updateApplyUMessageById(Apply apply);
    //1根据jobId和wUsername,查询申请(用于验证是否重复申请)
    @Select("select * from applies where wUsername=#{wUsername} and jobId=#{jobId}")
    public List<Apply> findApplyByWUsernameJoBId(Apply apply);

    //2企业看申请
    @Select("select * from applies where bUsername=#{bUsername}")
    public List<Apply> findApplyByBUsername(String bUsername);
    //2企业留言
    @Update("update applies set bMessage=#{bMessage} where id=#{id}")
    public void updateApplyBMessageById(Apply apply);
    //2招聘流程管理,进度加1
    @Update("update applies set applyProcess=applyProcess + #{arg0} where id=#{arg1}")
    public void updateApplyProcessById(Integer applyProcess,Integer id);
}
