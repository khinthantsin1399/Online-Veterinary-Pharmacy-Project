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
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDTO> dbGetOrderList() {
		return sessionFactory.getCurrentSession().createQuery("SELECT o FROM Order o WHERE o.deleted_at = null").list();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<OrderDetail> dbGetOrderDetail(Integer orderId) {
		String userHqlQuery = "SELECT od FROM OrderDetail od where od.o_id = :id";
		Query queryOrderId = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryOrderId.setParameter("id", orderId);
		List<OrderDetail> orderDetail = (List<OrderDetail>) queryOrderId.list();
		return orderDetail;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Order dbGetOrderById(Integer orderId) {
		String userHqlQuery = "SELECT o FROM Order o where o.id = :id";
		Query queryUserById = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserById.setParameter("id", orderId);
		Order resultUser = (Order) queryUserById.uniqueResult();
		return resultUser;
	}

	@Override
	public void dbAcceptOrder(Integer orderId) {
		Order order = (Order) sessionFactory.getCurrentSession().load(Order.class, orderId);
		if (order != null) {
			order.setStatus(true);
			order.setDate(new Date());
			this.sessionFactory.getCurrentSession().update(order);
		}
	}

	@Override
	public void dbCancelOrder(Integer orderId, int userId) {
		Order order = sessionFactory.getCurrentSession().load(Order.class, orderId);
		if (order != null) {
			order.setDeleted_user_id(userId);
			order.setDeleted_at(new Date());
			this.sessionFactory.getCurrentSession().update(order);
		}
	}
}