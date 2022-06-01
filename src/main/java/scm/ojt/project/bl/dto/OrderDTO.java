package scm.ojt.project.bl.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>OrderDTO Class</h2>
 * <p>
 * Process for Displaying OrderDTO
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Getter
@Setter
public class OrderDTO implements Serializable {
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
	 * <h2>Constructor for OrderDTO</h2>
	 * <p>
	 * Constructor for OrderDTO
	 * </p>
	 */
	public OrderDTO() {
		super();
	}

	/**
	 * <h2>Constructor for OrderDTO</h2>
	 * <p>
	 * Constructor for OrderDTO
	 * </p>
	 * 
	 * @param order
	 */
	public OrderDTO(Order order) {
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
		this.orderDetail = order.getOrderDetail();
	}
}