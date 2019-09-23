package com.ntuc.income.orderservice.repository;

import com.ntuc.income.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRespository extends CrudRepository<Order, Long> {
}
