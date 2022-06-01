package scm.ojt.project.bl.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.service.CartDetailService;
import scm.ojt.project.persistence.dao.CartDetailDao;
import scm.ojt.project.persistence.dao.MedicineDao;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Medicine;

@Service
@Transactional
public class CartDetailServiceImpl implements CartDetailService {
    @Autowired
    private CartDetailDao cartDetailDao;

    @Autowired
    private MedicineDao medicineDao;

    @Override
    public void addCartItem(CartDetail cartDetail, int createdUserID) {
        Medicine medicine = medicineDao.getMedicineById(cartDetail.getMedicine().getId());
        cartDetail.setMedicine(medicine);
        this.cartDetailDao.addCartItem(cartDetail, createdUserID);
    }

    @Override
    public void deleteCartItem(int cartDetailId) {
        cartDetailDao.deleteCartItem(cartDetailId);
    }

    @Override
    public List<CartDetail> doGetCartDetailsByCartId(int cartId) {
        return this.cartDetailDao.dbGetCartDetailsByCartId(cartId);
    }
    @Override
    public CartDetail doGetCartDetailById(int cartDetailId) {
        return this.cartDetailDao.dbGetCartDetailById(cartDetailId);
    }
    @Override
    public List<CartDetail> doGetCartDetailListById(){
        List<CartDetail> cartDetailResult = (List<CartDetail>) this.cartDetailDao.dbGetCartDetailListById();
        return cartDetailResult;  
    }
    @Override
    public void doUpdateCartDetail(CartDetail cartDetail) {
        this.cartDetailDao.dbUpdateCartDetail(cartDetail);
    }
    @Override
    public void doUpdateQuantity(CartDetail cartDetail) {
        this.cartDetailDao.dbUpdateQuantity(cartDetail);
    }
}
