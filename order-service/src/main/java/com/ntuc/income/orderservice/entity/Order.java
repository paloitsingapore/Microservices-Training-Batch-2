package com.ntuc.income.orderservice.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date orderDate;

}
