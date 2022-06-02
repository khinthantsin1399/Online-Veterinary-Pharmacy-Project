package scm.ojt.project.persistence.dao;

import java.util.List;

import scm.ojt.project.persistence.entity.CartDetail;

/**<h2> CartDetailDao Class</h2>
 * <p>
 * Process for Displaying CartDetailDao
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CartDetailDao {
    
    /**
     * <h2> addCartItem</h2>
     * <p>
     * Adding cart Item to cart
     * </p>
     *
     * @param cartDetail
     * @param createdUserID
     * @return void
     */
    public void addCartItem(CartDetail cartDetail, int createdUserID);

    /**
     * <h2> deleteCartItem</h2>
     * <p>
     * Deleting cart item
     * </p>
     *
     * @param cartDetailId
     * @return void
     */
    public void deleteCartItem(int cartDetailId);

    /**
     * <h2> dbGetCartDetailById</h2>
     * <p>
     * Getting detail by id
     * </p>
     *
     * @param cartDetailId
     * @return
     * @return CartDetail
     */
    public CartDetail dbGetCartDetailById(int cartDetailId);

    /**
     * <h2> dbGetCartDetailListById</h2>
     * <p>
     * Getting cart detail by id
     * </p>
     *
     * @return
     * @return List<CartDetail>
     */
    public List<CartDetail> dbGetCartDetailListById();
    
    /**
     * <h2> dbUpdateCartDetail</h2>
     * <p>
     * Updating cart detail
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    public void dbUpdateCartDetail(CartDetail cartDetail);
    
    /**
     * <h2> dbUpdateQuantity</h2>
     * <p>
     * Updating quantity of item
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    public void dbUpdateQuantity(CartDetail cartDetail) ;
}