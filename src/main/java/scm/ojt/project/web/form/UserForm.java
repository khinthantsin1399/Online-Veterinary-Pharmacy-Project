package scm.ojt.project.web.form;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import scm.ojt.project.persistence.entity.Cart;
import scm.ojt.project.persistence.entity.User;

@Getter
@Setter
@AllArgsConstructor
/**
 * <h2>UserForm Class</h2>
 * <p>
 * Process for Displaying UserForm
 * </p>
 * 
 * @author KyawHtet
 *
 */
public class UserForm {
    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private int id;
    /**
     * <h2>username</h2>
     * <p>
     * username
     * </p>
     */
    @NotEmpty
    private String username;
    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;
    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    @NotEmpty
    private String password;
    /**
     * <h2>type</h2>
     * <p>
     * type
     * </p>
     */
    @NotEmpty
    private String type;
    /**
     * <h2>phone</h2>
     * <p>
     * phone
     * </p>
     */
    @Pattern(regexp = "(09|959)\\d{9,11}")
    @NotEmpty
    private String phone;
    /**
     * <h2>address</h2>
     * <p>
     * address
     * </p>
     */
    @NotEmpty
    private String address;
    /**
     * <h2>created_user_id</h2>
     * <p>
     * created_user_id
     * </p>
     */
    private Integer created_user_id;
    /**
     * <h2>created_at</h2>
     * <p>
     * created_at
     * </p>
     */
    private Date created_at;
    /**
     * <h2>updated_user_id</h2>
     * <p>
     * updated_user_id
     * </p>
     */
    private Integer updated_user_id;
    /**
     * <h2>updated_at</h2>
     * <p>
     * updated_at
     * </p>
     */
    private Date updated_at;

    /**
     * <h2>deleted_user_id</h2>
     * <p>
     * deleted_user_id
     * </p>
     */
    private Integer deleted_user_id;
    /**
     * <h2>deleted_at</h2>
     * <p>
     * deleted_at
     * </p>
     */
    private Date deleted_at;

    private Cart cart;

    /**
     * <h2>Constructor for UserForm</h2>
     * <p>
     * Constructor for UserForm
     * </p>
     */
    public UserForm() {
        super();
    }

    /**
     * <h2>Constructor for UserForm</h2>
     * <p>
     * Constructor for UserForm
     * </p>
     * 
     * @param user
     */
    public UserForm(User user) {
        super();
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.type = user.getType();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.created_user_id = user.getCreated_user_id();
        this.created_at = user.getCreated_at();
        this.updated_user_id = user.getUpdated_user_id();
        this.updated_at = user.getUpdated_at();
        this.deleted_user_id = user.getDeleted_user_id();
        this.deleted_at = user.getDeleted_at();
        this.cart = user.getCart();
    }
}