package scm.ojt.project.persistence.dao;

import java.util.Date;
import java.util.List;

import scm.ojt.project.persistence.entity.Cart;

/**
 * <h2>CartDao Class</h2>
 * <p>
 * Process for Displaying CartDao
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CartDao {
    /**
     * <h2>dbAddCart</h2>
     * <p>
     * adding cart
     * </p>
     *
     * @param cart
     * @param currentDate
     * @return void
     */
    public void dbAddCart(Cart cart, Date currentDate);

    /**
     * <h2>dbGetCart</h2>
     * <p>
     * Getting cart by user id
     * </p>
     *
     * @param createdUserId
     * @return
     * @return Cart
     */
    public Cart dbGetCart(int createdUserId);

    /**
     * <h2>dbGetCartByCartId</h2>
     * <p>
     * Getting cart by cart id
     * </p>
     *
     * @param cartId
     * @return
     * @return Cart
     */
    public Cart dbGetCartByCartId(int cartId);

    /**
     * <h2>dbUpdateCart</h2>
     * <p>
     * Updating cart
     * </p>
     *
     * @param cart
     * @param currentDate
     * @return void
     */
    public void dbUpdateCart(Cart cart, Date currentDate);

    /**
     * <h2>dbGetCartList</h2>
     * <p>
     * 
     * </pGetting cart
     *
     * @return
     * @return Cart
     */
    public Cart dbGetCartList();

    /**
     * <h2>dbGetCarts</h2>
     * <p>
     * Getting carts
     * </p>
     *
     * @return
     * @return List<Cart>
     */
    public List<Cart> dbGetCarts();

    /**
     * <h2>isCreatedUserIdExist</h2>
     * <p>
     * Checking user id already exists or not
     * </p>
     *
     * @param createdUserId
     * @return
     * @return Cart
     */
    public Cart isCreatedUserIdExist(int createdUserId);
}