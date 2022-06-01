package scm.ojt.project.web.controller.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.project.bl.dto.OrderDTO;
import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.bl.service.OrderService;
import scm.ojt.project.persistence.entity.OrderDetail;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public ModelAndView orderList() {
		ModelAndView mv = new ModelAndView("orderList");
		List<OrderDTO> orderList = this.orderService.doGetOrderList();
		System.out.println(orderList);
		mv.addObject("orderList", orderList);
		return mv;
	}

	@RequestMapping(value = "/order/detail", method = RequestMethod.GET)
	public ModelAndView orderDetail(@RequestParam("id") Integer orderId) {
		ModelAndView orderDetailForm = new ModelAndView("orderDetail");
		List<OrderDetail> orderDetail = (List<OrderDetail>) this.orderService.doGetOrderDetail(orderId);
		OrderDTO orderById = this.orderService.doGetOrderById(orderId);
		orderDetailForm.addObject("orderDetail", orderDetail);
		orderDetailForm.addObject("order", orderById);
		System.out.println(orderId);
		return orderDetailForm;
	}

	@RequestMapping(value = "/order/accept", method = RequestMethod.GET)
	public ModelAndView acceptOrder(@RequestParam("id") Integer orderId) {
		this.orderService.doAcceptOrder(orderId);
		ModelAndView acceptOrder = new ModelAndView("redirect:/orderList");
		return acceptOrder;
	}

	@RequestMapping(value = "/order/cancel", method = RequestMethod.GET)
	public ModelAndView cancelOrder(@RequestParam("id") Integer orderId, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
		this.orderService.doCancelOrder(orderId, user.getId());
		ModelAndView cancelOrder = new ModelAndView("redirect:/orderList");
		return cancelOrder;
	}
}