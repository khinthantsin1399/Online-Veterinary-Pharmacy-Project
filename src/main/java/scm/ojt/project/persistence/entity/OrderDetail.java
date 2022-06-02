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

/**
 * <h2>OrderDetail Class</h2>
 * <p>
 * Process for Displaying OrderDetail
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {
	/**
	 * <h2>serialVersionUID</h2>
	 * <p>
	 * serialVersionUID
	 * </p>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <h2>id</h2>
	 * <p>
	 * id
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private int id;
	/**
	 * <h2>o_id</h2>
	 * <p>
	 * o_id
	 * </p>
	 */
	private int o_id;
	/**
	 * <h2>m_id</h2>
	 * <p>
	 * m_id
	 * </p>
	 */
	private int m_id;
	/**
	 * <h2>m_quantity</h2>
	 * <p>
	 * m_quantity
	 * </p>
	 */
	private int m_quantity;
	/**
	 * <h2>amount</h2>
	 * <p>
	 * amount
	 * </p>
	 */
	private double amount;
	/**
	 * <h2>medicine</h2>
	 * <p>
	 * medicine
	 * </p>
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "m_id", insertable = false, updatable = false, referencedColumnName = "id")
	private Medicine medicine;

	/**
	 * <h2>Constructor for OrderDetail</h2>
	 * <p>
	 * Constructor for OrderDetail
	 * </p>
	 */
	public OrderDetail() {
		super();
	}

	/**
	 * <h2>Constructor for OrderDetail</h2>
	 * <p>
	 * Constructor for OrderDetail
	 * </p>
	 * 
	 * @param orderDetailForm
	 */
	public OrderDetail(OrderDetailForm orderDetailForm) {
		super();
		this.id = orderDetailForm.getId();
		this.o_id = orderDetailForm.getO_id();
		this.m_id = orderDetailForm.getM_id();
		this.m_quantity = orderDetailForm.getM_quantity();
		this.amount = orderDetailForm.getAmount();
		this.medicine = orderDetailForm.getMedicine();
	}

	/**
	 * <h2>Constructor for OrderDetail</h2>
	 * <p>
	 * Constructor for OrderDetail
	 * </p>
	 * 
	 * @param cartDetail
	 */
	public OrderDetail(CartDetail cartDetail) {
		super();
		this.m_id = cartDetail.getMedicine().getId();
		this.m_quantity = cartDetail.getQuantity();
		this.amount = cartDetail.getAmount();
	}
}