package scm.ojt.project.bl.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.Medicine;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <h2>c_id</h2>
     * <p>
     * c_id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "c_id")
    private Integer c_id;

    @Column(name = "quantity")
    private Integer quantity;

    /**
     * <h2>amount</h2>
     * <p>
     * amount
     * </p>
     */
    @Column(name = "amount")
    private double amount;

    private Cart cart;

    private Medicine medicine;

}
