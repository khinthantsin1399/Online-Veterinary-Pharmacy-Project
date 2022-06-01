package scm.ojt.project.web.form;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.persistence.entity.OrderDetail;

@Getter
@Setter
public class OrderDetailForm {
	private int id;
	private int o_id;
	private int m_id;
	private int m_quantity;
	private double amount;
	private Medicine medicine;

	public OrderDetailForm() {
		super();
	}

	public OrderDetailForm(OrderDetail orderDetail) {
		super();
		this.id = orderDetail.getId();
		this.o_id = orderDetail.getO_id();
		this.m_id = orderDetail.getM_id();
		this.m_quantity = orderDetail.getM_quantity();
		this.amount = orderDetail.getAmount();
		this.medicine = orderDetail.getMedicine();
	}
}
