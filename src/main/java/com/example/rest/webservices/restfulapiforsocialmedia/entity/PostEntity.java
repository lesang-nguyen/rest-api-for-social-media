package com.example.rest.webservices.restfulapiforsocialmedia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity(name = "post_detail")
public class PostEntity {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 10)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    protected PostEntity() {}
}
