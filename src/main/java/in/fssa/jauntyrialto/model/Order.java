package in.fssa.jauntyrialto.model;

import java.sql.Timestamp;
import in.fssa.jauntyrialto.enums.OrderStatus;

public abstract class Order implements Comparable<Order> {

	private int id;
	private int productId;
	private int userId;
	private int qty;
	private boolean isActive;
	private double total;
	private String landMark;
	private int pinCode;
	private String address;
	private Timestamp orderDate;
	private OrderStatus orderStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
//	public void setOrderStatus(String orderStatus) {
//	    // Convert the input String to the corresponding enum value
//	    this.orderStatus = OrderStatus.valueOf(orderStatus);
//	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", productId=" + productId + ", userId=" + userId + ", qty=" + qty + ", isActive="
				+ isActive + ", total=" + total + ", landMark=" + landMark + ", pinCode=" + pinCode + ", address="
				+ address + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + "]";
	}

	@Override
	public int compareTo(Order c) {

		if (this.getId() == c.getId()) {
			return 0;
		} else {
			if (this.id > getId()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
