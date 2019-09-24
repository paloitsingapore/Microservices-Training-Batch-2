package com.ntuc.income.orderservice.api;

import com.ntuc.income.orderservice.entity.Order;
import com.ntuc.income.orderservice.repository.OrderRespository;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  private final OrderRespository orderRespository;

  private final UserService userService;

  OrderController(OrderRespository orderRespository, UserService userService){
    this.orderRespository = orderRespository;
    this.userService = userService;
  }

  @GetMapping("/orders")
  public ResponseEntity placeOrder(@RequestParam Long userId){
    try {
      return ResponseEntity.ok(
          orderRespository.save(new Order( new Date(), userId )));
    } catch (Exception e){
      return ResponseEntity.status(
          HttpStatus.INTERNAL_SERVER_ERROR)
          .body(e.getMessage());
    }
  }

  @GetMapping("/orders/users")
  public String getUsers(){
    return
        userService.getUsers() ;
  }

  @FeignClient(name = "user-service")
  private interface UserService{
    @GetMapping("/api/users")
    public String getUsers();
  }

}
