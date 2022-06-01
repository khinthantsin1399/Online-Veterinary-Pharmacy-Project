package scm.ojt.project.bl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.bl.service.OrderService;
import scm.ojt.project.persistence.dao.OrderDao;
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public List<OrderDTO> doGetOrderList() {
		return this.orderDao.dbGetOrderList();
	}

	@Override
	public List<OrderDetail> doGetOrderDetail(Integer orderId) {
		return this.orderDao.dbGetOrderDetail(orderId);
	}

	@Override
	public OrderDTO doGetOrderById(Integer orderId) {
		Order order = this.orderDao.dbGetOrderById(orderId);
		OrderDTO orderResult = order != null ? new OrderDTO(order) : null;
		return orderResult;
	}

	@Override
	public void doAcceptOrder(Integer orderId) {
		this.orderDao.dbAcceptOrder(orderId);
	}

	@Override
	public void doCancelOrder(Integer orderId, int userId) {
		this.orderDao.dbCancelOrder(orderId, userId);
	}
}