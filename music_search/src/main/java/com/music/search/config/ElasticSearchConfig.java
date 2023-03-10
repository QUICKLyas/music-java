package com.music.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    @Value("${es.hostname}")
    private String hostname;
    @Value("${es.port}")
    private int port;

//    @Bean
//    public ElasticsearchClient elasticSearchConfig(){
//        System.out.println(hostname + port);
//        RestClient client = RestClient.builder(new HttpHost(hostname,port,"http")).build();
//        ElasticsearchTransport transport = new RestClientTransport(client,new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }
}
