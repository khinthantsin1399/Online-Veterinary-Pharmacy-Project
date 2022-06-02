package scm.ojt.project.bl.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.bl.service.OrderService;
import scm.ojt.project.persistence.dao.OrderDao;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>OrderServiceImpl Class</h2>
 * <p>
 * Process for Displaying OrderServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	/**
	 * <h2>orderDao</h2>
	 * <p>
	 * orderDao
	 * </p>
	 */
	@Autowired
	private OrderDao orderDao;

	/**
	 * <h2>session</h2>
	 * <p>
	 * session
	 * </p>
	 */
	@Autowired
	HttpSession session;

	/**
	 * <h2>doGetOrderList</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public List<OrderDTO> doGetOrderList() {
		return this.orderDao.dbGetOrderList();
	}

	/**
	 * <h2>doGetOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public List<OrderDetail> doGetOrderDetail(Integer orderId) {
		return this.orderDao.dbGetOrderDetail(orderId);
	}

	/**
	 * <h2>doGetOrderById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public OrderDTO doGetOrderById(Integer orderId) {
		Order order = this.orderDao.dbGetOrderById(orderId);
		OrderDTO orderResult = order != null ? new OrderDTO(order) : null;
		return orderResult;
	}

	/**
	 * <h2>doAcceptOrder</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 */
	@Override
	public void doAcceptOrder(Integer orderId) {
		this.orderDao.dbAcceptOrder(orderId);
	}

	/**
	 * <h2>doCancelOrder</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 * @param userId
	 */
	@Override
	public void doCancelOrder(Integer orderId, int userId) {
		this.orderDao.dbCancelOrder(orderId, userId);
	}

	/**
	 * <h2>doGetCartAndCheckOutByCartId</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param cartId
	 * @return
	 */
	@Override
	public Cart doGetCartAndCheckOutByCartId(int cartId) {
		Cart resultCart = this.orderDao.dbGetCartAndCheckOutByCartId(cartId);
		return resultCart;
	}

	/**
	 * <h2>doSaveOrder</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param cart
	 * @return
	 */
	@Override
	public int doSaveOrder(Cart cart) {
		Order order = new Order(cart);
		UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
		order.setU_name(user.getUsername());
		order.setU_phone(user.getPhone());
		order.setU_address(user.getAddress());
		order.setCreated_at(new Date());
		return this.orderDao.dbSaveOrder(order);
	}

	/**
	 * <h2>doGetCartDetail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param cartId
	 * @param orderId
	 * @return
	 */
	@Override
	public List<OrderDetail> doGetCartDetail(Integer cartId, int orderId) {
		System.out.println(cartId);
		List<CartDetail> cartDetails = this.orderDao.dbGetCartDetail(cartId);
		List<OrderDetail> orderDetail = new ArrayList<>();
		for (CartDetail orderDe : cartDetails) {
			OrderDetail orderDetailForm = new OrderDetail(orderDe);
			orderDetailForm.setO_id(orderId);
			orderDetail.add(orderDetailForm);
		}
		return orderDetail;
	}

	/**
	 * <h2>doSaveOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderDetails
	 */
	@Override
	public void doSaveOrderDetail(List<OrderDetail> orderDetails) {
		for (OrderDetail orderDetail : orderDetails) {
			OrderDetail orderDetailForm = new OrderDetail();
			orderDetailForm.setO_id(orderDetail.getO_id());
			orderDetailForm.setM_id(orderDetail.getM_id());
			orderDetailForm.setM_quantity(orderDetail.getM_quantity());
			orderDetailForm.setAmount(orderDetail.getAmount());
			this.orderDao.dbSaveOrderDetail(orderDetailForm);
		}
	}
}