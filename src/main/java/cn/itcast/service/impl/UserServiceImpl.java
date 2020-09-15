package cn.itcast.service.impl;

import cn.itcast.dao.IUserDao;
import cn.itcast.domain.UserInfo;
import cn.itcast.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String json) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        ObjectMapper mapper = new ObjectMapper();
        String roleStr=null;
        try {
           UserInfo chooseRole = mapper.readValue(json, UserInfo.class);
           String role=chooseRole.getRole();

           String username=chooseRole.getUsername();
           if(role.equals("user")){
               userInfo=userDao.findByUsername(username);
               roleStr="ROLE_USER";

            }else if(role.equals("boss")){

               userInfo=userDao.findByUsernameBosses(username);
               roleStr="ROLE_BOSS";
            }else if(role.equals("admin")){

               userInfo=userDao.findByUsernameAdmins(username);
               roleStr="ROLE_ADMIN";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        封装成UserDetails,根据角色不同,给予不同权限

        User user=new User(json,"{noop}"+userInfo.getPassword(),getAuthority(roleStr));
        return user;
    }
    public List<SimpleGrantedAuthority> getAuthority(String roleStr){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleGrantedAuthority(roleStr));
        return list;
    }
}
