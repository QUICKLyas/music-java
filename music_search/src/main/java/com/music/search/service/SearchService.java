package com.music.search.service;

public interface SearchService {
    // 找寻数据songdetail
    // 初始化数据到ES，返回结果代表初始化是否成功
    boolean init();
}
