package com.nghiemn.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Table(name = "khachhang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idkhachhang;
	String tendangnhap;
	String matkhau;
	String hoten;
	String diachi;
	String sdt;
	boolean vaitro;
	
}
