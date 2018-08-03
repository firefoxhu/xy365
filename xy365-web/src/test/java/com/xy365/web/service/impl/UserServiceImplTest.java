package com.xy365.web.service.impl;

import com.xy365.core.model.User;
import com.xy365.core.repository.UserRepository;
import com.xy365.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserRepository  userRepository;

    @Test
    public void save() {

        User user = new User();
        user.setNickname("随风飘荡");
        user.setAvatar("http://image.luosen365.com/Article_2b142ba4e68b4312874dbb144656e0b7.jpg");
        user.setOpenId("6666666666666");

        userRepository.save(user);
    }

    @Test
    public void query() {
    }
}