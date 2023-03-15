package com.music.socket.service.impl;

import com.music.socket.service.SearchSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("SearchSocketService")
// 一般来说@Transactional  注解，事务正常起作用。无异常时正常提交，有异常时数据回滚。
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true,rollbackFor = Exception.class)
public class SearchSocketServiceImpl implements SearchSocketService {
    @Resource
    ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public List<Object> searchElement(String condition) {
        return null;
    }
}
