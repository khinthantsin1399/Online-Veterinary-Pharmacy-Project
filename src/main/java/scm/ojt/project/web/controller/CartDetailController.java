package scm.ojt.project.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.bl.dto.MedicineDto;
import scm.ojt.project.bl.service.CartDetailService;
import scm.ojt.project.bl.service.CartService;
import scm.ojt.project.bl.service.MedicineService;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.MedicineForm;

@Controller
public class CartDetailController {
    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String getCart(HttpServletRequest request) throws IOException {
        // ModelAndView cartView=new ModelAndView("viewCart");
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");
        Cart cart = cartService.doGetCart(loginUserId);
        int cartId = cart.getId();
        // List<CartDetail>
        // cartDetails=this.cartDetailService.doGetCartDetailsByCartId(cart.getId());
        // cartView.addObject("cartDetails",cartDetails);
        return "redirect:/viewCartDetail?id=" + cartId;

    }

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

    @RequestMapping(value = "/addToCart", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView addToCart(@RequestParam("id") Integer medicineId, HttpServletRequest request,MedicineForm medicineForm)
            throws IOException {
       // ModelAndView cartView = new ModelAndView("medicineList");
       // List<MedicineDto> MedicineList = medicineService.doGetMedicineList();
        ModelAndView medicineListView = new ModelAndView("medicineList");
        int currentPage = getCurrentPage(request);
        int recordsPerPage = getRecordsPerPage(request);
       
        int loginUserId = (int) request.getSession().getAttribute("loginUserId");

        MedicineForm medForm = this.medicineService.getMedicineById(medicineId);
        Medicine med = new Medicine(medForm);
       
        if (this.cartService.isCreatedUserIdExist(loginUserId)) {
          //  List<Cart> carts = this.cartService.doGetCarts();
          Cart cartList=this.cartService.doGetCart(loginUserId);
         // List<CartDetail> cartDetails=cartList.getCartDetails();
          
         List<CartDetail> cartDetails = this.cartDetailService.doGetCartDetailListById();
       
          for (CartDetail cartDetailResult : cartDetails) {
              if (cartDetailResult.getCart().getId()== cartList.getId() && cartDetailResult.getMedicine().getId()==med.getId()) {
                cartDetailResult.setQuantity(cartDetailResult.getQuantity()+1);
                cartDetailResult.setAmount(cartDetailResult.getQuantity()*med.getAmount());
                cartDetailService.doUpdateCartDetail(cartDetailResult);
                cartList.setAmount(cartList.getAmount() + cartDetailResult.getAmount());
                cartService.doUpdateCart(cartList);
               // ModelAndView cartView = new ModelAndView("redirect:/medicineList");
               // return cartView;
                //cartView.addObject("MedicineList", MedicineList);
                //return cartView;
                
                this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
                return medicineListView;
              }
               
          }
          
          CartDetail cartDetail = new CartDetail();
          cartDetail.setCart(cartList);
          cartDetail.setMedicine(med);
          cartDetail.setQuantity(1);
          cartDetail.setAmount(medicineForm.getAmount() * cartDetail.getQuantity());
          cartDetailService.addCartItem(cartDetail, loginUserId);
          cartList.setAmount(cartList.getAmount() + cartDetail.getAmount());
          cartService.doUpdateCart(cartList);
      
           
        } else {
            Cart cart = new Cart();
            cart.setCreated_user_id(loginUserId);
            cart.setAmount(med.getAmount());
            cart.setCreatedAt(new Date());
            cartService.doAddCart(cart);

            Cart cartList = this.cartService.doGetCartList();

            CartDetail cartDetail = new CartDetail();
            cartDetail.setCart(cartList);
            cartDetail.setMedicine(med);
            cartDetail.setQuantity(1);
            cartDetail.setAmount(medicineForm.getAmount() * cartDetail.getQuantity());
            cartDetailService.addCartItem(cartDetail, loginUserId);
        }
        this.getPagination(medicineListView, currentPage, recordsPerPage, false, medicineForm);
        return medicineListView;
       // cartView.addObject("MedicineList", MedicineList);
       // return cartView;

    }

    /*
     * @RequestMapping(value = "/addToCart", method = RequestMethod.GET) public
     * ModelAndView addToCart(@RequestParam("id") Integer medicineId, Integer
     * userId, HttpServletRequest request) { ModelAndView medicineView = new
     * ModelAndView("detailMedicine"); // UserForm userForm =
     * this.userService.doGetUserbyId(userForm.getId()); //
     * customerService.findCustomerByUsername(activeUser.getUsername()); // Cart
     * cart = userForm.getCart(); // Cart cart=new Cart(); int loginUserId = (int)
     * request.getSession().getAttribute("loginUserId"); Cart cartresult =
     * this.cartService.doGetCart(loginUserId); MedicineForm detailMedicine = null;
     * try { detailMedicine = this.medicineService.getMedicineById(medicineId); }
     * catch (NullPointerException | IOException e) { medicineView = new
     * ModelAndView("redirect:/medicineList"); }
     * 
     * List<CartDetail> cartDetails = cartresult.getCartDetails();
     * 
     * // IF PRODUCT ALREDAY EXIST IN CART THEN INCRESE ITS QUANTITY
     * 
     * for (int i = 0; i < cartDetails.size(); i++) {
     * 
     * if (detailMedicine.getId() == cartDetails.get(i).getMedicine().getId()) {
     * CartDetail cartDetail = cartDetails.get(i);
     * cartDetail.setQuantity(cartDetail.getQuantity() + 1);
     * cartDetail.setAmount(detailMedicine.getAmount() * cartDetail.getQuantity());
     * cartDetailService.addCartItem(cartDetail);
     * 
     * return medicineView; }
     * 
     * }
     * 
     * // IF PRODUCT IS NEW
     * 
     * CartDetail cartDetail = new CartDetail();
     * cartDetail.setMedicine(detailMedicine); cartDetail.setQuantity(1);
     * cartDetail.setAmount(detailMedicine.getAmount() * cartDetail.getQuantity());
     * cartDetail.setCart(cartresult); cartDetailService.addCartItem(cartDetail);
     * return medicineView;
     * 
     * }
     */

    @RequestMapping(value = "/deleteCartItem", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@RequestParam("id") Integer cartDetailId, HttpServletRequest request) {
        
        CartDetail cartDetail = cartDetailService.doGetCartDetailById(cartDetailId);
        if (cartDetail != null) {
            int loginUserId = (int) request.getSession().getAttribute("loginUserId");
            Cart cart = cartService.doGetCart(loginUserId);
            cart.setAmount(cart.getAmount() - cartDetail.getAmount());

            cartService.doUpdateCart(cart);
            cartDetailService.deleteCartItem(cartDetailId);
        }
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
