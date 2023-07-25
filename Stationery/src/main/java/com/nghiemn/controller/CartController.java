 package com.nghiemn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiemn.dao.CustomerDao;
import com.nghiemn.dao.OrderDao;
import com.nghiemn.dao.OrderDetailDao;
import com.nghiemn.dao.ProductDao;
import com.nghiemn.entity.Cart;
import com.nghiemn.entity.Customer;
import com.nghiemn.entity.Order;
import com.nghiemn.entity.OrderDetail;
import com.nghiemn.entity.Product;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	ProductDao dao;
	@Autowired
	HttpServletRequest req;
	List<Product> cart = new ArrayList<>();
	@Autowired
	OrderDao orderdao;
	@Autowired
	HttpSession session;
	@Autowired
	CustomerDao customerdao;
	@Autowired
	OrderDetailDao orderdetaildao;
	@Autowired
	ProductDao productdao;
	@Autowired
	EmailSender emailSender;
	@Autowired
	SendBill sendbill;
	private Map<Integer, Cart> map = new HashMap<>();

	@RequestMapping("clear")
	public String clear(HttpSession session) {
		Customer user = (Customer) session.getAttribute("user");
		if (user == null) {
			return "redirect:/page/login";
		}

		Map<Integer, Cart> map = (Map<Integer, Cart>) session.getAttribute("cart"); // Lấy giỏ hàng từ session
		if (map != null) {
			map.clear(); // Xóa toàn bộ sản phẩm trong giỏ hàng
		}

		session.removeAttribute("cart"); // Xóa giỏ hàng khỏi session

		return "redirect:/cart/page-products";
	}

	@RequestMapping("buy/{idsanpham}")
	public String buy(@PathVariable("idsanpham") int id, Model model) {
		Customer user = (Customer) session.getAttribute("user");

		if (user == null) {
			return "redirect:/page/login";
		}

		boolean isProductInCart = map.containsKey(id);

		if (!isProductInCart) {
			Product product = dao.findById(id).orElseThrow();
			Cart cartItem = new Cart(product.getIdsanpham(), product.getTensp(), product.getHinh(), product.getDongia(),
					1);
			map.put(id, cartItem);
		} else {
			Cart cartItem = map.get(id);
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		}

		Collection<Cart> cartItems = map.values();
		model.addAttribute("cart", cartItems);
		session.setAttribute("cart", map);

		return "redirect:/cart/page-products";
	}

	@RequestMapping("remove/{idsanpham}")
	public String remove(@PathVariable("idsanpham") int id, Model model, HttpSession session) {
		Customer user = (Customer) session.getAttribute("user");
		Map<Integer, Cart> map;

		if (user == null) {
			return "redirect:/page/login";
		}

		// Kiểm tra xem có tồn tại giỏ hàng trong session hay không
		if (session.getAttribute("cart") == null) {
			map = new HashMap<>(); // Nếu không tồn tại, tạo mới đối tượng map
		} else {
			map = (Map<Integer, Cart>) session.getAttribute("cart"); // Nếu tồn tại, lấy giỏ hàng từ session
		}

		map.remove(id); // Xóa sản phẩm với id tương ứng khỏi giỏ hàng

		session.setAttribute("cart", map); // Lưu giỏ hàng đã được cập nhật vào session

		Collection<Cart> cartItems = map.values();
		model.addAttribute("cart", cartItems);

		return "redirect:/cart/page-products";
	}

	@RequestMapping("page-products")
	public String pageproduct(Model model, @RequestParam("p") Optional<Integer> p) {
		Customer tendangnhapSession = (Customer) session.getAttribute("user");

		if (tendangnhapSession != null) {
			model.addAttribute("user", "Xin chào " + tendangnhapSession.getTendangnhap());
		} else {
			model.addAttribute("user", "Tài khoản");
		}
		
		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		Page<Product> page = dao.findAll(pageable);
		model.addAttribute("products", page);
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		List<Cart> cartItems = new ArrayList<>(cart.values()); // Lấy danh sách sản phẩm từ giỏ hàng
		model.addAttribute("cart", cartItems); // Truyền danh sách sản phẩm vào model

		return "page-product";
	}
	
	@PostMapping("search-product")
	public String search(Model model,@ModelAttribute("nameproduct")String name) {
		Product pro=dao.findByTensp("bút đen");
		System.out.print(pro);
		model.addAttribute("products", pro);
		return "page-product";
	}
	@RequestMapping("page-products-no-user")
	public String pageproductnouser(Model model, @RequestParam("p") Optional<Integer> p) {

		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		Page<Product> page = dao.findAll(pageable);
		model.addAttribute("cart", cart);
		model.addAttribute("products", page);
		return "page-productnouser";
	}

	@GetMapping("go-cart")
	public String showPaymentPage(Model model) {
		Customer tendangnhapSession = (Customer) session.getAttribute("user");

		if (tendangnhapSession != null) {
			model.addAttribute("user", "Xin chào " + tendangnhapSession.getTendangnhap());
		} else {
			model.addAttribute("user", "Tài khoản");
		}
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		List<Cart> cartItems = new ArrayList<>(cart.values());
		model.addAttribute("cart", cartItems); 
		int totalAmount = (int) calculateTotalAmount();
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("totalAmount", totalAmount);
		session.setAttribute("total", totalAmount);
		session.setAttribute("cart", cart);
		return "shoppingcart";
		
	}

	@PostMapping("/placeOrder")
	public String placeOrder(Model model) {
		cart.clear();
		model.addAttribute("orderSuccess", true);
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		
		Customer user=(Customer) session.getAttribute("user");
		int idkhachhangsession = user.getIdkhachhang();
		int totalAmount = (int) session.getAttribute("total");		  
		String usename = user.getTendangnhap();
		Order order = new Order();
		order.setIdkhachhang(idkhachhangsession);
		order.setNgaytao(new Date());
		order.setTongtien(totalAmount);
		orderdao.save(order);   
		 List<OrderDetail> orderDetails = new ArrayList<>();
		 
		 for (Cart cartItem : cart.values()) {
			 int idsanpham = cartItem.getIdsanpham();
			    Optional<Product> optionalProduct = productdao.findById(idsanpham); 
			    Product product = optionalProduct.get();
		        OrderDetail orderDetail = new OrderDetail();		     
		        orderDetail.setOrder(order);
		        orderDetail.setProduct(product);
		        orderDetail.setSoluongban(cartItem.getQuantity());
		        orderDetail.setGiaban(cartItem.getDongia()*cartItem.getQuantity());		   
		        orderdetaildao.save(orderDetail);		      
		        orderDetails.add(orderDetail);
		    }
		
		String content = "Chúc mừng bạn " + usename + " đã đặt hàng thành công! ";		
		
		sendbill.sendEmail(orderDetails,usename,totalAmount,content);
		return "redirect:/form/main";
	}

	public double calculateTotalAmount() {
		int totalAmount = 0;
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		for (Cart cartItem : cart.values()) {
			double itemTotal = cartItem.getDongia() * cartItem.getQuantity();
			totalAmount += itemTotal;
		}

		return totalAmount;
	}
}
