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

    /**
     * <h2>addCartItem</h2>
     * <p>
     * Adding cart Item to cart
     * </p>
     *
     * @param cartDetail
     * @param createdUserID
     * @return void
     */
    @Override
    public void addCartItem(CartDetail cartDetail, int createdUserID) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(cartDetail);
    }

    /**
     * <h2>deleteCartItem</h2>
     * <p>
     * Deleting cart item
     * </p>
     *
     * @param cartDetailId
     * @return void
     */
    @Override
    public void deleteCartItem(int cartDetailId) {
        CartDetail cartDetail = (CartDetail) sessionFactory.getCurrentSession().load(CartDetail.class, cartDetailId);
        if (null != cartDetail) {
            this.sessionFactory.getCurrentSession().delete(cartDetail);
        }
    }

    /**
     * <h2>dbGetCartDetailById</h2>
     * <p>
     * Getting detail by id
     * </p>
     *
     * @param cartDetailId
     * @return
     * @return CartDetail
     */
    @SuppressWarnings({ "rawtypes", "deprecation" })
    @Override
    public CartDetail dbGetCartDetailById(int cartDetailId) {
        Query queryCartDetailByCartId = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT cd FROM CartDetail cd where cd.id = :id");
        queryCartDetailByCartId.setParameter("id", cartDetailId);
        CartDetail resultCartDetail = (CartDetail) queryCartDetailByCartId.uniqueResult();
        return resultCartDetail;
    }

    /**
     * <h2>dbGetCartDetailListById</h2>
     * <p>
     * Getting cart detail by id
     * </p>
     *
     * @return
     * @return List<CartDetail>
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
    @Override
    public List<CartDetail> dbGetCartDetailListById() {
        Query queryCartDetailById = this.sessionFactory.getCurrentSession().createQuery("SELECT cd FROM CartDetail cd");
        List<CartDetail> resultCartDetailList = (List<CartDetail>) queryCartDetailById.list();
        return resultCartDetailList;
    }

    /**
     * <h2>dbUpdateCartDetail</h2>
     * <p>
     * Updating cart detail
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    @Override
    public void dbUpdateCartDetail(CartDetail cartDetail) {
        this.sessionFactory.getCurrentSession().update(cartDetail);
    }

    /**
     * <h2>dbUpdateQuantity</h2>
     * <p>
     * Updating quantity of item
     * </p>
     *
     * @param cartDetail
     * @return void
     */
    @Override
    public void dbUpdateQuantity(CartDetail cartDetail) {
        this.sessionFactory.getCurrentSession().update(cartDetail);
    }
}
