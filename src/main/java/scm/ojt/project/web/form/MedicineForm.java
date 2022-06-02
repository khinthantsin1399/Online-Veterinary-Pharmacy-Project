package scm.ojt.project.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.CartDetail;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.persistence.entity.OrderDetail;

/**
 * <h2>MedicineForm Class</h2>
 * <p>
 * Process for Displaying MedicineForm
 * </p>
 * 
 * @author khinthantsin
 *
 */
@Getter
@Setter
public class MedicineForm {
	/**
	 * <h2>id</h2>
	 * <p>
	 * id
	 * </p>
	 */
	private Integer id;

	/**
	 * <h2>medicine_name</h2>
	 * <p>
	 * medicine_name
	 * </p>
	 */

	@NotBlank(message = "Medicine Name is required")
	private String medicine_name;

	/**
	 * <h2>medicine_description</h2>
	 * <p>
	 * medicine_description
	 * </p>
	 */
	@NotBlank(message = "Medicine Description is required")
	private String medicine_description;

	/**
	 * <h2>medicine_code</h2>
	 * <p>
	 * medicine_code
	 * </p>
	 */

	@NotEmpty
	private String medicine_code;

	/**
	 * <h2>unit_in_stock</h2>
	 * <p>
	 * unit_in_stock
	 * </p>
	 */
	// @Min(value = 0,message = "Invalid Data Entries!")
	// @Digits(integer = 4, fraction = 0, message = "Invalid Data Entries!")
	// @Pattern(regexp = "[0-9]*", message = "Invalid Data Entries!")
	private Integer unit_in_stock;

	/**
	 * <h2>amount</h2>
	 * <p>
	 * amount
	 * </p>
	 */
	@Digits(integer = 10, fraction = 2)
	private double amount;

	/**
	 * <h2>image</h2>
	 * <p>
	 * image
	 * </p>
	 */
	private String image;

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
	private Date updatedAt;

	/**
	 * <h2>deletedAt</h2>
	 * <p>
	 * deletedAt
	 * </p>
	 */
	private Date deletedAt;

    /**
     * <h2>category</h2>
     * <p>
     * category
     * </p>
     */
    public Category category;
    
    private List<CartDetail> cartDetails = new ArrayList<CartDetail>();

	  private OrderDetail orderDetail;

	  public MedicineForm() {
	      super();
	  }
    /**
     * <h2>Constructor for MedicineForm</h2>
     * <p>
     * Constructor for MedicineForm
     * </p>
     * 
     * @param medicine
     */
    public MedicineForm(Medicine medicine) {
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
        this.cartDetails=medicine.getCartDetails();
	      this.orderDetail = medicine.getOrderDetail();
    }
}