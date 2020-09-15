package cn.itcast.dao;

import cn.itcast.domain.Boss;
import cn.itcast.domain.Worker;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

//交给ioc容器管理
@Repository
public interface BossDao {
//    //2boss注册
//    @Insert("insert into bosses (username,password) values (#{username},#{password})")
    //2boss注册
    @Insert("insert into bosses values (null,#{username},#{password},#{firmName},#{phoneNum},#{email},#{industry},null,0,0)")
    public Integer addBoss(Boss boss);
    //2查看某个公司信息
    @Select("select * from bosses where username=#{username}")
    public List<Boss> findBossByUsername(String username);
    //2维护公司信息
    @Update("update bosses set firmName=#{firmName},phoneNum=#{phoneNum},email=#{email},industry=#{industry} where username=#{username}")
    public void updateBossInfo(Boss boss);
    //2付费模块
    @Update("update bosses set vipDate=#{vipDate},isVip=#{isVip} where username=#{username}")
    public void updateVipInfo(Boss boss);
    //3验证企业信息
    @Select("select * from bosses where isValid=#{isValid}")
    public List<Boss> findBossByIsValid(Integer isValid);
    //3企业验证通过
    @Update("update bosses set isValid=1 where id=#{id}")
    public void validateBoss(Integer id);
    //3根据id删除boss
    @Delete("delete from bosses where id=#{id}")
    public void deleteBossById(Integer id);
    //3根据id查一个boss
    @Select("select * from bosses where id=#{id}")
    public Boss findBossById(Integer id);
    //3改一条公司信息
    @Update("update bosses set username=#{username},firmName=#{firmName},phoneNum=#{phoneNum},email=#{email},industry=#{industry},vipDate=#{vipDate},isVip=#{isVip},isValid=#{isValid} where id=#{id}")
    public void updateBossById(Boss boss);
    //3增加一个boss
    @Insert("insert into bosses values (null,#{username},#{password},#{firmName},#{phoneNum},#{email},#{industry},#{vipDate},#{isVip},#{isValid})")
    public void addBossPlus(Boss boss);
    //3查看所有企业
    @Select("select * from bosses")
    public List<Boss> findAllBosses();
    //3更新vip日期
    @Update("update bosses set vipDate=date_add(vipDate, interval #{arg1} day)  where username=#{arg0}")
    public Integer updateVipDateByUsername(String username,Integer day);
}
