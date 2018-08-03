package com.xy365.web.wx;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xy365.core.model.User;
import com.xy365.core.properties.XyProperties;
import com.xy365.web.domain.support.SimpleResponse;
import com.xy365.web.exception.UserException;
import com.xy365.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Autowired
    private XyProperties xyProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public SimpleResponse login(String code,String xy365_3rd_session){
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+xyProperties.getWxConfig().getAppId()+"&secret="+xyProperties.getWxConfig().getSecret()+"&js_code="+code+"&grant_type=authorization_code";
        try {
            URL weChatUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = weChatUrl.openConnection();
            // 设置通用的请求属性
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            // 建立实际的连接
            connection.connect();

            StringBuffer accessToken = new StringBuffer();
            try(
                    Reader in = new InputStreamReader(connection.getInputStream());
                    BufferedReader br =new BufferedReader(in)
            ){
                String line;
                while ((line = br.readLine()) != null) {
                    accessToken.append(line);
                }
            }

            // 获取openid
            WxSession wxSession = JSON.parseObject(accessToken.toString(),WxSession.class);
            wxSession.setExpired_session(xy365_3rd_session);

            return SimpleResponse.success(userService.registerOrLogin(wxSession));

            /*

            // 生成成3rd_session
            String[] cmdA = { "/bin/sh", "-c", "head -n 80 /dev/urandom | tr -dc A-Za-z0-9 | head -c 168" };

            try {

                Process process = Runtime.getRuntime().exec(cmdA);

                StringBuffer thirdSession = new StringBuffer();
                try(
                        Reader in = new InputStreamReader(process.getInputStream());
                        LineNumberReader br = new LineNumberReader(in)
                ){
                    String line;
                    while ((line = br.readLine()) != null) {
                        thirdSession.append(line).append("\n");
                    }

                }

                redisTemplate.opsForValue().set(thirdSession.toString(),"");

            } catch (IOException e) {
                e.printStackTrace();
            }

*/

        }catch (Exception e){
            e.printStackTrace();
        }
        return SimpleResponse.fail("处理失败");
    }
}
