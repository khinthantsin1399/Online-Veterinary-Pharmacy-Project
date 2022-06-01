package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.persistence.entity.OrderDetail;

public interface OrderService {
	List<OrderDTO> doGetOrderList();

	List<OrderDetail> doGetOrderDetail(Integer orderId);

	OrderDTO doGetOrderById(Integer orderId);

	void doAcceptOrder(Integer orderId);

	void doCancelOrder(Integer orderId, int userId);
}