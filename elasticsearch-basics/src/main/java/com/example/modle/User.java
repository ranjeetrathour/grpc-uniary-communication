package com.example.modle;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "users")
public class User {

    @Id
    private String uuid;

    private String username;
    private String userEmail;
    private int age;

    // Constructor
    public User(String username, String userEmail, int age) {
        this.username = username;
        this.userEmail = userEmail;
        this.age = age;
    }

    // Default constructor (optional)
    public User() {
    }

    // Getter and Setter methods
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
