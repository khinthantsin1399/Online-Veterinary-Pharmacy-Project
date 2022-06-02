package scm.ojt.project.persistence.dao;

import java.util.List;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>OrderDao Class</h2>
 * <p>
 * Process for Displaying OrderDao
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface OrderDao {
	/**
	 * <h2>dbGetOrderList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<OrderDTO>
	 */
	List<OrderDTO> dbGetOrderList();

	/**
	 * <h2>dbGetOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @return
	 * @return List<OrderDetail>
	 */
	List<OrderDetail> dbGetOrderDetail(Integer orderId);

	/**
	 * <h2>dbGetOrderById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @return
	 * @return Order
	 */
	Order dbGetOrderById(Integer orderId);

	/**
	 * <h2>dbAcceptOrder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @return void
	 */
	void dbAcceptOrder(Integer orderId);

	/**
	 * <h2>dbCancelOrder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @param userId
	 * @return void
	 */
	void dbCancelOrder(Integer orderId, int userId);

	/**
	 * <h2>dbGetCartAndCheckOutByCartId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param cartId
	 * @return
	 * @return Cart
	 */
	Cart dbGetCartAndCheckOutByCartId(int cartId);

	/**
	 * <h2>dbSaveOrder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param order
	 * @return
	 * @return int
	 */
	int dbSaveOrder(Order order);

	/**
	 * <h2>dbGetCartDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param cartId
	 * @return
	 * @return List<CartDetail>
	 */
	List<CartDetail> dbGetCartDetail(Integer cartId);

	/**
	 * <h2>dbSaveOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param order
	 * @return void
	 */
	void dbSaveOrderDetail(OrderDetail order);
}