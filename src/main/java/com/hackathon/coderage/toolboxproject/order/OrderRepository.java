package com.hackathon.coderage.toolboxproject.order;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

  List<Order> findAll();

  List<Order> findAllByCustomerUsername(String username);

  List<Order> findAllByDate(long date);
}
