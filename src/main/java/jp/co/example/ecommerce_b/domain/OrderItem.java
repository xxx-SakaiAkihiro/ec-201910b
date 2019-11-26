package jp.co.example.ecommerce_b.domain;

import java.util.List;

public class OrderItem {
	
	/* ID */
	private Integer id;
	/* ItemID */
	private Integer itemId;
	/* OrderID */
	private Integer orderId;
	/* 数量 */
	private Integer quantity;
	/* サイズ */
	private Character size;
	/* 商品 */
	private Item item;
	/* 注文トッピングリスト */
	private List<Integer> orderToppingList;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}
	
	/**
	 * Mサイズを選んだ際の小計を表す.
	 * 
	 * MサイズとLサイズの小計を一緒にしたい
	 * 
	 * @return Mサイズを選んだ際の小計
	 */
//	public int getSubTotal() {
//		int totalToppingPriceM = 0;
//		int totalToppingPriceL = 0;
//		for( Integer toppings : orderToppingList ) {
//			Topping topping = toppings.();
//			totalToppingPriceM = totalToppingPriceM + topping.getPriceM();
//			totalToppingPriceL = totalToppingPriceL + topping.getPriceL();
//		}
//		if(item.getPriceM() != 0) {
//			return ( item.getPriceM() + totalToppingPriceM ) * quantity;			
//		} 
//		if(item.getPriceL() != 0) {
//			return ( item.getPriceM() + totalToppingPriceL ) * quantity;		
//		}
//		return 0;
//	}
	
	/**
	 * Lサイズを選んだ際の小計を表す.
	 * 
	 * @return Lサイズを選んだ際の小計
	 */
//	public int getSubTotalL() {
//		int totalToppingPrice = 0;
//		for( OrderTopping toppings : orderToppingList ) {
//			Topping topping = toppings.getTopping();
//			totalToppingPrice = totalToppingPrice + topping.getPriceL();
//		}
//		return ( item.getPriceL() + totalToppingPrice ) * quantity;
//	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the size
	 */
	public Character getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Character size) {
		this.size = size;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the orderToppingList
	 */
	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	/**
	 * @param orderToppingList the orderToppingList to set
	 */
	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
