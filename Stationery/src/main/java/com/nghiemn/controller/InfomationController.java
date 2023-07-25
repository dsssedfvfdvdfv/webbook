package com.nghiemn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiemn.dao.CustomerDao;
import com.nghiemn.entity.Customer;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("info")
public class InfomationController {
	@Autowired
	CustomerDao dao;
	@Autowired
	HttpSession session;

	@RequestMapping("form")
	public String form(Model model) {
		Customer tendangnhapSession = (Customer) session.getAttribute("user");
		if (tendangnhapSession != null) {
			model.addAttribute("user", tendangnhapSession.getTendangnhap());
		} else {
			model.addAttribute("user", "Tài khoản");
		}

		return "accountDetail";
	}

	@Transactional
	@PostMapping("save-info")
	public String save(Model model, @ModelAttribute("username") String username,
			@ModelAttribute("numberphone") String numberphone, @RequestParam("address") String address) {
		Customer user = (Customer) session.getAttribute("user");
		String tendangnhap = user.getTendangnhap();
		String numberphoneString = numberphone.replaceAll("[^0-9]", "");
	
			dao.updateInfo(tendangnhap, username, address, numberphoneString);
			return "redirect:/form/main";
		

	}

	@RequestMapping("/display-info/{tendangnhap}")
	public String searchInfo(@PathVariable("tendangnhap") String username, Model model) {
		Customer user = dao.findByTendangnhap(username);
		model.addAttribute("account", user);
		return "forward:/info/form";
	}

}
