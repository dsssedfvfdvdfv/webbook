package com.nghiemn.controller;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.nghiemn.dao.BillDao;
import com.nghiemn.dao.ClasscifyDao;
import com.nghiemn.dao.CustomerDao;
import com.nghiemn.dao.ProducerDao;
import com.nghiemn.dao.ProductDao;
import com.nghiemn.entity.*;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	CustomerDao dao;
	@Autowired
	BillDao billdao;
	@Autowired
	ProductDao productdao;
	@Autowired
	ClasscifyDao classcifidao;
	@Autowired
	ProducerDao producerdao;
	@Autowired
	HttpSession session;
	@Autowired
	private ServletContext servletContext;

	@GetMapping("customers")
	public String form() {		
		return "customermanager";
	}

	@RequestMapping("formuser")
	public String adduser() {
		return "formadd-user";
	}
	@Transactional
	@PostMapping("add-user")
	public String user(@RequestParam("account") String account, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("adress") String adress,
			@RequestParam("sodienthoai") String sdt, @RequestParam("role") boolean role) {
//		Customer customer = new Customer();
//		customer.setTendangnhap(account);
//		customer.setMatkhau(password);
//		customer.setDiachi(adress);
//		customer.setHoten(name);
//		customer.setSdt(sdt);
//		customer.setVaitro(role);
//
//		dao.save(customer);
		dao.updateInfomation(account, name, adress, sdt,role);
		return "redirect:/admin/customer";
	}
	
	@RequestMapping("removeuser/{id}")
	public String removeuser(@PathVariable("id") String idkhachhang) {
		dao.deleteById(idkhachhang);
		return "redirect:/admin/customer";
	}

	@RequestMapping("bill")
	public String bill(Model model, @RequestParam("p") Optional<Integer> p) {		
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		Page<Bill> page = billdao.findAll(pageable);
		model.addAttribute("items", page);
		return "billmanager";
	}
	@RequestMapping("removebill/{id}")
	public String idill(@PathVariable("id") String iddonhang) {
		billdao.deleteById(iddonhang);
		return "redirect:/admin/bill";
	}
	@RequestMapping("update-bill/{idbill}")
	public String updatebill(Model model,@PathVariable("idbill")int idbill) {
		Bill bill=billdao.findByIddonhang(idbill);
		model.addAttribute("bill", bill);
		return"form-pay";
	}
	@RequestMapping("productslist")
	public String product(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		Page<Product> page = productdao.getProductsInfo(pageable);
		model.addAttribute("products", page);
		return "productmanager";
	}

	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("user");
		return "redirect:/page/main";
	}

	@RequestMapping("producer")
	public String producerform() {			
		return "producermanager";
	}

	@RequestMapping("formproducer")
	public String formproducer() {
		return "formadd-producer";
	}
	@RequestMapping("update-producer/{tennhasx}")
	public String updateproducer(Model model,@PathVariable("tennhasx") String name) {
		Producer producer = producerdao.findByTennhasx(name);
		model.addAttribute("producer", producer);
		return "formadd-producer";
	}
	@Transactional
	@PostMapping("add-producer")
	public String addproducer(@RequestParam("producerName") String producername, @RequestParam("adress") String adress,
			@RequestParam("numberphone") String sdt) {
	
		producerdao.updateProducer(producername, adress, sdt);
		return "redirect:/admin/producer";
	}

	@RequestMapping("removeproducer/{id}")
	public String removeproducer(@PathVariable("id") int idnhasx) {
		producerdao.deleteById(idnhasx);
		return "redirect:/admin/producer";
	}

	@RequestMapping("form-addproduct")
	public String addproduct(Model model) {
		List<Classify> phanloai = classcifidao.findAll();
		List<Producer> nhasx = producerdao.findAll();
		model.addAttribute("producers", nhasx);
		model.addAttribute("categories", phanloai);
		return "formadd-product";
	}

	@PostMapping("add-product")
	public String productsadd(@RequestParam("productName") String name, @RequestParam("price") int price,
			@RequestParam("producer") int idnhasx, @RequestParam("productInfo") String info,
			@RequestParam("category") int idphanloai, @RequestParam("image") MultipartFile image) {

		Product pro = new Product();
		pro.setTensp(name);
		pro.setDongia(price);
		pro.setInfosanpham(info);
		pro.setNgaynhap(new Date());

		Optional<Producer> optionalProducer = producerdao.findById(idnhasx);
		Producer nhasanxuat = optionalProducer.orElse(null);
		pro.setNhasanxuat(nhasanxuat);

		Optional<Classify> optionalClassify = classcifidao.findById(idphanloai);
		Classify phanloai = optionalClassify.orElse(null);
		pro.setPhanloai(phanloai);

		String imagePath = saveImage(image);
		pro.setHinh(imagePath);

		productdao.save(pro);

		return "redirect:/admin/productslist";
	}

	@RequestMapping("remove/{id}")
	public String removeproduct(@PathVariable("id") int idsanpham) {
		productdao.deleteById(idsanpham);
		return "redirect:/admin/productslist";
	}

	private String saveImage(MultipartFile image) {
		// Tạo đường dẫn và tên file duy nhất cho hình ảnh
		String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
		String basePath = servletContext.getRealPath("/"); // Đường dẫn cơ sở của ứng dụng
		String imagePath = basePath + "/img/anhsanpham/" + imageName; // Đường dẫn tương đối

		try {
			// Lưu hình ảnh vào thư mục
			byte[] imageData = image.getBytes();
			Files.write(Paths.get(imagePath), imageData);

			return imageName; // Trả về tên file để có thể sử dụng trong đường dẫn hiển thị hình ảnh
		} catch (IOException e) {
			e.printStackTrace();
			System.out.print(e);
		}

		return null; // Trả về null nếu có lỗi xảy ra
	}
	@PostMapping("find-user")
	public String find(Model model,@ModelAttribute("tendangnhap")String tendangnhap) {
		Customer cus=dao.findByTendangnhap(tendangnhap);
		model.addAttribute("items", cus);
		return "customermanager";
	}
	@RequestMapping("update-usser/{tendangnhap}")
	public String updateuser(Model model,@PathVariable("tendangnhap") String username) {
		Customer user = dao.findByTendangnhap(username);
		System.out.print(user);
		model.addAttribute("account", user);
		return "formadd-user";
	}
}
