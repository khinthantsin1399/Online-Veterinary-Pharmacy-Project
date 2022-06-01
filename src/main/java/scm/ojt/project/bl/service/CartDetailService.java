package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.persistence.entity.CartDetail;

public interface CartDetailService {
    public void addCartItem(CartDetail cartDetail, int createdUserID);

    public void deleteCartItem(int cartDetailId);

    public List<CartDetail> doGetCartDetailsByCartId(int cartId);
    public CartDetail doGetCartDetailById(int cartDetailId);
    public List<CartDetail> doGetCartDetailListById();
    public void doUpdateCartDetail(CartDetail cartDetail);
    public void doUpdateQuantity(CartDetail cartDetail) ;
}
