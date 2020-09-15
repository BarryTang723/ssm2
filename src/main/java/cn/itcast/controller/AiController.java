package cn.itcast.controller;

import cn.itcast.util.face.ClientToken;
import cn.itcast.util.face.FaceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AiController {
//    private String token="24.22dee2f3ecb1f3f02c7324dc965d2f2e.2592000.1596940299.282335-21168303";
//    @RequestMapping("showToken")
//    @ResponseBody
//    public String showToken(){
//        System.out.println("show token...");
//        String ak="xwYrAt5w3qMC8AAf0xVfaRWp";
//        String sk="iA2q4Fby7Lx3GnQtr2KI6OK8AvboBoXj";
//        String token = ClientToken.getAuth(ak, sk);
//        return token;
//    }
//    @RequestMapping("showDetect")
//    @ResponseBody
//    public String showDetect() {
//        System.out.println("show detect----------------------------");
//        String result= FaceUtil.faceDetect(token);
//        return result;
//    }
//    @RequestMapping("showMatch")
//    @ResponseBody
//    public String showMatch() {
//        System.out.println("show detect----------------------------");
//        String result=FaceUtil.faceMatch(token);
//        return result;
//    }
}
