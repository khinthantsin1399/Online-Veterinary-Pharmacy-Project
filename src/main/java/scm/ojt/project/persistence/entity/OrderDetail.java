package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.web.form.OrderDetailForm;

@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	private int o_id;
	private int m_id;
	private int m_quantity;
	private double amount;
	@OneToOne(optional = false)
	@JoinColumn(name = "m_id", insertable = false, updatable = false, referencedColumnName = "id")
	private Medicine medicine;

	public OrderDetail() {
		super();
	}

	public OrderDetail(OrderDetailForm orderDetailForm) {
		super();
		this.id = orderDetailForm.getId();
		this.o_id = orderDetailForm.getO_id();
		this.m_id = orderDetailForm.getM_id();
		this.m_quantity = orderDetailForm.getM_quantity();
		this.amount = orderDetailForm.getAmount();
		this.medicine = orderDetailForm.getMedicine();
	}
}