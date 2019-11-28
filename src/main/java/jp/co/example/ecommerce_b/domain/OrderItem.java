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
	private List<OrderTopping> orderToppingList;

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	/**
	 * （トッピング＋商品） ×数量の値段
	 * 
	 * @return
	 */
	public int getSubTotal() {
		int subtotal = 0;
		int totalTopping = 0;
		// トッピング+商品 Mの価格×数量
		if (size.equals('M')) {
			for (OrderTopping topping : orderToppingList) {
				totalTopping += topping.getTopping().getPriceM();
			}
			subtotal = (totalTopping + item.getPriceM()) * quantity;
			// トッピング Lの価格×数量
		} else {
			for (OrderTopping topping : orderToppingList) {
				totalTopping += topping.getTopping().getPriceL();
			}
			subtotal += (totalTopping + item.getPriceL()) * quantity;
		}
		return subtotal;

	}

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
	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	/**
	 * @param orderToppingList the orderToppingList to set
	 */
	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

}
