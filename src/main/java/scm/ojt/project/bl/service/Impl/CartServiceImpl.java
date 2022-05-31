package scm.ojt.project.bl.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.service.CartService;
import scm.ojt.project.persistence.dao.CartDao;
import scm.ojt.project.persistence.entity.Cart;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Cart doGetCart(int createdUserId) {
        // User user = this.userDao.dbGetUserbyId(createdUserId);
        Cart cartResult = (Cart) this.cartDao.dbGetCart(createdUserId);
        return cartResult;
    }

    @Override
    public Cart dbGetCartByCartId(int cartId) {
        Cart resultCart = this.cartDao.dbGetCartByCartId(cartId);
        return resultCart;
    }

    @Override
    public Cart doGetCartList() {
        Cart cartResult = (Cart) this.cartDao.doGetCartList();
        return cartResult;
    }

    @Override
    public List<Cart> doGetCarts() {
        List<Cart> cartResults = (List<Cart>) this.cartDao.dbGetCarts();
        return cartResults;
    }

    @Override
    public void doAddCart(Cart cart) {
        /*
         * double grandTotal = 0; cart = cartDao.dbGetCartByCartId(cart.getId());
         * 
         * List<CartDetail> cartDetails = cart.getCartDetails();
         * 
         * for (CartDetail cartDetail : cartDetails) { grandTotal +=
         * cartDetail.getAmount(); }
         * 
         * cart.setAmount(grandTotal);
         */

        this.cartDao.dbAddCart(cart, new Date());
    }

    @Override
    public void doUpdateCart(Cart cart) {
        this.cartDao.dbUpdateCart(cart, new Date());
    }

    @Override
    public Boolean isCreatedUserIdExist(int createdUserId) {
        Cart resultCart = this.cartDao.isCreatedUserIdExist(createdUserId);
        boolean createdUserIdExist = false;
        if (resultCart != null) {
            createdUserIdExist = true;
        }
        return createdUserIdExist;
    }
}