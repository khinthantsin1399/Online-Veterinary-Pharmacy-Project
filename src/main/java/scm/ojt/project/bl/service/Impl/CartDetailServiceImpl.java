package scm.ojt.project.bl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.service.CartDetailService;
import scm.ojt.project.persistence.dao.CartDetailDao;
import scm.ojt.project.persistence.dao.MedicineDao;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Medicine;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private MedicineDao medicineDao;

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
    @Override
    public void addCartItem(CartDetail cartDetail, int createdUserID) {
        Medicine medicine = medicineDao.getMedicineById(cartDetail.getMedicine().getId());
        cartDetail.setMedicine(medicine);
        this.cartDetailDao.addCartItem(cartDetail, createdUserID);
    }

    /**
     * <h2>deleteCartItem</h2>
     * <p>
     * Deleting item from cart
     * </p>
     *
     * @param cartDetailId
     * @return void
     */
    @Override
    public void deleteCartItem(int cartDetailId) {
        cartDetailDao.deleteCartItem(cartDetailId);
    }

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
    @Override
    public CartDetail doGetCartDetailById(int cartDetailId) {
        return this.cartDetailDao.dbGetCartDetailById(cartDetailId);
    }

    /**
     * <h2>doGetCartDetailListById</h2>
     * <p>
     * getting cart details by id
     * </p>
     *
     * @return
     * @return List<CartDetail>
     */
    @Override
    public List<CartDetail> doGetCartDetailListById() {
        List<CartDetail> cartDetailResult = (List<CartDetail>) this.cartDetailDao.dbGetCartDetailListById();
        return cartDetailResult;
    }

    /**
     * <h2>doUpdateCartDetail</h2>
     * <p>
     * Updating cart detail
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    @Override
    public void doUpdateCartDetail(CartDetail cartDetail) {
        this.cartDetailDao.dbUpdateCartDetail(cartDetail);
    }

    /**
     * <h2>doUpdateQuantity</h2>
     * <p>
     * Updating item quantity
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    @Override
    public void doUpdateQuantity(CartDetail cartDetail) {
        this.cartDetailDao.dbUpdateQuantity(cartDetail);
    }
}