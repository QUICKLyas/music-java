package com.music.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 基于OpenFeign实现远程服务调用
 */
@FeignClient("music-details")
public interface MusicSongDetailService {
//    @GetMapping("/")
//    List<>
}
