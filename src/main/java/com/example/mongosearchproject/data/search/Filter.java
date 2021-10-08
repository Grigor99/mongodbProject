package com.example.mongosearchproject.data.search;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Filter  {

    public List<Criteria> nonNull(List<Criteria> criteriaList) {
        return criteriaList.stream().filter(Objects::nonNull).collect(Collectors.toList());

    }

}
