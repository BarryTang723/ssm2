package cn.itcast.dao;

import cn.itcast.domain.Admin;
import cn.itcast.domain.Worker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//交给ioc容器管理
@Repository
public interface AdminDao {
    //3通过用户名查一个admin
    @Select("select * from admins where username=#{username}")
    public List<Admin> findAdminByUsername(String username);
    //3注册admin
    @Insert("insert into admins (username,password) values (#{username},#{password})")
    public int addAdmin(Admin admin);
}
