package com.nghiemn.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	int idsanpham;
	String tensp;
	String hinh;
	int dongia;
	int quantity=1;	
}
