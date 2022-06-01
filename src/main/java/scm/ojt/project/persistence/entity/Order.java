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

/**
 * <h2>Order Class</h2>
 * <p>
 * Process for Displaying Order
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {
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
	 * <h2>u_name</h2>
	 * <p>
	 * u_name
	 * </p>
	 */
	private String u_name;
	/**
	 * <h2>u_phone</h2>
	 * <p>
	 * u_phone
	 * </p>
	 */
	private String u_phone;
	/**
	 * <h2>u_address</h2>
	 * <p>
	 * u_address
	 * </p>
	 */
	private String u_address;
	/**
	 * <h2>date</h2>
	 * <p>
	 * date
	 * </p>
	 */
	private Date date;
	/**
	 * <h2>status</h2>
	 * <p>
	 * status
	 * </p>
	 */
	private Boolean status;
	/**
	 * <h2>amount</h2>
	 * <p>
	 * amount
	 * </p>
	 */
	private double amount;
	/**
	 * <h2>created_user_id</h2>
	 * <p>
	 * created_user_id
	 * </p>
	 */
	private Integer created_user_id;
	/**
	 * <h2>created_at</h2>
	 * <p>
	 * created_at
	 * </p>
	 */
	private Date created_at;
	/**
	 * <h2>updated_user_id</h2>
	 * <p>
	 * updated_user_id
	 * </p>
	 */
	private Integer updated_user_id;
	/**
	 * <h2>updated_at</h2>
	 * <p>
	 * updated_at
	 * </p>
	 */
	private Date updated_at;
	/**
	 * <h2>deleted_user_id</h2>
	 * <p>
	 * deleted_user_id
	 * </p>
	 */
	private Integer deleted_user_id;
	/**
	 * <h2>deleted_at</h2>
	 * <p>
	 * deleted_at
	 * </p>
	 */
	private Date deleted_at;

	/**
	 * <h2>orderDetail</h2>
	 * <p>
	 * orderDetail
	 * </p>
	 */
	public OrderDetail orderDetail;

	/**
	 * <h2>Constructor for Order</h2>
	 * <p>
	 * Constructor for Order
	 * </p>
	 */
	public Order() {
		super();
	}

	/**
	 * <h2>Constructor for Order</h2>
	 * <p>
	 * Constructor for Order
	 * </p>
	 * 
	 * @param orderForm
	 */
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

	/**
	 * <h2>Constructor for Order</h2>
	 * <p>
	 * Constructor for Order
	 * </p>
	 * 
	 * @param cart
	 */
	public Order(Cart cart) {
		super();
		this.status = false;
		this.amount = cart.getAmount();
		this.created_user_id = cart.getCreated_user_id();
	}
}