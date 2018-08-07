package com.xy365.web.service.impl;
import com.google.common.collect.Maps;
import com.xy365.core.model.User;
import com.xy365.core.properties.XyProperties;
import com.xy365.core.repository.UserRepository;
import com.xy365.web.service.UserService;
import com.xy365.web.util.ChineseNameUtil;
import com.xy365.web.wx.WxSession;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private XyProperties xyProperties;

    @Transactional
    @Override
    public Map<String, Object> registerOrLogin(WxSession wxSession){


        User user = userRepository.findOne((root,query,cb)->
                cb.and(cb.equal(root.get("openId"),wxSession.getOpenId()))
        ).map(x->userRepository.save(
                User.builder()
                        .id(x.getId())
                        .openId(x.getOpenId())
                        .anonymousName(x.getAnonymousName())
                        .anonymousAvatar(x.getAnonymousAvatar())
                        .nickname(x.getNickname())
                        .avatar(x.getAvatar())
                        .status(x.getStatus())
                .build()
        )).orElseGet(()->userRepository.save(
                User.builder()
                        .openId(wxSession.getOpenId())
                        .anonymousName(ChineseNameUtil.randomName())
                        .anonymousAvatar(xyProperties.getFileConfig().getImageServer()+"head_"+ RandomUtils.nextInt(1,10)+".png")
                        .status("0")
                        .build()
        ));

        // 模拟生成 3r_session
        String r_session = UUID.randomUUID().toString();

        // 放入缓存做登录状态
        redisTemplate.delete(wxSession.getExpired_session());

        redisTemplate.opsForValue().set(r_session,user,7, TimeUnit.DAYS);

        Map<String,Object> map = Maps.newHashMap();

        map.put("xy365_3rd_session",r_session);
        return map;
    }


}
