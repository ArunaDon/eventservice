package com.microservice.demo.elastic.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.microservices.demo.config.ElasticConfigData;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.Objects;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.microservice.demo.elastic.index.client.repository")
public class ElasticsearchConfig {

    private final ElasticConfigData elasticConfigData;

    public ElasticsearchConfig(ElasticConfigData configData) {
        this.elasticConfigData = configData;
    }

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        UriComponents serverUri = UriComponentsBuilder.fromHttpUrl(elasticConfigData.getConnectionUrl()).build();
        RestClientBuilder builder = RestClient.builder(new HttpHost(
                Objects.requireNonNull(serverUri.getHost()),
                serverUri.getPort(),
                serverUri.getScheme()
        )).setRequestConfigCallback(requestConfigBuilder ->
                requestConfigBuilder
                        .setConnectTimeout(elasticConfigData.getConnectionTimeout())
                        .setSocketTimeout(elasticConfigData.getSocketTimeout())
        );

        return new RestHighLevelClient(builder);
    }

    public ElasticsearchOperations elasticsearchTemplate() {
        return null;
    }
}