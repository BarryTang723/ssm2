package cn.itcast.dao;

import cn.itcast.domain.Worker;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
//交给ioc容器管理
@Repository
public interface WorkerDao {
    //3查看所有求职者
    @Select("select * from workers")
    public List<Worker> findAll();
    //1注册
    @Insert("insert into workers (username,password) values (#{username},#{password})")
    public void addWorker(Worker worker);
    //注册是检测用户名是否可用
    @Select("select * from workers where username=#{username}")
    public List<Worker> findWorkerByUsername(String username);
    //1更改个人信息和简历
    @Update("update workers set password=#{password},realname=#{realname},sex=#{sex},phoneNum=#{phoneNum},email=#{email},education=#{education},intention=#{intention},resume=#{resume} where username=#{username}")
    public void updateResume(Worker worker);

    //2企业搜索求职者
    @Select("select * from Workers where education like '%${education}%'")
    public List<Worker> findWorkerByEdu(Worker worker);
    @Select("select * from Workers where intention like '%${intention}%'")
    public List<Worker> searchWorkerByItention(Worker worker);
//    @Select("select * from Workers where resume=#{resume}")
//    public List<Worker> searchWorkerByResume(Worker worker);
//    //2企业搜索求职者 多条件查询
//    @Select("select * from Workers where education=#{education}")
//    public List<Worker> searchWorkerByEduItention(Worker worker);
//    @Select("select * from Workers where intention=#{intention}")
//    public List<Worker> searchWorkerByEduResume(Worker worker);
//    @Select("select * from Workers where resume=#{resume}")
//    public List<Worker> searchWorkerByItentionResume(Worker worker);
    //3按id删除一个worker
    @Delete("delete from workers where id=#{id}")
    public void deleteWorkerById(Integer id);
    //3根据id查一个worker
    @Select("select * from workers where id=#{id}")
    public Worker findWorkerById(Integer id);
    //1增加一个worker
    @Insert("insert into workers values (null,#{username},#{password},#{realname},#{sex},#{phoneNum},#{email},#{education},#{intention},#{resume})")
    public void addWorkerPlus(Worker worker);
}
