package scm.ojt.project.persistence.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.persistence.dao.CartDao;
import scm.ojt.project.persistence.entity.Cart;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {
    @Autowired
    private SessionFactory sessionFactory;

    public static String SELECT_CART_HQL = "FROM Cart c " + "WHERE c.deletedAt IS NUll "
            + "AND c.created_user_id = :created_user_id";

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
    @Override
    public void dbAddCart(Cart cart, Date currentDate) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(cart);
        this.sessionFactory.getCurrentSession().flush();
    }

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
    @SuppressWarnings("deprecation")
    @Override
    public Cart dbGetCart(int createdUserId) {
        StringBuffer query = new StringBuffer(SELECT_CART_HQL);
        @SuppressWarnings("rawtypes")
        Query queryCartList = this.sessionFactory.getCurrentSession().createQuery(query.toString());

        queryCartList.setParameter("created_user_id", createdUserId);

        Cart cartResult = (Cart) queryCartList.uniqueResult();
        return cartResult;
    }

    /**
     * <h2>dbGetCartList</h2>
     * <p>
     * 
     * </pGetting cart
     *
     * @return
     * @return Cart
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public Cart dbGetCartList() {
        Query<Cart> queryCartList = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Cart c WHERE c.deletedAt IS NUll ");
        queryCartList.setMaxResults(1);
        Cart cartList = (Cart) queryCartList.uniqueResult();
        return cartList;
    }

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
    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public Cart dbGetCartByCartId(int cartId) {
        Query queryCartById = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Cart c where c.id = :id");
        queryCartById.setParameter("id", cartId);
        Cart resultCart = (Cart) queryCartById.uniqueResult();
        return resultCart;

    }

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
    @Override
    public void dbUpdateCart(Cart cart, Date currentDate) {
        cart.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().update(cart);
    }

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
    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public Cart isCreatedUserIdExist(int createdUserId) {
        String HqlQuery = "SELECT c FROM Cart c where c.created_user_id = :created_user_id";
        Query queryisExist = this.sessionFactory.getCurrentSession().createQuery(HqlQuery);
        queryisExist.setParameter("created_user_id", createdUserId);
        Cart resultCart = (Cart) queryisExist.uniqueResult();
        return resultCart;
    }

    /**
     * <h2>dbGetCarts</h2>
     * <p>
     * Getting carts
     * </p>
     *
     * @return
     * @return List<Cart>
     */
    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
    @Override
    public List<Cart> dbGetCarts() {
        Query queryCarts = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Cart c WHERE c.deletedAt IS NUll ");

        List<Cart> cartList = (List<Cart>) queryCarts.list();
        return cartList;
    }
}
