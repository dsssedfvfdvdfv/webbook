package com.nghiemn.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nghiemn.DTO.ProducerDTO;

import com.nghiemn.dao.ProducerDao;
import com.nghiemn.entity.Producer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
@CrossOrigin("*")
@RestController
public class ProducerRestAPI {
	@Autowired
	ProducerDao dao;
	@GetMapping("rest/producers")
	public List<ProducerDTO> getAll(@RequestParam(defaultValue = "0") int page,
	                                @RequestParam(defaultValue = "5") int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Producer> producerPage = dao.findAll(pageable);

	    List<ProducerDTO> producerDTOs = producerPage.getContent().stream()
	        .map(producer -> {
	            ProducerDTO dto = new ProducerDTO();
	            dto.setIdnhasx(producer.getIdnhasx());
	            dto.setTennhasx(producer.getTennhasx());
	            dto.setDiachi(producer.getDiachi());
	            dto.setSdt(producer.getSdt());
	            return dto;
	        })
	        .collect(Collectors.toList());

	    return producerDTOs;
	}
	@GetMapping("rest/producers/{idnhasx}")
	public ProducerDTO getOne(@PathVariable("idnhasx")int idnhasx) {
		  Producer producer = dao.findByIdnhasx(idnhasx);
		    if (producer == null) {
		    }

		    ProducerDTO producerDTO = new ProducerDTO();
		    producerDTO.setTennhasx(producer.getTennhasx());
		    producerDTO.setDiachi(producer.getDiachi());
		    producerDTO.setSdt(producer.getSdt());
		    return producerDTO;
	}
	@PostMapping("rest/producers")
	public Producer post(@RequestBody Producer producer) {
		dao.save(producer);
		return producer;
	}
}
