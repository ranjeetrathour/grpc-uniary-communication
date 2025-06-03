package com.example.repository;

import com.example.modle.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,String> {
    User findByUsername(String name);
}
