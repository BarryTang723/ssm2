package cn.itcast.dao;

import cn.itcast.domain.ApplyAndJob;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyAndJobDao {
    //1多表查询,apply 和 job 表,根据申请人名称
    @Select("select wUserName,jobName,firmName,jobRequire,treatment,applyDate,applyProcess,uMessage,bMessage from applies inner join jobs on applies.jobId=jobs.id where wUserName=#{wUserName}")
    public List<ApplyAndJob> findApplyJobByWUsername(String wUserName);
    //2多表查询,apply 和 job 表,根据企业用户名
    @Select("select applies.id as id,wUserName,applies.bUserName,jobName,firmName,jobRequire,treatment,applyDate,applyProcess,uMessage,bMessage from applies inner join jobs on applies.jobId=jobs.id where applies.bUsername=#{bUserName}")
    public List<ApplyAndJob> findApplyJobByBUsername(String bUserName);
}
