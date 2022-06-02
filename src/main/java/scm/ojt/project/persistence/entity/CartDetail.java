package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>CartDetail Class</h2>
 * <p>
 * Process for Displaying CartDetail
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_details")
public class CartDetail implements Serializable {
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

    /**
     * <h2>quantity</h2>
     * <p>
     * quantity
     * </p>
     */
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

    /**
     * <h2>cart</h2>
     * <p>
     * cart
     * </p>
     */
    @ManyToOne(fetch = FetchType.EAGER) // with cart
    @JoinColumn(name = "cart_id") // primary key of cart
    @JsonIgnore
    private Cart cart;

    /**
     * <h2>medicine</h2>
     * <p>
     * medicine
     * </p>
     */
    @ManyToOne // with cartDetail
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
}