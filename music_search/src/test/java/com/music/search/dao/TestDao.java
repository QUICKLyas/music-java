package com.music.search.dao;

import com.music.search.MusicSearchServerApplication;
import com.music.search.entity.User;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {MusicSearchServerApplication.class})
@RunWith(SpringRunner.class)
public class TestDao {
    @Resource
    private UserDao userDao;
    @Test
    public void  testSearch() {
        User user = new User();
        user.setId(1);
        user.setName("zzz");
//        userDao
        return;
    }
}
