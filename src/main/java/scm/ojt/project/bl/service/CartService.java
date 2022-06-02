package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.persistence.entity.Cart;

/**<h2> CartService Class</h2>
 * <p>
 * Process for Displaying CartService
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CartService {
    /**
     * <h2> doGetCart</h2>
     * <p>
     * Getting cart by user id
     * </p>
     *
     * @param createdUserId
     * @return
     * @return Cart
     */
    public Cart doGetCart(int createdUserId);

    /**
     * <h2> doGetCartByCartId</h2>
     * <p>
     * Getting cart by cart id
     * </p>
     *
     * @param cartId
     * @return
     * @return Cart
     */
    public Cart doGetCartByCartId(int cartId);

    /**
     * <h2> doAddCart</h2>
     * <p>
     * Adding cart to database
     * </p>
     *
     * @param cart
     * @return void
     */
    public void doAddCart(Cart cart);

    /**
     * <h2> doUpdateCart</h2>
     * <p>
     * Updating cart
     * </p>
     *
     * @param cart
     * @return void
     */
    public void doUpdateCart(Cart cart);

    /**
     * <h2> doGetCartList</h2>
     * <p>
     * Getting cart list
     * </p>
     *
     * @return
     * @return Cart
     */
    public Cart doGetCartList();
    
    /**
     * <h2> isCreatedUserIdExist</h2>
     * <p>
     * Checking user id already exists or not
     * </p>
     *
     * @param createdUserId
     * @return
     * @return Boolean
     */
    public Boolean isCreatedUserIdExist(int createdUserId);
    
    /**
     * <h2> doGetCarts</h2>
     * <p>
     * Getting carts 
     * </p>
     *
     * @return
     * @return List<Cart>
     */
    public List<Cart> doGetCarts();
}