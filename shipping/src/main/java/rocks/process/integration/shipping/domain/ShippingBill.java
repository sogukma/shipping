package rocks.process.integration.shipping.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;






@Entity
public class ShippingBill {

	@Id
	@GeneratedValue
	private Long shippingBillId;
	private String trackingId;
	private Long customerId;
	private String orderId;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getTrackingId() {
		return trackingId;
	}
	
	public ShippingBill(Customer customer, String orderId, String trackingId, String status)
	{
		this.customerId = customer.getCustomerId();
		this.orderId = orderId;
		this.trackingId = trackingId;
		this.status = status;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public Long getShippingBillId() {
		return shippingBillId;
	}

	public void setShippingBillId(Long shippingBillId) {
		this.shippingBillId = shippingBillId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	
	
}
