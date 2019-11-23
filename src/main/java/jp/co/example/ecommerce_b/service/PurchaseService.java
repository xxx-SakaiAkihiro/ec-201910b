package jp.co.example.ecommerce_b.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.ecommerce_b.repository.OrderItemRepository;

@Service
@Transactional
public class PurchaseService {

	@Autowired
	private OrderItemRepository repository;
	
	public void inserOrder(Order order) {
		repository.insert(order);
	}
}
