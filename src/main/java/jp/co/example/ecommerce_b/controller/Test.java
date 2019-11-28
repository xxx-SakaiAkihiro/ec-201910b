package jp.co.example.ecommerce_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.ecommerce_b.domain.CreditCard;
import jp.co.example.ecommerce_b.domain.CreditCardData;
import jp.co.example.ecommerce_b.service.PurchaseService;

@Controller
@RequestMapping("/test")
public class Test {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@RequestMapping("/credit")
	public String credit() {
		CreditCard form = new CreditCard(); 
		
		  form.setCard_name("takahiro");
		  form.setCard_number(1234567891234567l);
		  form.setCard_exp_year(2019);
		  form.setCard_exp_month(12);
		  form.setCard_cvv(123);
//		  form.setAmount(10000);
//		  form.setUserId(1);
//		  form.setOrder_number(12345678912345l);
		  System.err.println(form);
		
		CreditCardData creditCardData=purchaseService.creditCardCall(form);
		System.err.println(creditCardData);
		
		if ("error".equals(creditCardData.getMessage())) {
			System.out.println("false");
			return "foward:/purchase";
		}
		return null;
		
	}
	
	

}
