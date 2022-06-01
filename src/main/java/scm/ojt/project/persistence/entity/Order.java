package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.web.form.OrderForm;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private String u_name;
	private String u_phone;
	private String u_address;
	private Date date;
	private Boolean status;
	private double amount;
	private Integer created_user_id;
	private Date created_at;
	private Integer updated_user_id;
	private Date updated_at;
	private Integer deleted_user_id;
	private Date deleted_at;
	
	public OrderDetail orderDetail;

	public Order() {
		super();
	}

	public Order(OrderForm orderForm) {
		super();
		this.id = orderForm.getId();
		this.u_name = orderForm.getU_name();
		this.u_phone = orderForm.getU_phone();
		this.u_address = orderForm.getU_address();
		this.date = orderForm.getDate();
		this.status = orderForm.getStatus();
		this.amount = orderForm.getAmount();
		this.created_user_id = orderForm.getCreated_user_id();
		this.created_at = orderForm.getCreated_at();
		this.updated_user_id = orderForm.getUpdated_user_id();
		this.updated_at = orderForm.getUpdated_at();
		this.deleted_user_id = orderForm.getDeleted_user_id();
		this.deleted_at = orderForm.getDeleted_at();
	}
}