package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.VersionBean;
import com.example.demo.utils.Logger;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppService {
    @GetMapping("/get_version")
    public String getVersion() {
        JSONObject object = new JSONObject();
        VersionBean versionBean = new VersionBean(App.VERSION);
        object.put("version", "1.0");
        return generateJson(object, "200", "成功", "");
    }


    /**
     * http://localhost:8081/api/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public String helloWorld() {
        JSONObject object = new JSONObject();
        object.put("content", "无参数GET请求");
        return generateJson(object, "200", "成功", "");
    }


    /**
     * http://localhost:8081/api/hello_id?id=777
     *
     * @param id
     * @return
     */
    @GetMapping("/hello_id")
    public String helloId(@RequestParam Integer id) {
        JSONObject object = new JSONObject();
        object.put("content", "请求参数 id = " + id);
        return generateJson(object, "200", "成功", "");
    }

    /**
     * http://localhost:8081/api/hello/999
     *
     * @param id
     * @return
     */
    @GetMapping("/hello/{id}")
    public String helloPathId(@PathVariable Integer id) {
        JSONObject object = new JSONObject();
        object.put("content", "请求参数 id = " + id);
        return generateJson(object, "200", "成功", "");
    }

    /**
     * http://localhost:8081/api/url1?id=1
     * http://localhost:8081/api/url2?id=2
     * 上面两个请求都走这个方法
     */
    @GetMapping({"/url1", "url2"})
    public String multiUrl(@RequestParam Integer id) {
        return "request param: " + id;
    }


    /**
     * http://localhost:8081/api/no-required
     * http://localhost:8081/api/no-required?id=3
     *
     * @param id
     * @return
     */
    @GetMapping("/no-required")
    public String noRequiredParam(@RequestParam(required = false, defaultValue = "0") Integer id) {
        return "request param: " + id;
    }


    @PostMapping("/hello_post")
    public String hello(@RequestBody Map params) {
        Logger.d("TEST", "来请求了");
        JSONObject object = new JSONObject();
        return generateJson(object, "200", "成功", "");
    }

    /**
     * http://10.133.46.17:8081/api/hello_post_new
     *
     * @param request
     * @return
     */
    @PostMapping("/hello_post_new")
    public String hello(HttpServletRequest request) {
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[App.POST_BUFF];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            System.out.println(sb.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", sb.toString());
            return generateJson(jsonObject, "200", "成功", "");
            //return "获取到的文本内容为：" + sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private String generateJson(JSONObject object, String resultCode, String resultMsg, String traceNo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", resultCode);//错误码
        jsonObject.put("resultMsg", resultMsg);//结果消息
        jsonObject.put("traceNo", traceNo);//traceNumber
        jsonObject.put("msg", object);//数据实体
        return jsonObject.toString();
    }


}
