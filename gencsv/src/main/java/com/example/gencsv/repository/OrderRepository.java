package com.example.gencsv.repository;

import com.example.gencsv.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT MAX(o.id) FROM orders o", nativeQuery = true)
    Integer findMaxId();
}
