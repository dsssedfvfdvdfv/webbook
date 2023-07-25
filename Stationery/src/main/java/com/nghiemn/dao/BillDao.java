package com.nghiemn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghiemn.entity.Bill;



@Repository
public interface BillDao extends JpaRepository<Bill, String> {
	Bill findByIddonhang(int iddonhang);
}
