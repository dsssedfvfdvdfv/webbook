package com.nghiemn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghiemn.entity.Classify;

@Repository
public interface ClasscifyDao extends JpaRepository<Classify, Integer> {

}
