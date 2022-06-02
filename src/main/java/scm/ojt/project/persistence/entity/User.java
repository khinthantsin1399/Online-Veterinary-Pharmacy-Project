package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scm.ojt.project.web.form.UserForm;

/**
 * <h2>User Class</h2>
 * <p>
 * Process for Displaying User
 * </p>
 * 
 * @author KyawHtet
 *
 */
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {
    /**
     * <h2>serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
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
    private int id;
    /**
     * <h2>username</h2>
     * <p>
     * name
     * </p>
     */
    @Column(name = "username")
    private String username;
    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    @Column(name = "email", unique = true)
    private String email;
    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    @Column(name = "password")
    private String password;
    /**
     * <h2>type</h2>
     * <p>
     * type
     * </p>
     */
    @Column(name = "type")
    private String type;
    /**
     * <h2>phone</h2>
     * <p>
     * phone
     * </p>
     */
    @Column(name = "phone")
    private String phone;
    /**
     * <h2>address</h2>
     * <p>
     * address
     * </p>
     */
    @Column(name = "address")
    private String address;
    /**
     * <h2>created_user_id</h2>
     * <p>
     * create_user_id
     * </p>
     */
    @Column(name = "created_user_id")
    private Integer created_user_id;
    /**
     * <h2>createdAt</h2>
     * <p>
     * createdAt
     * </p>
     */
    @Column(name = "created_at")
    private Date created_at;
    /**
     * <h2>updated_user_id</h2>
     * <p>
     * updated_user_id
     * </p>
     */
    @Column(name = "updated_user_id")
    private Integer updated_user_id;
    /**
     * <h2>updatedAt</h2>
     * <p>
     * updatedAt
     * </p>
     */
    @Column(name = "updated_at")
    private Date updated_at;
    /**
     * <h2>deleted_user_id</h2>
     * <p>
     * deleted_user_id
     * </p>
     */
    @Column(name = "deleted_user_id")
    private Integer deleted_user_id;
    /**
     * <h2>deletedAt</h2>
     * <p>
     * deletedAt
     * </p>
     */
    @Column(name = "deleted_at")
    private Date deleted_at;

    

  
  
   

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = Cart.class, fetch = FetchType.LAZY)
    private Cart cart = new Cart();

  
    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     */
    public User() {
        super();
    }

    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param userForm
     */
    public User(UserForm userForm) {
        super();
        this.id = userForm.getId();
        this.username = userForm.getUsername();
        this.email = userForm.getEmail();
        this.password = userForm.getPassword();
        this.type = userForm.getType();
        this.phone = userForm.getPhone();
        this.address = userForm.getAddress();
        this.created_user_id = userForm.getCreated_user_id();
        this.created_at = userForm.getCreated_at();
        this.updated_user_id = userForm.getUpdated_user_id();
        this.updated_at = userForm.getUpdated_at();
        this.deleted_user_id = userForm.getDeleted_user_id();
        this.deleted_at = userForm.getDeleted_at();
        this.cart = userForm.getCart();
    }
}