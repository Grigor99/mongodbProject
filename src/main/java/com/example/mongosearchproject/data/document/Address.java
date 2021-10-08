package com.example.mongosearchproject.data.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "address")
public class Address implements Serializable {

    @Id
    private String id;

    @Field(value = "zip_code")
    private String zipCode;

    @Field(value = "street")
    private String street;

    @Field(value = "city")
    private String city;

    @Field(value = "country")
    private String country;
}
