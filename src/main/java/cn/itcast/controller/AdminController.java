package cn.itcast.controller;

import cn.itcast.dao.BossDao;
import cn.itcast.dao.WorkerDao;
import cn.itcast.domain.*;
import cn.itcast.util.face.FaceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private BossDao bossDao;
    //展示所有申请人json
    @RequestMapping("checkWorker.do")
    @ResponseBody
    public List<Worker> findAllJobs2(){
        List<Worker> workerList = workerDao.findAll();
        return workerList;
    }
    //根据id删除一个worker
    @RequestMapping("deleteWorker.do")
    @ResponseBody
    public String deleteWorker(Integer id){
        //TODO 有可能删除失败
        workerDao.deleteWorkerById(id);
        return "1";
    }
    //根据id修改一个worker
    @RequestMapping("upateWorker.do")
    public String upateWorker(Integer id, Model model){
        Worker worker = workerDao.findWorkerById(id);
        model.addAttribute("worker",worker);
        return "admin/upateWorker";
    }
    //增加一个worker
    @RequestMapping("addWorker.do")
    @ResponseBody
    public String addWorker(@RequestBody Worker worker){
        System.out.println(worker);
        workerDao.addWorkerPlus(worker);
        return "1";
    }
    //展示所有Boss(json)
    @RequestMapping("checkBoss.do")
    @ResponseBody
    public List<Boss> checkBoss(){
        List<Boss> bossList = bossDao.findAllBosses();
        return bossList;
    }
    //根据id删除一个boss
    @RequestMapping("deleteBoss.do")
    @ResponseBody
    public String deleteBoss(Integer id){
        //TODO 有可能删除失败
        bossDao.deleteBossById(id);
        return "1";
    }
    //根据id修改一个boss
    @RequestMapping("upateBoss.do")
    public String upateBoss(Integer id, Model model){
        Boss boss = bossDao.findBossById(id);
        model.addAttribute("boss",boss);
        return "admin/updateBoss";
    }
    //增加一个boss
    @RequestMapping("addBoss.do")
    @ResponseBody
    public String addBoss(@RequestBody Boss boss){
        System.out.println("seeyisee"+boss);
        bossDao.addBossPlus(boss);
        return "1";
    }

    //审核企业信息
    @RequestMapping("validateBoss.do")
    @ResponseBody
    public List<Boss> validateBoss(){
        //查所有未审核的企业
        List<Boss> bossList = bossDao.findBossByIsValid(0);
        return bossList;
    }
    //提交审核
    @RequestMapping("validateBossSubmit.do")
    @ResponseBody
    public String validateBossSubmit(Integer id){
        bossDao.validateBoss(id);
        return "1";
    }
    //上传照片
    @RequestMapping("upper1")
    @ResponseBody
    public String fun1(@RequestParam("file") MultipartFile upfile, HttpServletRequest request) throws Exception {
//        System.out.println("upper1----------------------------"+upfile.getOriginalFilename());
//        String path = request.getServletContext().getRealPath("/upload");
//        String fileName="upfile"+System.currentTimeMillis()+".jpg";
//        File saveFile=new File(path+"/"+fileName);
//        upfile.transferTo(saveFile);
//        return new ResponseCode(0, fileName);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        if (principal instanceof UserDetails) {
            String json=((UserDetails) principal).getUsername();
            ObjectMapper mapper = new ObjectMapper();
            UserInfo userInfo = mapper.readValue(json, UserInfo.class);
            username=userInfo.getUsername();
        }
//        String username="tom";
        System.out.println("upper1----------------------------"+upfile.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/upload");
        String fileName=username+".jpg";
        File saveFile=new File(path+"/"+fileName);
        upfile.transferTo(saveFile);
        //判断是否人脸
        String token="24.22dee2f3ecb1f3f02c7324dc965d2f2e.2592000.1596940299.282335-21168303";

        String res_json= FaceUtil.faceDetect(token,path+"/"+fileName);
        System.out.println(res_json);
        JSONObject jsonObject = JSON.parseObject(res_json);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("face_list");
        System.out.println(jsonArray);
        String face_probability = jsonArray.getJSONObject(0).getString("face_probability");
        System.out.println(face_probability);
        if(Float.parseFloat(face_probability)>0.7){
            return "1";
        }else {
            return "0";
        }
    }
    //提交对boss的修改
    @RequestMapping("submitBoss.do")
    @ResponseBody
    public String submitBoss(@RequestBody Boss boss){
        System.out.println(boss);
        bossDao.updateBossById(boss);
        return "1";
    }
}
