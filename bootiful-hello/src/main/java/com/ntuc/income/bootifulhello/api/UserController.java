package com.ntuc.income.bootifulhello.api;

import com.ntuc.income.bootifulhello.entity.User;
import com.ntuc.income.bootifulhello.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired UserRepository userRepository;

  @GetMapping(path = "/users")
  public Iterable<User> getUsers(){
    return userRepository.findAll();
  }

  @GetMapping(path = "/user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id){
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
