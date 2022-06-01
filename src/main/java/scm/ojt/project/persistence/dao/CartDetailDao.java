package scm.ojt.project.persistence.dao;

import java.util.List;

import scm.ojt.project.persistence.entity.CartDetail;

public interface CartDetailDao {
    public void addCartItem(CartDetail cartDetail, int createdUserID);

    public void deleteCartItem(int cartDetailId);

    public List<CartDetail> dbGetCartDetailsByCartId(int cartId);
    
  

    // public void removeAllCartItems(Cart cart);
    public CartDetail dbGetCartDetailById(int cartDetailId);

    public List<CartDetail> dbGetCartDetailListById();
    public void dbUpdateCartDetail(CartDetail cartDetail);
    public void dbUpdateQuantity(CartDetail cartDetail) ;
}
