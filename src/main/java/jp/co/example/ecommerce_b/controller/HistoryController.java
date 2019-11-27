package jp.co.example.ecommerce_b.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class HistoryController {
	
	@RequestMapping("")
	public String index() {
		
		
		return "order_history.html";
	}
}
