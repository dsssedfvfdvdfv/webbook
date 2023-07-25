package com.nghiemn.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;


import com.nghiemn.dao.ProductDao;
import com.nghiemn.entity.Customer;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("page")
public class MainController {

	@Autowired
	ProductDao dao;
	@Autowired
	HttpSession session;


	@RequestMapping("main")
	public String main2() {
		return "main2";
	}

	@RequestMapping("login")
	public String login() {
		return "login";
	}

	@RequestMapping("register")
	public String register() {
		
		return "register";
	}

	@RequestMapping("accountdetail")
	public String accountDetail(Model model) {
		Customer user=(Customer) session.getAttribute("user");
		if(user==null) {
			return"redirect:/page/login";
		}
		Customer tendangnhapSession = (Customer) session.getAttribute("user");
		if (tendangnhapSession != null) {
			model.addAttribute("user", "Xin chào " + tendangnhapSession.getTendangnhap());
		} else {
			model.addAttribute("user", "Tài khoản");
		}
		return "accountDetail-nouser";
	}

	@RequestMapping("product-detail")
	public String prouduct() {
		return "product-detail";
	}
	@RequestMapping("forgot-password")
	public String forgot() {
		return"forgot-password";
	}
}
