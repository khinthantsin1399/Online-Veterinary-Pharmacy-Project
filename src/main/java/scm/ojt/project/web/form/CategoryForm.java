package scm.ojt.project.web.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.persistence.entity.Medicine;

/**
 * <h2>CategoryForm Class</h2>
 * <p>
 * Process for Displaying CategoryForm
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Getter
@Setter
public class CategoryForm {
    /**
     * <h2>category_id</h2>
     * <p>
     * category_id
     * </p>
     */
    private Integer category_id;

    /**
     * <h2>category_code</h2>
     * <p>
     * category_code
     * </p>
     */
    @NotEmpty
    private String category_code;

    /**
     * <h2>category_name</h2>
     * <p>
     * category_name
     * </p>
     */
    @NotEmpty
    private String category_name;

    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;

    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    public Date updatedAt;

    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    public Date deletedAt;
    /**
     * <h2>medicines</h2>
     * <p>
     * medicines
     * </p>
     */
    private List<Medicine> medicines;

    /**
     * <h2>Constructor for CategoryForm</h2>
     * <p>
     * Constructor for CategoryForm
     * </p>
     */
    public CategoryForm() {
        super();
    }

    /**
     * <h2>Constructor for CategoryForm</h2>
     * <p>
     * Constructor for CategoryForm
     * </p>
     * 
     * @param category
     */
    public CategoryForm(Category category) {
        this.category_id = category.getCategory_id();
        this.category_code = category.getCategory_code();
        this.category_name = category.getCategory_name();
        this.createdAt = category.getCreatedAt();
        this.updatedAt = category.getUpdatedAt();
        this.deletedAt = category.getDeletedAt();
        this.medicines = category.getMedicines();
    }
}