package cts.udemy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cts.udemy.springboot.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
