package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>OrderService Class</h2>
 * <p>
 * Process for Displaying OrderService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface OrderService {
	/**
	 * <h2>doGetOrderList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<OrderDTO>
	 */
	List<OrderDTO> doGetOrderList();

	/**
	 * <h2>doGetOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @return
	 * @return List<OrderDetail>
	 */
	List<OrderDetail> doGetOrderDetail(Integer orderId);

	/**
	 * <h2>doGetOrderById</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @return
	 * @return OrderDTO
	 */
	OrderDTO doGetOrderById(Integer orderId);

	/**
	 * <h2>doAcceptOrder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @return void
	 */
	void doAcceptOrder(Integer orderId);

	/**
	 * <h2>doCancelOrder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param orderId
	 * @param userId
	 * @return void
	 */
	void doCancelOrder(Integer orderId, int userId);

	/**
	 * <h2>doGetCartAndCheckOutByCartId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param cartId
	 * @return
	 * @return Cart
	 */
	Cart doGetCartAndCheckOutByCartId(int cartId);

	/**
	 * <h2>doSaveOrder</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param cart
	 * @return
	 * @return int
	 */
	int doSaveOrder(Cart cart);

	/**
	 * <h2>doGetCartDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param cartId
	 * @param orderId
	 * @return
	 * @return List<OrderDetail>
	 */
	List<OrderDetail> doGetCartDetail(Integer cartId, int orderId);

	/**
	 * <h2>doSaveOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param cartDetails
	 * @return void
	 */
	void doSaveOrderDetail(List<OrderDetail> cartDetails);
}