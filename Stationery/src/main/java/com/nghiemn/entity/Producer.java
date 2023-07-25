package com.nghiemn.entity;

import java.io.Serializable;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Table(name = "nhasanxuat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int idnhasx;
	String tennhasx;
	String diachi;
	String sdt;
	@OneToMany(mappedBy = "nhasanxuat")
	List<Product> product;
}
