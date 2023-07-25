package com.nghiemn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghiemn.dao.OrderDao;
import com.nghiemn.entity.Order;

@CrossOrigin("*")
@RestController
public class OrderRestAPI {
	@Autowired
	OrderDao dao;
	@GetMapping("rest/orders")
	public List<Order> getAll(){
		return dao.findAll();
	}
}
