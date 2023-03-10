package com.music.search;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.music.elasticsearch.config.ElasticSearchConfig;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = {MusicSearchServerApplication.class})
@RunWith(SpringRunner.class)
public class AppTest 
{
    @Resource
    ElasticsearchClient client;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void exitsIndex() throws IOException {
        ElasticSearchConfig elasticSearchConfig = new ElasticSearchConfig();
        elasticSearchConfig.elasticSearchConfig();
        System.out.println(client);
        BooleanResponse booleanResponse = client.indices().exists(e-> e.index("songdetail"));
        System.out.println(booleanResponse.value());
    }

}
