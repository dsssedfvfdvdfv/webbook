package com.nghiemn.controller;

import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nghiemn.dao.CustomerDao;
import com.nghiemn.entity.Customer;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/form")
public class LoginController {
	@Autowired
	CustomerDao dao;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;
	@Autowired
	EmailSender emailSender;

	@RequestMapping("main")
	public String display(HttpServletRequest req, Model model) {
		Customer tendangnhapSession = (Customer) session.getAttribute("user");
		if (tendangnhapSession != null) {
			model.addAttribute("user", tendangnhapSession.getTendangnhap());
		} else {
			model.addAttribute("user", "Tài khoản");
		}
		if (tendangnhapSession == null) {
			return "redirect:/page/main";
		}
		
		return "main";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute(name = "username") String tendangnhap,
			@ModelAttribute(name = "password") String matkhau, Model model, HttpSession session) {
		Customer user = dao.findByTendangnhap(tendangnhap);

		if (user != null && user.getMatkhau().equals(matkhau)) {
			if (user.isVaitro()) {
				session.setAttribute("user", user);
				return "redirect:/admin/customers";
			} else {
				// Kiểm tra định dạng email trước khi đăng nhập
				if (isValidEmail(tendangnhap)) {
					session.setAttribute("user", user);
					return "redirect:/form/main";
				} else {
					model.addAttribute("error", "Invalid email format");
					return "login";
				}
			}
		} else {
			// Đăng nhập thất bại, hiển thị thông báo lỗi
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	@RequestMapping("/register")
	public String register(Model model) {
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		Customer existingUser = dao.findByTendangnhap(username);
		if (existingUser != null) {
			model.addAttribute("error", "Tài khoản đã tồn tại");
			return "register";
		}
		Customer customer = new Customer();
		customer.setTendangnhap(username);
		customer.setMatkhau(password);
		customer.setVaitro(false);
		dao.save(customer);
		String subject = "Đăng ký thành công";
		String content = "Chúc mừng bạn đã đăng ký thành công!";
		emailSender.sendEmail(username, subject, content);
		return "redirect:/page/login";
	}

	@PostMapping("/verify")
	public String sendVerificationCode(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password, RedirectAttributes redirectAttributes,
			@RequestParam("confirm") String confirm) {
		if (!password.equals(confirm)) {
			model.addAttribute("error", "Xác nhận mật khẩu không trùng khớp");
			return "register";
		} else {
			String verificationCode = generateVerificationCode();
			String subject = "Mã xác nhận đăng ký";
			String content = "Mã xác nhận của bạn là: " + verificationCode;
			emailSender.sendEmail(username, subject, content);
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			redirectAttributes.addFlashAttribute("email", username);
			session.setAttribute("verificationCode", verificationCode);
			return "redirect:/form/confirm";
		}

	}

	@GetMapping("/confirm")
	public String showConfirmationForm(@ModelAttribute("email") String email) {
		return "confirm";
	}

	@PostMapping("/form/confirm")
	public String confirmAccount(Model model, @RequestParam("verificationcodee") String verificationCode,
	        HttpSession session) {
	    String storedVerificationCode = (String) session.getAttribute("verificationCode");
	    if(verificationCode.isEmpty()) {
	    	model.addAttribute("error", "Vui lòng nhập mã xác nhận");
	    	 return "confirm";
	    }else if (verificationCode.equals(storedVerificationCode)) {
	        String username = (String) session.getAttribute("username");
	        String password = (String) session.getAttribute("password");
	        session.removeAttribute("verificationCode");	      
	        return "redirect:/form/register";
	    } else {
	        // Mã xác nhận không đúng, hiển thị thông báo lỗi
	        model.addAttribute("error", "Mã xác nhận không đúng");
	        return "confirm";
	    }
	  
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {		
		session.removeAttribute("user"); // Xóa session hiện tại
		return "redirect:/page/main";
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	
	private String generateVerificationCode() {
		Random random = new Random();
		StringBuilder code = new StringBuilder();
		int codeLength = 6;

		for (int i = 0; i < codeLength; i++) {
			int digit = random.nextInt(10); // Sinh số ngẫu nhiên từ 0 đến 9
			code.append(digit);
		}

		return code.toString();
	}

	@PostMapping("/forgot-password")
	public String sendVerification(Model model, @ModelAttribute("email") String username,
			RedirectAttributes redirectAttributes) {

		String verificationCode = generateVerificationCode();
		String subject = "Mã xác nhận đăng ký";
		String content = "Mã xác nhận của bạn là: " + verificationCode;
		emailSender.sendEmail(username, subject, content);
		session.setAttribute("username", username);
		redirectAttributes.addFlashAttribute("code", username);
		session.setAttribute("verificationCode", verificationCode);		
		return "redirect:/form/confirm-change";
	}

//	@PostMapping("/confirmcode-change")
//	public String confirmChange(@RequestParam("verificationCode") String verificationCode,
//			RedirectAttributes redirectAttributes) {
//
//		if (isValidVerificationCode(verificationCode)) {
//			return "redirect:/form/form-change";
//		} else {
//			redirectAttributes.addFlashAttribute("error", "Mã code không hợp lệ");
//			return "redirect:/form/confirm";
//		}
//	}
	@PostMapping("/confirmcode-change")
	public String confirmChange(Model model, @RequestParam("verificationcodee") String verificationCode,
	        HttpSession session) {
	    String storedVerificationCode = (String) session.getAttribute("verificationCode");
	    if(verificationCode.isEmpty()) {
	    	  model.addAttribute("error", "Không được để trống mã để xác nhận");
		        return "confirm-change";
	    }else  if (verificationCode.equals(storedVerificationCode)) {
	        String username = (String) session.getAttribute("username");	   
	        session.removeAttribute("verificationCode");	      
	        return "redirect:/form/form-changepassword";
	    } else {
	        // Mã xác nhận không đúng, hiển thị thông báo lỗi
	        model.addAttribute("error", "Mã xác nhận không đúng");
	        return "confirm-change";
	    }
	 
	}
	@GetMapping("/confirm-change")
	public String showConfirmation(@ModelAttribute("code") String email) {
		return "confirm-change";
	}

	@RequestMapping("form-change")
	public String change() {
		return "changepassword";
	}
	@RequestMapping("/form-changepassword")
	public String formchange() {
		return"changepassword";
	}
	
	@Transactional
	@PostMapping("/change-password")
	public String updatepassword(@ModelAttribute("newPassword") String newpassword,
			@ModelAttribute("confirmPassword") String confirm, Model model) {
		String user = (String) session.getAttribute("username");
		if (!newpassword.equals(confirm)) {
			model.addAttribute("error", "Xác thực không đúng");
			return "redirect:/form/form-changepassword";
		} else {
			dao.updatePassword(user, newpassword);
			String subject = "Đổi mật khẩu thành công";
			String content = "Bạn đã đổi mật khẩu thành công!";
			emailSender.sendEmail(user, subject, content);
			return "redirect:/page/login";
		}

	}
}
