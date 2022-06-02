package scm.ojt.project.bl.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.service.CartService;
import scm.ojt.project.persistence.dao.CartDao;
import scm.ojt.project.persistence.entity.Cart;

/**
 * <h2>CartServiceImpl Class</h2>
 * <p>
 * Process for Displaying CartServiceImpl
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    /**
     * <h2>doGetCart</h2>
     * <p>
     * Getting cart by user id
     * </p>
     *
     * @param createdUserId
     * @return
     * @return Cart
     */
    @Override
    public Cart doGetCart(int createdUserId) {
        Cart cartResult = (Cart) this.cartDao.dbGetCart(createdUserId);
        return cartResult;
    }

    /**
     * <h2>doGetCartByCartId</h2>
     * <p>
     * Getting cart by cart id
     * </p>
     *
     * @param cartId
     * @return
     * @return Cart
     */
    @Override
    public Cart doGetCartByCartId(int cartId) {
        Cart resultCart = this.cartDao.dbGetCartByCartId(cartId);
        return resultCart;
    }

    /**
     * <h2>doGetCartList</h2>
     * <p>
     * Getting cart list
     * </p>
     *
     * @return
     * @return Cart
     */
    @Override
    public Cart doGetCartList() {
        Cart cartResult = (Cart) this.cartDao.dbGetCartList();
        return cartResult;
    }

    /**
     * <h2>doGetCarts</h2>
     * <p>
     * Getting carts
     * </p>
     *
     * @return
     * @return List<Cart>
     */
    @Override
    public List<Cart> doGetCarts() {
        List<Cart> cartResults = (List<Cart>) this.cartDao.dbGetCarts();
        return cartResults;
    }

    /**
     * <h2>doAddCart</h2>
     * <p>
     * Adding cart to database
     * </p>
     *
     * @param cart
     * @return void
     */
    @Override
    public void doAddCart(Cart cart) {
        this.cartDao.dbAddCart(cart, new Date());
    }

    /**
     * <h2>doUpdateCart</h2>
     * <p>
     * Updating cart
     * </p>
     *
     * @param cart
     * @return void
     */
    @Override
    public void doUpdateCart(Cart cart) {
        this.cartDao.dbUpdateCart(cart, new Date());
    }

    /**
     * <h2>isCreatedUserIdExist</h2>
     * <p>
     * Checking user id already exists or not
     * </p>
     *
     * @param createdUserId
     * @return
     * @return Boolean
     */
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