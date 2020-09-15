package cn.itcast.dao;

import cn.itcast.domain.Intention;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//交给ioc容器管理
@Repository
public interface IntentionDao {
    //1所有目标职业
    @Select("select * from intention")
    public List<Intention> findAll();
}
