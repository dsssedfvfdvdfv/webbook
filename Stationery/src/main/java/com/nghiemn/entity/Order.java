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

import lombok.Setter;

@Data
@Table(name = "donhang")
@Getter
@Setter
@AllArgsConstructor

@Entity
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int iddonhang;
    int idkhachhang;
    Date ngaytao;
    int tongtien;

    // Constructor mặc định không tham số
    public Order() {
    }
}
