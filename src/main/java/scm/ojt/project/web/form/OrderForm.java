package scm.ojt.project.web.form;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Order;

@Getter
@Setter
public class OrderForm {
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

	public OrderForm() {
		super();
	}

	public OrderForm(Order order) {
		super();
		this.id = order.getId();
		this.u_name = order.getU_name();
		this.u_phone = order.getU_phone();
		this.u_address = order.getU_address();
		this.date = order.getDate();
		this.status = order.getStatus();
		this.amount = order.getAmount();
		this.created_user_id = order.getCreated_user_id();
		this.created_at = order.getCreated_at();
		this.updated_user_id = order.getUpdated_user_id();
		this.updated_at = order.getUpdated_at();
		this.deleted_user_id = order.getDeleted_user_id();
		this.deleted_at = order.getDeleted_at();
	}
}