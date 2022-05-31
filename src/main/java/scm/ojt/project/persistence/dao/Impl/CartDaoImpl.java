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

    @Override
    public void dbAddCart(Cart cart, Date currentDate) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(cart);
        this.sessionFactory.getCurrentSession().flush();
    }

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

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public Cart doGetCartList() {
        Query<Cart> queryCartList = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Cart c WHERE c.deletedAt IS NUll ");
        queryCartList.setMaxResults(1);
        Cart cartList = (Cart) queryCartList.uniqueResult();
        return cartList;

    }

    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public Cart dbGetCartByCartId(int cartId) {
        Query queryCartById = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Cart c where c.id = :id");
        queryCartById.setParameter("id", cartId);
        Cart resultCart = (Cart) queryCartById.uniqueResult();
        return resultCart;

    }

    @Override
    public void dbUpdateCart(Cart cart, Date currentDate) {
        cart.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().update(cart);
    }

    @SuppressWarnings({ "deprecation", "rawtypes" })
    @Override
    public Cart isCreatedUserIdExist(int createdUserId) {
        String HqlQuery = "SELECT c FROM Cart c where c.created_user_id = :created_user_id";
        Query queryisExist = this.sessionFactory.getCurrentSession().createQuery(HqlQuery);
        queryisExist.setParameter("created_user_id", createdUserId);
        Cart resultCart = (Cart) queryisExist.uniqueResult();
        return resultCart;
    }

    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
    @Override
    public List<Cart> dbGetCarts() {
        Query queryCarts = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Cart c WHERE c.deletedAt IS NUll ");

        List<Cart> cartList = (List<Cart>) queryCarts.list();
        return cartList;
    }
}
