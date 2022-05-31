package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.persistence.entity.Cart;

public interface CartService {
    public Cart doGetCart(int createdUserId);

    public Cart dbGetCartByCartId(int cartId);

    public void doAddCart(Cart cart);

    public void doUpdateCart(Cart cart);

    public Cart doGetCartList();
    
    public Boolean isCreatedUserIdExist(int createdUserId);
    
    public List<Cart> doGetCarts();
}
