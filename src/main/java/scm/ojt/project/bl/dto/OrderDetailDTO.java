package scm.ojt.project.bl.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.persistence.entity.OrderDetail;

@Getter
@Setter
public class OrderDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int o_id;
	private int m_id;
	private int m_quantity;
	private double amount;
	private Medicine medicine;

	public OrderDetailDTO() {
		super();
	}

	public OrderDetailDTO(OrderDetail orderDetail) {
		super();
		this.id = orderDetail.getId();
		this.o_id = orderDetail.getO_id();
		this.m_id = orderDetail.getM_id();
		this.m_quantity = orderDetail.getM_quantity();
		this.amount = orderDetail.getAmount();
		this.medicine = orderDetail.getMedicine();
	}
}
