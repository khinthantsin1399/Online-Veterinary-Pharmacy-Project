package scm.ojt.project.persistence.dao;

import java.util.Date;
import java.util.List;

import scm.ojt.project.persistence.entity.Cart;

public interface CartDao {
    public void dbAddCart(Cart cart, Date currentDate);

    public Cart dbGetCart(int createdUserId);

    public Cart dbGetCartByCartId(int cartId);

    public void dbUpdateCart(Cart cart, Date currentDate);

    public Cart doGetCartList();

    public List<Cart> dbGetCarts();

    public Cart isCreatedUserIdExist(int createdUserId);
}
