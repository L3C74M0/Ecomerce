package com.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderservice.entity.Order;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findByStatus(String status);

}
