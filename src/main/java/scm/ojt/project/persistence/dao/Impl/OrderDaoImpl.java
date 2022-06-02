package scm.ojt.project.persistence.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.persistence.dao.OrderDao;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>OrderDaoImpl Class</h2>
 * <p>
 * Process for Displaying OrderDaoImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>dbGetOrderList</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDTO> dbGetOrderList() {
		return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Order o WHERE o.deleted_at = null").list();
	}

	/**
	 * <h2>dbGetOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<OrderDetail> dbGetOrderDetail(Integer orderId) {
		String userHqlQuery = "SELECT od FROM OrderDetail od where od.o_id = :id";
		Query queryOrderId = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryOrderId.setParameter("id", orderId);
		List<OrderDetail> orderDetail = (List<OrderDetail>) queryOrderId.list();
		return orderDetail;
	}

	/**
	 * <h2>dbGetOrderById</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Order dbGetOrderById(Integer orderId) {
		String userHqlQuery = "SELECT o FROM Order o where o.id = :id";
		Query queryUserById = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserById.setParameter("id", orderId);
		Order resultUser = (Order) queryUserById.uniqueResult();
		return resultUser;
	}

	/**
	 * <h2>dbAcceptOrder</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 */
	@Override
	public void dbAcceptOrder(Integer orderId) {
		Order order = (Order) sessionFactory.getCurrentSession().load(Order.class, orderId);
		if (order != null) {
			order.setStatus(true);
			order.setDate(new Date());
			this.sessionFactory.getCurrentSession().update(order);
		}
	}

	/**
	 * <h2>dbCancelOrder</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderId
	 * @param userId
	 */
	@Override
	public void dbCancelOrder(Integer orderId, int userId) {
		Order order = sessionFactory.getCurrentSession().load(Order.class, orderId);
		if (order != null) {
			order.setDeleted_user_id(userId);
			order.setDeleted_at(new Date());
			this.sessionFactory.getCurrentSession().update(order);
		}
	}

	/**
	 * <h2>dbGetCartAndCheckOutByCartId</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param cartId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public Cart dbGetCartAndCheckOutByCartId(int cartId) {
		Cart cart = sessionFactory.getCurrentSession().load(Cart.class, cartId);
		if (cart != null) {
			cart.setDate(new Date());
			cart.setCheckout_flg(true);
			this.sessionFactory.getCurrentSession().update(cart);
		}
		Query queryCartById = this.sessionFactory.getCurrentSession()
		        .createQuery("SELECT c FROM Cart c where c.id = :id");
		queryCartById.setParameter("id", cartId);
		Cart resultCart = (Cart) queryCartById.uniqueResult();
		return resultCart;
	}

	/**
	 * <h2>dbSaveOrder</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param order
	 * @return
	 */
	@Override
	public int dbSaveOrder(Order order) {
		return (int) this.sessionFactory.getCurrentSession().save(order);
	}

	/**
	 * <h2>dbGetCartDetail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param cartId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CartDetail> dbGetCartDetail(Integer cartId) {
		Query queryCartDetailById = this.sessionFactory.getCurrentSession()
		        .createQuery("SELECT cd FROM CartDetail cd where cd.cart_id = :cart_id");
		queryCartDetailById.setParameter("cart_id", cartId);
		List<CartDetail> resultCartDetailList = (List<CartDetail>) queryCartDetailById.list();
		return resultCartDetailList;
	}

	/**
	 * <h2>dbSaveOrderDetail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param orderDetail
	 */
	@Override
	public void dbSaveOrderDetail(OrderDetail orderDetail) {
		this.sessionFactory.getCurrentSession().save(orderDetail);
	}
}