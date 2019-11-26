package jp.co.example.ecommerce_b.domain;

public class OrderTopping {

	private Integer id;
	private Integer toppingId;
	private Integer orderItemId;
	private Topping topping;

	@Override
	public String toString() {
		return "OrderTopping [id=" + id + ", toppingId=" + toppingId + ", orderItemId=" + orderItemId + ", topping="
				+ topping + "]";
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
	 * @return the toppingId
	 */
	public Integer getToppingId() {
		return toppingId;
	}

	/**
	 * @param toppingId the toppingId to set
	 */
	public void setToppingId(Integer toppingId) {
		this.toppingId = toppingId;
	}

	/**
	 * @return the orderItemId
	 */
	public Integer getOrderItemId() {
		return orderItemId;
	}

	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * @return the topping
	 */
	public Topping getTopping() {
		return topping;
	}

	/**
	 * @param topping the topping to set
	 */
	public void setTopping(Topping topping) {
		this.topping = topping;
	}

}
