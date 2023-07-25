package com.nghiemn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsanpham")
	int idsanpham;

	String tensp;
	@Column(insertable=false, updatable=false)
	int idphanloai;
	Date ngaynhap;
	int dongia;
	@Column(insertable=false, updatable=false)
	int idnhasx;

	String infosanpham;
	String hinh;
	String nhaxuatban;
	String tacgia;
	String hinhthucbia;
	String nhacungcap;
	@ManyToOne
	@JoinColumn(name = "idnhasx")
	Producer nhasanxuat;

	@ManyToOne
	@JoinColumn(name = "idphanloai")
	Classify phanloai;
	
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderdetail;
}
