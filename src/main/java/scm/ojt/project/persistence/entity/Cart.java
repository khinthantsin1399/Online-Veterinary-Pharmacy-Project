package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>Cart Class</h2>
 * <p>
 * Process for Displaying Cart
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
@Table(name = "shopping_cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * <h2>amount</h2>
     * <p>
     * amount
     * </p>
     */
    @Column(name = "amount")
    private double amount;

    /**
     * <h2>date</h2>
     * <p>
     * date
     * </p>
     */
    @Column(name = "date")
    private Date date;

    /**
     * <h2>checkout_flg</h2>
     * <p>
     * checkout_flg
     * </p>
     */
    @Column(name = "checkout_flg")
    private Boolean checkout_flg;

    /**
     * <h2>created_user_id</h2>
     * <p>
     * created_user_id
     * </p>
     */
    @Column(name = "created_user_id")
    private Integer created_user_id;

    /**
     * <h2>updated_user_id</h2>
     * <p>
     * updated_user_id
     * </p>
     */
    @Column(name = "updated_user_id")
    private Integer updated_user_id;

    /**
     * <h2>deleted_user_id</h2>
     * <p>
     * deleted_user_id
     * </p>
     */
    @Column(name = "deleted_user_id")
    private Integer deleted_user_id;

    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;

    /**
     * <h2>cartDetails</h2>
     * <p>
     * cartDetails
     * </p>
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartDetail> cartDetails;

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private User user;
}