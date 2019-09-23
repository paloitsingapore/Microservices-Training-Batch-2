package com.ntuc.income.userservice.api;

import com.ntuc.income.userservice.entity.User;
import com.ntuc.income.userservice.repository.UserRepository;
import java.net.URI;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping("/user")
  public ResponseEntity saveOrCreateUser(@RequestBody @Valid User user){
    User savedUser = userRepository.save(user);
    URI uri = URI.create("http://localhost:8082/api/user/" + Long.toString(savedUser.getId()));
    return ResponseEntity.created(uri).build();
  }
}
