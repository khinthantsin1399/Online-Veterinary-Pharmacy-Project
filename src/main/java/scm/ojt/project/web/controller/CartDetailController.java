package scm.ojt.project.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.project.bl.dto.MedicineDto;
import scm.ojt.project.bl.service.CartDetailService;
import scm.ojt.project.bl.service.CartService;
import scm.ojt.project.bl.service.MedicineService;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.MedicineForm;

/**
 * <h2>CartDetailController Class</h2>
 * <p>
 * Process for Displaying CartDetailController
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Controller
public class CartDetailController {
    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MessageSource messageSource;

    /**
     * <h2>getCart</h2>
     * <p>
     * Getting cart
     * </p>
     *
     * @param request
     * @return
     * @throws IOException
     * @return String
     */
    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String getCart(HttpServletRequest request) throws IOException {
        // ModelAndView cartView=new ModelAndView("viewCart");
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        Cart cart = cartService.doGetCart(loginUserId);
        int cartId = cart.getId();
        return "redirect:/viewCartDetail?id=" + cartId;

    }

    /**
     * <h2>getCart</h2>
     * <p>
     * Showing cart
     * </p>
     *
     * @param cartId
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/viewCartDetail", method = RequestMethod.GET)
    public ModelAndView getCart(@RequestParam("id") Integer cartId, HttpServletRequest request) throws IOException {
        ModelAndView cartView = new ModelAndView("viewCartDetail");
        List<CartDetail> cartDetails = this.cartDetailService.doGetCartDetailListById();
        List<CartDetail> cd = new ArrayList<>();
        for (CartDetail cartDetailResult : cartDetails) {
            if (cartDetailResult.getCart().getId() == cartId) {
                cd.add(cartDetailResult);
            }
        }

        cartView.addObject("cartDetails", cd);
        cartView.setViewName("viewCartDetail");
        return cartView;
    }

    // @SuppressWarnings("deprecation")
    /**
     * <h2>addToCart</h2>
     * <p>
     * Adding item to cart
     * </p>
     *
     * @param medicineId
     * @param request
     * @param medicineForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/addToCart", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView addToCart(@RequestParam("id") Integer medicineId, HttpServletRequest request,
            MedicineForm medicineForm) throws IOException {
        ModelAndView medicineListView = new ModelAndView("userMedicineList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        MedicineForm medForm = this.medicineService.getMedicineById(medicineId);
        Medicine med = new Medicine(medForm);

        if (this.cartService.isCreatedUserIdExist(loginUserId)) {
            Cart cartList = this.cartService.doGetCart(loginUserId);
            List<CartDetail> cartDetails = this.cartDetailService.doGetCartDetailListById();
            for (CartDetail cartDetailResult : cartDetails) {
                if (cartDetailResult.getCart().getId() == cartList.getId()
                        && cartDetailResult.getMedicine().getId() == med.getId()) {
                    if (med.getUnit_in_stock() > 0) {
                        cartDetailResult.setQuantity(cartDetailResult.getQuantity() + 1);
                        cartDetailResult.setAmount(cartDetailResult.getQuantity() * med.getAmount());
                        cartDetailService.doUpdateCartDetail(cartDetailResult);
                        cartList.setAmount(cartList.getAmount() + cartDetailResult.getAmount());
                        cartService.doUpdateCart(cartList);
                        med.setUnit_in_stock(med.getUnit_in_stock() - 1);
                        this.medicineService.doUpdateMedicine(med);
                        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
                        return medicineListView;
                    } else {
                        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
                        medicineListView.addObject("errorMsg", messageSource.getMessage("M_SC_0011", null, null));
                        return medicineListView;
                    }
                }
            }
            if (med.getUnit_in_stock() > 0) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cartList);
                cartDetail.setMedicine(med);
                cartDetail.setQuantity(1);
                cartDetail.setAmount(medicineForm.getAmount() * cartDetail.getQuantity());
                cartDetailService.addCartItem(cartDetail, loginUserId);
                cartList.setAmount(cartList.getAmount() + cartDetail.getAmount());
                cartService.doUpdateCart(cartList);
                med.setUnit_in_stock(med.getUnit_in_stock() - 1);
                this.medicineService.doUpdateMedicine(med);
            } else {
                this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
                medicineListView.addObject("errorMsg", messageSource.getMessage("M_SC_0011", null, null));
                return medicineListView;
            }

        } else {
            Cart cart = new Cart();
            cart.setCreated_user_id(loginUserId);
            cart.setAmount(med.getAmount());
            cart.setCreatedAt(new Date());
            cartService.doAddCart(cart);

            Cart cartList = this.cartService.doGetCart(loginUserId);
            if (med.getUnit_in_stock() > 0) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCart(cartList);
                cartDetail.setMedicine(med);
                cartDetail.setQuantity(1);
                cartDetail.setAmount(medicineForm.getAmount() * cartDetail.getQuantity());
                cartDetailService.addCartItem(cartDetail, loginUserId);
                med.setUnit_in_stock(med.getUnit_in_stock() - 1);
                this.medicineService.doUpdateMedicine(med);
            } else {
                this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
                medicineListView.addObject("errorMsg", messageSource.getMessage("M_SC_0011", null, null));
                return medicineListView;
            }
        }
        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
        return medicineListView;
    }

    /**
     * <h2>addToCart</h2>
     * <p>
     * Updaing quantity in cart
     * </p>
     *
     * @param cartDetailId
     * @param quantity
     * @param request
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateQuantity", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView addToCart(@RequestParam("id") Integer cartDetailId, @RequestParam Integer quantity,
            HttpServletRequest request) throws IOException {
        CartDetail cartDetail = cartDetailService.doGetCartDetailById(cartDetailId);
        int medId = cartDetail.getMedicine().getId();
        MedicineForm medForm = this.medicineService.getMedicineById(medId);
        Medicine med = new Medicine(medForm);
        if (cartDetail != null && med.getUnit_in_stock() > 0 && quantity <= med.getUnit_in_stock()) {
            int loginUserId = (int) request.getSession().getAttribute("loginUserId");
            Cart cartList = cartService.doGetCart(loginUserId);
            double oldAmount = cartDetail.getAmount();
            cartDetail.setQuantity(quantity);
            cartDetail.setAmount(quantity * cartDetail.getMedicine().getAmount());
            cartDetailService.doUpdateCartDetail(cartDetail);

            cartList.setAmount(cartList.getAmount() - oldAmount + cartDetail.getAmount());
            cartService.doUpdateCart(cartList);
        } else {
            return new ModelAndView("redirect:/viewCart");
        }
        return new ModelAndView("redirect:/viewCart");
    }

    // @SuppressWarnings("deprecation")
    /**
     * <h2>deleteCartItem</h2>
     * <p>
     * Deleting item from cart
     * </p>
     *
     * @param cartDetailId
     * @param request
     * @param medicineForm
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/deleteCartItem", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@RequestParam("id") Integer cartDetailId, HttpServletRequest request,
            MedicineForm medicineForm) throws IOException {
        CartDetail cartDetail = cartDetailService.doGetCartDetailById(cartDetailId);
        int medId = cartDetail.getMedicine().getId();
        medicineForm = this.medicineService.getMedicineById(medId);
        Medicine med = new Medicine(medicineForm);
        if (cartDetail != null) {
            int loginUserId = (int) request.getSession().getAttribute("loginUserId");
            Cart cart = cartService.doGetCart(loginUserId);
            cart.setAmount(cart.getAmount() - cartDetail.getAmount());
            cartService.doUpdateCart(cart);
            cartDetailService.deleteCartItem(cartDetailId);
        }
        med.setUnit_in_stock(medicineForm.getUnit_in_stock() + cartDetail.getQuantity());
        this.medicineService.doUpdateMedicine(med);
        return new ModelAndView("redirect:/viewCart");
    }

    private void getPagination(ModelAndView medicineListView, int currentPage, int recordsPerPage, boolean resultSearch,
            MedicineForm medicineForm) throws IOException {
        List<MedicineDto> medicineList;
        if (resultSearch == false) {
            medicineList = this.medicineService.doGetMedicineList();
        } else {
            medicineList = this.medicineService.doSearchMedicineList(medicineForm);
        }
        int rows = medicineList.size();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }
        List<MedicineDto> searchMedicineList = this.medicineService.doSearchMedicineWithPagi(currentPage,
                recordsPerPage, medicineForm);
        medicineListView.addObject("noOfPages", nOfPages);
        medicineListView.addObject("currentPage", currentPage);
        medicineListView.addObject("recordsPerPage", recordsPerPage);
        medicineListView.addObject("MedicineList", searchMedicineList);
    }

    /**
     * <h2>getCurrentPage</h2>
     * <p>
     * To get current page
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return int
     */
    private int getCurrentPage(HttpServletRequest request) {
        int currentPage = request.getParameter("currentPage") != null
                ? Integer.valueOf(request.getParameter("currentPage"))
                : 1;
        return currentPage;
    }

    /**
     * <h2>getRecordsPerPage</h2>
     * <p>
     * To get records per page
     * </p>
     *
     * @param request HttpServletRequest
     * @return
     * @return int
     */
    private int getRecordsPerPage(HttpServletRequest request) {
        int recordsPerPage = request.getParameter("recordsPerPage") != null
                ? Integer.valueOf(request.getParameter("recordsPerPage"))
                : 6;
        return recordsPerPage;
    }
}