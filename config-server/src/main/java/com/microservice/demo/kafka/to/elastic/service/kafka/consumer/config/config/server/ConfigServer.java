package com.microservice.demo.kafka.to.elastic.service.kafka.consumer.config.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServer {
    public static void main(String [] args){
        SpringApplication.run(ConfigServer.class,args);
    }
}
