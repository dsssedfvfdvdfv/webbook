package com.nghiemn.entity;

import java.io.Serializable;
import java.util.Date;



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
@Table(name="donhang")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Bill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer iddonhang;
	String idkhachhang;
	Date ngaytao;
	Integer tongtien;
}
