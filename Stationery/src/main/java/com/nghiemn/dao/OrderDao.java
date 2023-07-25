package com.nghiemn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghiemn.entity.Order;
@Repository
public interface OrderDao extends JpaRepository<Order,Integer> {

}
