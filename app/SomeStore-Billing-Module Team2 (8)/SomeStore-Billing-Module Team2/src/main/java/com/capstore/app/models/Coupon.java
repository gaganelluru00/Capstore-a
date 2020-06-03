package main.java.com.capstore.app.models;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {

	@Id
	@Column(name = "coupon_id")
	private int couponId;
	@Column(name = "coupon_code")
	private String couponCode;
	@Column(name = "user_id")
	private int userId; //(Foreign Key)
	@Column(name = "end_date")
    private String couponEndDate;
	@Column(name = "start_date")
    private String couponStartDate;
	@Column(name = "coupon_amount")
    private int couponAmount; 
	@Column(name = "min_order_amount")
    private int couponMinOrderAmount;
	@Column(name = "issued_by")
    private String issuedBy; //{“Admin”,”Merchant”}
    
	
	
	public Coupon(int couponId, String couponCode, int userId, String couponEndDate, String couponStartDate,
			int couponAmount, int couponMinOrderAmount, String issuedBy) {
		super();
		this.couponId = couponId;
		this.couponCode = couponCode;
		this.userId = userId;
		this.couponEndDate = couponEndDate;
		this.couponStartDate = couponStartDate;
		this.couponAmount = couponAmount;
		this.couponMinOrderAmount = couponMinOrderAmount;
		this.issuedBy = issuedBy;
	}



	public int getCouponId() {
		return couponId;
	}



	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}



	public String getCouponCode() {
		return couponCode;
	}



	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	



	public int getCouponAmount() {
		return couponAmount;
	}



	public void setCouponAmount(int couponAmount) {
		this.couponAmount = couponAmount;
	}



	public int getCouponMinOrderAmount() {
		return couponMinOrderAmount;
	}



	public void setCouponMinOrderAmount(int couponMinOrderAmount) {
		this.couponMinOrderAmount = couponMinOrderAmount;
	}



	public String getIssuedBy() {
		return issuedBy;
	}



	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}



	public Coupon() {
	} 
	
}
