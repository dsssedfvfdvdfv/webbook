package com.nghiemn.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Table(name = "phanloai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Classify implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	int idphanloai;
	String tenphanloai;
	@OneToMany(mappedBy = "phanloai")
	List<Product> product;
}
