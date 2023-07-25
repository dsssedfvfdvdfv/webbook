package com.nghiemn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiemn.dao.ClasscifyDao;
import com.nghiemn.dao.CustomerDao;

import com.nghiemn.dao.ProductDao;
import com.nghiemn.entity.Classify;
import com.nghiemn.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.PageRequest;
@Controller
@RequestMapping("page")
public class ProductDetailController {
	@Autowired
	ProductDao dao;
	
	@Autowired
	ClasscifyDao daopl;
	
	List<Product> pro = new ArrayList<>();
	List<Product> proSortID = new ArrayList<>();
	List<Product> proSortRan = new ArrayList<>();
	@RequestMapping("product-detail/{id}")
	public String prouductDetail(@PathVariable("id") int id, Model model,
			@RequestParam("p") Optional<Integer> p	) {
		//Load chi tiết sẩn phẩm
		Product product = dao.findById(id).orElseThrow();
		//Load sản phẩm cùng loại với chi tiết sản phẩm
		int iDPhanLoai = product.getIdphanloai();
		System.out.print(id);
		List<Product> proSortID = dao.findAllByPhanLoai(iDPhanLoai);
		Classify pl = daopl.findById(iDPhanLoai).orElseThrow();
		/*
		 * Pageable pageable = PageRequest.of(p.orElse(0), 4, Sort.by("idphanloai"));
		 * Page<Product> page = dao.findAll(pageable); model.addAttribute("page", page);
		 */
		Pageable pageable = PageRequest.of(p.orElse(0), 4);
	    Page<Product> page = dao.findAll(pageable);
	    model.addAttribute("page", page);
		model.addAttribute("phanloai",pl);
		model.addAttribute("proSortID",proSortID);
		model.addAttribute("product",product);
		
		return"product-detail";
	}
	@Autowired
	private void prouductDetail() {
		// TODO Auto-generated method stub

	}
	@RequestMapping("pagepation")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p,@ModelAttribute(name="idsanpham") int id) {
		/* Optional<Integer> p = Optional.of(0); */
	
	    Pageable pageable = PageRequest.of(p.orElse(0), 4);
	    Page<Product> page = dao.findAll(pageable);
	    model.addAttribute("page", page);
	    System.out.print(page);
		return"redirect:/page/product-detail/{id}";
	}
}
