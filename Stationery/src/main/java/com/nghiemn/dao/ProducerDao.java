package com.nghiemn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nghiemn.entity.Producer;
@Repository
public interface ProducerDao extends JpaRepository<Producer, Integer> {
	Producer findByTennhasx(String tenhasx);
	Producer findByIdnhasx(int idnhasx);
	@Modifying
	@Query("UPDATE Producer SET diachi = :diachi, sdt = :sdt WHERE tennhasx = :tennhasx")
	void updateProducer(@Param("tennhasx") String tennhasx, @Param("diachi") String diachi, @Param("sdt") String sdt);

}
