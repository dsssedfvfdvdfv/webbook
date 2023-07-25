package com.nghiemn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nghiemn.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String> {
	Customer findByTendangnhap(String tendangnhap);

	@Modifying
	@Query("UPDATE Customer SET matkhau = :newPassword WHERE tendangnhap = :tendangnhap")
	void updatePassword(@Param("tendangnhap") String tendangnhap, @Param("newPassword") String newPassword);
	
	
	@Modifying
	@Query("UPDATE Customer SET hoten = :name, diachi = :adress, sdt = :numberphone WHERE tendangnhap = :tendangnhap")
	void updateInfo(
	    @Param("tendangnhap") String tendangnhap,
	    @Param("name") String username,
	    @Param("adress") String adress,
	    @Param("numberphone") String numberphone
	);
	
	@Modifying
	@Query("UPDATE Customer SET hoten = :name, diachi = :adress, sdt = :numberphone,vaitro= :vaitro WHERE tendangnhap = :tendangnhap")
	void updateInfomation(
	    @Param("tendangnhap") String tendangnhap,
	    @Param("name") String username,
	    @Param("adress") String adress,
	    @Param("numberphone") String numberphone,
	    @Param("vaitro")boolean vaitro
	);

}
