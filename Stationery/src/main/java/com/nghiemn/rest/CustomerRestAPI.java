package com.nghiemn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghiemn.dao.CustomerDao;
import com.nghiemn.entity.Customer;

@CrossOrigin("*")
@RestController
public class CustomerRestAPI {
	@Autowired
	CustomerDao dao;
	@GetMapping("rest/customers")
	public List<Customer> getAll(Model model){
		return dao.findAll();
	}
}
