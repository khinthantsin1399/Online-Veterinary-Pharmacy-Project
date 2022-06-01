package scm.ojt.project.persistence.dao;

import java.util.List;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.persistence.entity.Order;
import scm.ojt.project.persistence.entity.OrderDetail;

public interface OrderDao {
	List<OrderDTO> dbGetOrderList();

	List<OrderDetail> dbGetOrderDetail(Integer orderId);

	Order dbGetOrderById(Integer orderId);

	void dbAcceptOrder(Integer orderId);

	void dbCancelOrder(Integer orderId, int userId);
}