package com.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.report.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	@GetMapping("/total_balance")
	public String getTotalBalance() throws Exception {
		
		
		return reportService.findTotalInvoiceAmount();
		
	}
	
	
	@GetMapping("/cust-invoice/{customer_data}")
	public String findInvoice(@PathVariable String customer_data) throws Exception {
		
		
		return reportService.findInvoiceByCustomer(customer_data);
		
	}
	
	
	@GetMapping("/cust-po/{customer_data}")
	public String findPO(@PathVariable String customer_data) throws Exception {
		
		
		return reportService.findPOByCustomer(customer_data);
		
	}

}
