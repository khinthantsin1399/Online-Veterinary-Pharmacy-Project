package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.web.form.MedicineForm;

/**
 * <h2>Medicine Class</h2>
 * <p>
 * Process for Displaying Medicine
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "medicines")
public class Medicine implements Serializable {
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
     * <h2>medicine_name</h2>
     * <p>
     * medicine_name
     * </p>
     */
    @Column(name = "m_name")
    private String medicine_name;

    /**
     * <h2>medicine_description</h2>
     * <p>
     * medicine_description
     * </p>
     */
    @Column(name = "m_description")
    private String medicine_description;

    /**
     * <h2>medicine_code</h2>
     * <p>
     * medicine_code
     * </p>
     */
    @Column(name = "m_code")
    private String medicine_code;

    /**
     * <h2>unit_in_stock</h2>
     * <p>
     * unit_in_stock
     * </p>
     */
    @Min(value = 0, message = "Unit In Stock must be numbers!")
    @Column(name = "unit_in_stock")
    private Integer unit_in_stock;

    /**
     * <h2>amount</h2>
     * <p>
     * amount
     * </p>
     */
    @Column(name = "amount")
    @Min(value = 0, message = "Price must be numbers!")
    private double amount;

    /**
     * <h2>image</h2>
     * <p>
     * image
     * </p>
     */
    @Column(name = "image")
    private String image;
    // private Integer create_user_id;

    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at")
    private Date createdAt;
//	private Integer updated_user_id;
    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updatedAt;
//	private Integer deleted_user_id;

    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deletedAt;

    /**
     * <h2>category</h2>
     * <p>
     * category
     * </p>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = true, referencedColumnName = "category_id")
    public Category category;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, targetEntity = CartDetail.class, fetch = FetchType.LAZY)
    private List<CartDetail> cartDetails = new ArrayList<CartDetail>();

    /**
     * <h2>Constructor for Medicine</h2>
     * <p>
     * Constructor for Medicine
     * </p>
     */
    public Medicine() {
        super();
    }

    /**
     * <h2>Constructor for Medicine</h2>
     * <p>
     * Constructor for Medicine
     * </p>
     * 
     * @param medicineForm
     */
    public Medicine(MedicineForm medicineForm) {
        this.id = medicineForm.getId();
        this.medicine_name = medicineForm.getMedicine_name();
        this.medicine_description = medicineForm.getMedicine_description();
        this.medicine_code = medicineForm.getMedicine_code();
        this.unit_in_stock = medicineForm.getUnit_in_stock();
        this.amount = medicineForm.getAmount();
        this.image = medicineForm.getImage();
        this.createdAt = medicineForm.getCreatedAt();
        this.updatedAt = medicineForm.getUpdatedAt();
        this.deletedAt = medicineForm.getDeletedAt();
        this.category = medicineForm.getCategory();
this.cartDetails=medicineForm.getCartDetails();
        // this.categoryId=medicineForm.getCategoryId();
    }
}