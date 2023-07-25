package com.nghiemn.controller;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.nghiemn.entity.OrderDetail;

@Component
public class SendBill {
	  private final JavaMailSender mailSender;

	    public SendBill(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	    public void sendEmail(List<OrderDetail> orderdetails, String to, int totalAmount, String titel) {
	        String subject = "Danh sách hóa đơn sản phẩm";

	        StringBuilder content = new StringBuilder();
	        content.append("Danh sách hóa đơn sản phẩm:\n\n");
	        for (OrderDetail orderDetail : orderdetails) {
	            content.append("Sản phẩm: ").append(orderDetail.getProduct().getTensp()).append("\n");
	            content.append("Số lượng: ").append(orderDetail.getSoluongban()).append("\n");
	            content.append("Giá bán: ").append(orderDetail.getGiaban()).append("\n");
	            content.append("\n");
	        }

	        StringBuilder messageContent = new StringBuilder();
	        messageContent.append(titel).append("\n");
	        messageContent.append(content).append("\n");
	        messageContent.append("Total amount: ").append(totalAmount);

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(messageContent.toString());

	        mailSender.send(message);
	        System.out.println("Email sent successfully!");
	    }
}
