package scm.ojt.project.persistence.dao.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.persistence.dao.CartDetailDao;
import scm.ojt.project.persistence.entity.CartDetail;

@Repository
@Transactional
public class CartDetailDaoImpl implements CartDetailDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCartItem(CartDetail cartDetail, int createdUserID) {
        // Medicine medicine =
        // medicineDao.getMedicineById(cartDetail.getMedicine().getId());
        // cartDetail.setMedicine(medicine);
        // cartDetail.setAmount(cartDetail.getMedicine().getAmount());

        this.sessionFactory.getCurrentSession().saveOrUpdate(cartDetail);
    }

    @Override
    public void deleteCartItem(int cartDetailId) {
        CartDetail cartDetail = (CartDetail) sessionFactory.getCurrentSession().load(CartDetail.class, cartDetailId);
        if (null != cartDetail) {
            this.sessionFactory.getCurrentSession().delete(cartDetail);
        }
    }

    @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
    @Override
    public List<CartDetail> dbGetCartDetailsByCartId(int cartId) {
        Query queryCartDetailById = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT cd FROM CartDetail cd where c.cart_id = :cart_id");
        queryCartDetailById.setParameter("cart_id", cartId);
        List<CartDetail> resultCartDetailList = (List<CartDetail>) queryCartDetailById.list();
        return resultCartDetailList;
    }

    @SuppressWarnings({ "rawtypes", "deprecation" })
    @Override
    public CartDetail dbGetCartDetailById(int cartDetailId) {
        Query queryCartDetailByCartId = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT cd FROM CartDetail cd where cd.id = :id");
        queryCartDetailByCartId.setParameter("id", cartDetailId);
        CartDetail resultCartDetail = (CartDetail) queryCartDetailByCartId.uniqueResult();
        return resultCartDetail;

    }
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    @Override
    public List<CartDetail> dbGetCartDetailListById(){
        Query queryCartDetailById = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT cd FROM CartDetail cd");
      
        List<CartDetail> resultCartDetailList = (List<CartDetail>) queryCartDetailById.list();
        return resultCartDetailList;
    }
    @Override
    public void dbUpdateCartDetail(CartDetail cartDetail) {
        this.sessionFactory.getCurrentSession().update(cartDetail);
    }
   
}
