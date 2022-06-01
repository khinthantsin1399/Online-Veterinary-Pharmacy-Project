package scm.ojt.project.web.form;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>OrderDetailForm Class</h2>
 * <p>
 * Process for Displaying OrderDetailForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
public class OrderDetailForm {
	/**
	 * <h2>id</h2>
	 * <p>
	 * id
	 * </p>
	 */
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
	private Medicine medicine;

	/**
	 * <h2>Constructor for OrderDetailForm</h2>
	 * <p>
	 * Constructor for OrderDetailForm
	 * </p>
	 */
	public OrderDetailForm() {
		super();
	}

	/**
	 * <h2>Constructor for OrderDetailForm</h2>
	 * <p>
	 * Constructor for OrderDetailForm
	 * </p>
	 * 
	 * @param orderDetail
	 */
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