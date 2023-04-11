package com.music.recommendation.dao;

import com.music.recommendation.MusicRecommendationApplication;
import com.music.recommendation.pojo.Recommendation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

@SpringBootTest(classes = {MusicRecommendationApplication.class})
@RunWith(SpringRunner.class)
public class TestDaoImpl {
    @Autowired
    private RecommendationDao recommendationDao;

    @Test
    public void TestRecommendSong() {
        List<Object> recommendationIdList=recommendationDao.getRecommendationS("1a78b764b67911ed95dd00155dadb10b",null,null);
        for (Object id : recommendationIdList) {
            System.out.println(id);
        }
    }
}
