package cn.itcast.dao;

import cn.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface IUserDao {
    @Select("select * from workers where username=#{username}")
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from bosses where username=#{username}")
    public UserInfo findByUsernameBosses(String username) throws Exception;

    @Select("select * from admins where username=#{username}")
    public UserInfo findByUsernameAdmins(String username) throws Exception;
}
