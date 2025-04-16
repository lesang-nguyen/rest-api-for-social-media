package com.example.rest.webservices.restfulapiforsocialmedia.repository;

import com.example.rest.webservices.restfulapiforsocialmedia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
