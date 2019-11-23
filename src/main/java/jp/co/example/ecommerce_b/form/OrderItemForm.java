package jp.co.example.ecommerce_b.form;

import java.util.List;

import jp.co.example.ecommerce_b.domain.Item;
import jp.co.example.ecommerce_b.domain.OrderTopping;

/**
 * 商品を追加する際に使用するフォーム.
 * 
 * @author iidashuhei
 *
 */
public class OrderItemForm {

	/* ID */
	private String id;
	/* ItemID */
	private String itemId;
	/* OrderID */
	private String orderId;
	/* 数量 */
	private String quantity;
	/* サイズ */
	private Character size;
	/* 商品 */
	private Item item;
	/* 注文商品リスト */
	private List<OrderTopping> orderToppingList;

	@Override
	public String toString() {
		return "OrderItemForm [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
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
