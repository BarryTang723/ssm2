package cn.itcast.test;

import cn.itcast.dao.*;
import cn.itcast.domain.*;
import cn.itcast.util.face.ClientToken;
import cn.itcast.util.face.FaceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestAi {
    String token="24.22dee2f3ecb1f3f02c7324dc965d2f2e.2592000.1596940299.282335-21168303";
    @Autowired
    private IntentionDao intentionDao;
    @Test
    public void testToken() {
        String token="24.22dee2f3ecb1f3f02c7324dc965d2f2e.2592000.1596940299.282335-21168303";
        String path="C:/Users/WDTMD/IdeaProjects/ssm/target/ssm/upload/tom.jpg";
        String res_json= FaceUtil.faceDetect(token,path);
        System.out.println(res_json);
        JSONObject jsonObject = JSON.parseObject(res_json);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("face_list");
        System.out.println(jsonArray);
        String face_probability = jsonArray.getJSONObject(0).getString("face_probability");
        System.out.println(face_probability);
        if(Float.parseFloat(face_probability)>0.7){
            System.out.println("哈哈");
        }else {
            System.out.println("呵呵");
        }

    }
    @Test
    public void testDetect() {

        System.out.println("show detect...");
        String path="C:/Users/WDTMD/IdeaProjects/ssm/target/ssm/upload/1.jpg";
        String res_json= FaceUtil.faceDetect(token,path);
        System.out.println(res_json);
        JSONObject jsonObject = JSON.parseObject(res_json);
        if(jsonObject.getString("error_code").equals("0")){
            JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("face_list");
            System.out.println(jsonArray);
            String face_probability = jsonArray.getJSONObject(0).getString("face_probability");
            System.out.println(face_probability);
            if(Float.parseFloat(face_probability)>0.7){
                System.out.println("结果1");
            }else {
                System.out.println("结果2");
            }
        }else {
            System.out.println("结果2");
        }



    }

    @Test
    public void test() throws IOException {
        //先存上
//        System.out.println("upper1----------------------------"+upfile.getOriginalFilename());
//        String path = request.getServletContext().getRealPath("/upload");
//        String fileName="login.jpg";
//        File saveFile=new File(path+"/"+fileName);
//        upfile.transferTo(saveFile);
        //对比
        String path="C:/Users/WDTMD/IdeaProjects/ssm/target/ssm/upload";
        String username="tom";

        String path1=path+"/"+username+".jpg";
        String path2=path+"/login.jpg";
        String res_json = FaceUtil.faceMatch(token, path1, path2);

        JSONObject jsonObject = JSON.parseObject(res_json);
        String score = jsonObject.getJSONObject("result").getString("score");
        System.out.println(score);
    }
    //    @Test
//    public void testMatch() {
//
//        System.out.println("show match...");
//        String result= FaceUtil.faceMatch(token);
////        System.out.println(result);
//
//    }
//
//    @Test
//    public void testMatch2() throws IOException {

//        System.out.println("show jedis..");
//        Jedis jd = new Jedis("192.168.0.102", 6379);
//        jd.auth("hello");
//        jd.set("intention","CSGO_professor");
//        System.out.println("--------------------------"+jd.keys("*"));
//        jd.close();
//        List<Intention> intentionList = intentionDao.findAll();
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(intentionList);
//        System.out.println(json);
//        Intention intention = mapper.readValue(json, Intention.class);
//        System.out.println(intention);
//    }
}
