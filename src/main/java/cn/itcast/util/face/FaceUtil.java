package cn.itcast.util.face;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.人脸检测
 */
public class FaceUtil {
    public static String faceDetect(String accessToken,String path) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
//        	String path="D:/testFace/lai.jpg";
        	String img=Base64Util.encode(FileUtil.readFileByBytes(path));
        	
            Map<String, Object> map = new HashMap<>();
            map.put("image", img);
            map.put("face_field", "age,beauty,gender,face_type");
            map.put("image_type", "BASE64");

            String param = GsonUtils.toJson(map);


            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String faceMatch(String accessToken,String path1,String path2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
//        	String path="D:/testFace/dasi.jpg";

        	
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", Base64Util.encode(FileUtil.readFileByBytes(path1)));
            map1.put("image_type", "BASE64");

            String img=Base64Util.encode(FileUtil.readFileByBytes(path2));
            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", img);
            map2.put("image_type", "BASE64");
        	List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
        	maps.add(map1);
        	maps.add(map2);
            String param = GsonUtils.toJson(maps);


            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
