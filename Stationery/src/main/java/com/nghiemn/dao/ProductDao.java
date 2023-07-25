package com.nghiemn.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.nghiemn.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	@Query("SELECT a.idsanpham, a.tensp, b.tenphanloai, a.ngaynhap, a.dongia, c.tennhasx, a.infosanpham, a.hinh " +
		       "FROM Product a " +
		       "JOIN a.phanloai b " +
		       "JOIN a.nhasanxuat c")

	Page<Product> getProductsInfo(Pageable pageable);
	@Query(value = "SELECT * FROM product WHERE idphanloai = ?1",nativeQuery = true)
	List<Product> findAllByPhanLoai(int iDPhanLoai);
	@Query(value = "SELECT * FROM product WHERE TenSP like %?1% or mota like %?1%",nativeQuery = true)
	Page<Product> SeachProducts	(String keyWork, Pageable pageable);
	@Query(value = "SELECT * FROM Product WHERE tensp = N:butDen", nativeQuery = true)
	Product findByTensp(@Param("butDen") String butDen);



}
