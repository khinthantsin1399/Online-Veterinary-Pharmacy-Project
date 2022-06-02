package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.persistence.entity.CartDetail;

/**
 * <h2>CartDetailService Class</h2>
 * <p>
 * Process for Displaying CartDetailService
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface CartDetailService {
    /**
     * <h2>addCartItem</h2>
     * <p>
     * Adding item to cart
     * </p>
     *
     * @param cartDetail
     * @param createdUserID
     * @return void
     */
    public void addCartItem(CartDetail cartDetail, int createdUserID);

    /**
     * <h2>deleteCartItem</h2>
     * <p>
     * Deleting item from cart
     * </p>
     *
     * @param cartDetailId
     * @return void
     */
    public void deleteCartItem(int cartDetailId);

    /**
     * <h2>doGetCartDetailById</h2>
     * <p>
     * Getting cart detail by id
     * </p>
     *
     * @param cartDetailId
     * @return
     * @return CartDetail
     */
    public CartDetail doGetCartDetailById(int cartDetailId);

    /**
     * <h2>doGetCartDetailListById</h2>
     * <p>
     * getting cart details by id
     * </p>
     *
     * @return
     * @return List<CartDetail>
     */
    public List<CartDetail> doGetCartDetailListById();

    /**
     * <h2>doUpdateCartDetail</h2>
     * <p>
     * Updating cart detail
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    public void doUpdateCartDetail(CartDetail cartDetail);

    /**
     * <h2>doUpdateQuantity</h2>
     * <p>
     * Updating item quantity
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    public void doUpdateQuantity(CartDetail cartDetail);
}