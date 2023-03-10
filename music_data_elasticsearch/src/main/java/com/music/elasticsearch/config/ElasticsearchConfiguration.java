package com.music.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;

@Configuration
@EnableElasticsearchRepositories // 可以使用JPA访问
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {
    @Value("${spring.elasticsearch.rest.uris}")
    private String uris;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(uris.split(","))
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(30))
                .build();
        return RestClients.create(configuration).rest();
    }

    @Bean("esRestTemplate")
    public ElasticsearchRestTemplate esRestTemplate() {
        return  new ElasticsearchRestTemplate(elasticsearchClient());
    }
}
