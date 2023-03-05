package com.music.recommendation.controller;

import com.music.commons.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RecommendationController {
    @RequestMapping(value = "/recomends",method = RequestMethod.POST)
    public Result getRecommendations(HttpServletRequest request){
        System.out.println("request = " + request);
        return null;
    }
}
