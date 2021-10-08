package com.example.mongosearchproject;

/*
import com.github.cloudyrock.spring.v5.EnableMongock;
*/
import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoAuditing



@EnableMongoRepositories(basePackages = "com.example.mongosearchproject.repository")
@EnableMongock
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MongoSearchProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoSearchProjectApplication.class, args);
    }

}
