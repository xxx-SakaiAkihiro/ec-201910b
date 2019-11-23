package jp.co.example.ecommerce_b.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import jp.co.example.ecommerce_b.domain.OrderItem;
import jp.co.example.ecommerce_b.domain.User;

/**
 * 注文する際に使用するフォーム.
 * 
 * @author iidashuhei
 *
 */
public class OrderForm {

	/* ID */
	private String id;
	/* UserId */
	private String userId;
	/* 状態 */
	private String status;
	/* 合計金額 */
	private String totalPrice;
	/* 注文日 */
	private Date orderDate;
	/* 宛先氏名 */
	private String destinationName;
	/* 宛先Eメール */
	private String destinationEmail;
	/* 宛先郵便番号 */
	private String destinationZipcode;
	/* 宛先住所 */
	private String destinationAddress;
	/* 宛先TEL */
	private String destinationTel;
	/* 配達時間 */
	private Timestamp deliveryTime;
	/* 支払方法 */
	private String paymentMethod;
	/* ユーザ */
	private User user;
	/* 注文したアイテム */
	private List<OrderItem> orderItemList;

	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderItemList=" + orderItemList + "]";
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the totalPrice
	 */
	public String getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return destinationName;
	}

	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	/**
	 * @return the destinationEmail
	 */
	public String getDestinationEmail() {
		return destinationEmail;
	}

	/**
	 * @param destinationEmail the destinationEmail to set
	 */
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	/**
	 * @return the destinationZipcode
	 */
	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	/**
	 * @param destinationZipcode the destinationZipcode to set
	 */
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	/**
	 * @return the destinationAddress
	 */
	public String getDestinationAddress() {
		return destinationAddress;
	}

	/**
	 * @param destinationAddress the destinationAddress to set
	 */
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	/**
	 * @return the destinationTel
	 */
	public String getDestinationTel() {
		return destinationTel;
	}

	/**
	 * @param destinationTel the destinationTel to set
	 */
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	/**
	 * @return the deliveryTime
	 */
	public Timestamp getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime the deliveryTime to set
	 */
	public void setDeliveryTime(Timestamp deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the orderItemList
	 */
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	/**
	 * @param orderItemList the orderItemList to set
	 */
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

}
