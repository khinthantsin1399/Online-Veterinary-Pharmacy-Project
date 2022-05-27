package scm.ojt.project.bl.dto;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.persistence.entity.Medicine;

/**<h2> MedicineDto Class</h2>
 * <p>
 * Process for Displaying MedicineDto
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Getter
@Setter
public class MedicineDto {
    /**
     * <h2> id</h2>
     * <p>
     * id
     * </p>
     */
    private Integer id;

    /**
     * <h2> medicine_name</h2>
     * <p>
     * medicine_name
     * </p>
     */
    private String medicine_name;

    /**
     * <h2> medicine_description</h2>
     * <p>
     * medicine_description
     * </p>
     */
    private String medicine_description;

    /**
     * <h2> medicine_code</h2>
     * <p>
     * medicine_code
     * </p>
     */
    private String medicine_code;

   // private Integer categoryId;

    /**
     * <h2> unit_in_stock</h2>
     * <p>
     * unit_in_stock
     * </p>
     */
    private Integer unit_in_stock;

    /**
     * <h2> amount</h2>
     * <p>
     * amount
     * </p>
     */
    private double amount;

    /**
     * <h2> image</h2>
     * <p>
     * image
     * </p>
     */
    private String image;
    // private Integer create_user_id;

    /**
     * <h2> createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    private Date createdAt;
//  private Integer updated_user_id;

    /**
     * <h2> updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    private Date updatedAt;
//  private Integer deleted_user_id;

    /**
     * <h2> deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    private Date deletedAt;
    
    /**
     * <h2> category</h2>
     * <p>
     * category
     * </p>
     */
    public Category category ;

    /**
     * <h2> Constructor for MedicineDto </h2>
     * <p>
     * Constructor for MedicineDto
     * </p>
     */
    public MedicineDto() {
        super();
    }
    
    /**
     * <h2> Constructor for MedicineDto </h2>
     * <p>
     * Constructor for MedicineDto
     * </p>
     * @param medicine
     */
    public MedicineDto(Medicine medicine) {
        this.id = medicine.getId();
        this.medicine_name = medicine.getMedicine_name();
        this.medicine_description = medicine.getMedicine_description();
        this.medicine_code = medicine.getMedicine_code();
        this.unit_in_stock = medicine.getUnit_in_stock();
        this.amount = medicine.getAmount();
        this.image = medicine.getImage();
        this.createdAt = medicine.getCreatedAt();
        this.updatedAt = medicine.getUpdatedAt();
        this.deletedAt = medicine.getDeletedAt();
        this.category = medicine.getCategory();
    }
}
