package scm.ojt.project.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.web.form.CategoryForm;

/**<h2> Category Class</h2>
 * <p>
 * Process for Displaying Category
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <h2> category_id</h2>
     * <p>
     * category_id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer category_id;

    /**
     * <h2> category_code</h2>
     * <p>
     * category_code
     * </p>
     */
    @Column(name = "category_code")
    private String category_code;

    /**
     * <h2> category_name</h2>
     * <p>
     * category_name
     * </p>
     */
    @Column(name = "category_name")
    private String category_name;

    /**
     * <h2> createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * <h2> updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    public Date updatedAt;

    /**
     * <h2> deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    public Date deletedAt;

    
    /**
     * <h2> medicines</h2>
     * <p>
     * medicines
     * </p>
     */
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, targetEntity = Medicine.class, fetch = FetchType.LAZY)
    private List<Medicine> medicines = new ArrayList<Medicine>();

    /**
     * <h2> Constructor for Category </h2>
     * <p>
     * Constructor for Category
     * </p>
     */
    public Category() {
        super();
    }

    /**
     * <h2> Constructor for Category </h2>
     * <p>
     * Constructor for Category
     * </p>
     * @param categoryForm
     */
    public Category(CategoryForm categoryForm) {
        this.category_id = categoryForm.getCategory_id();
        this.category_code = categoryForm.getCategory_code();
        this.category_name = categoryForm.getCategory_name();
        this.createdAt = categoryForm.getCreatedAt();
        this.updatedAt = categoryForm.getUpdatedAt();
        this.deletedAt = categoryForm.getDeletedAt();
        this.medicines = categoryForm.getMedicines();
    }
}
