package com.example.rest.webservices.restfulapiforsocialmedia.service;

import com.example.rest.webservices.restfulapiforsocialmedia.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
     private static final List<UserEntity> users = new ArrayList<>();
     static {
         users.add(new UserEntity(1, "Adam", LocalDate.now().minusYears(30)));
         users.add(new UserEntity(2, "Eve", LocalDate.now().minusYears(25)));
         users.add(new UserEntity(3, "Jim", LocalDate.now().minusYears(20)));
     }

     public List<UserEntity> findAll() {
         return users;
     }

     public UserEntity findById(Integer id) {
         Predicate<? super UserEntity> predicate = p -> p.getId().equals(id);
         return users.stream().filter(predicate).findFirst().orElse(null);
     }

     public void save(UserEntity user) {
         user.setId(users.size() + 1);
         users.add(user);
     }

     public void deleteById(Integer id) {
         users.removeIf(p -> p.getId().equals(id));
     }
}
