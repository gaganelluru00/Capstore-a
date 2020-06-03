package main.java.com.capstore.app.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@Column(name = "transaction_id")
	private int transactionId;
	@Column(name = "order_id")
    private int orderId;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "transaction_money")
    private int transactionMoney;
    @Column(name = "transaction_method")
    private String transactionMethod;  //(“Credit”,”Debit”,”UPI”,”Wallet”)
    @Column(name = "transaction_status")
    private String transactionStatus; //(“Success”,”Fail”,”Pending”)
	public int getTransactionId() {
		return transactionId;
	}
	
    
	

}
