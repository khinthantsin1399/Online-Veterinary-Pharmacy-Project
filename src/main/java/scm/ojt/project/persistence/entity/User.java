package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int id;
	/**
	 * <h2>username</h2>
	 * <p>
	 * name
	 * </p>
	 */
	private String username;
	/**
	 * <h2>email</h2>
	 * <p>
	 * email
	 * </p>
	 */
	private String email;
	/**
	 * <h2>password</h2>
	 * <p>
	 * password
	 * </p>
	 */
	private String password;
	/**
	 * <h2>type</h2>
	 * <p>
	 * type
	 * </p>
	 */
	private String type;
	/**
	 * <h2>phone</h2>
	 * <p>
	 * phone
	 * </p>
	 */
	private String phone;
	/**
	 * <h2>address</h2>
	 * <p>
	 * address
	 * </p>
	 */
	private String address;
	/**
	 * <h2>created_user_id</h2>
	 * <p>
	 * create_user_id
	 * </p>
	 */
	private Integer created_user_id;
	/**
	 * <h2>createdAt</h2>
	 * <p>
	 * createdAt
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
	 * <h2>updatedAt</h2>
	 * <p>
	 * updatedAt
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
	 * <h2>deletedAt</h2>
	 * <p>
	 * deletedAt
	 * </p>
	 */
	private Date deleted_at;

	/**
	 * <h2>getId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * <h2>setId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param id
	 * @return void
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <h2>getUsername</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	/**
	 * <h2>setUsername</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param username
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * <h2>getEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@Column(name = "email", unique = true)
	public String getEmail() {
		return email;
	}

	/**
	 * <h2>setEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return void
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <h2>getPassword</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * <h2>setPassword</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param password
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * <h2>getType</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@Column(name = "type")
	public String getType() {
		return type;
	}

	/**
	 * <h2>setType</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param type
	 * @return void
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <h2>getPhone</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * <h2>setPhone</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param phone
	 * @return void
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * <h2>getAddress</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return String
	 */
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	/**
	 * <h2>setAddress</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param address
	 * @return void
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * <h2>getCreated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Integer
	 */
	@Column(name = "created_user_id")
	public Integer getCreated_user_id() {
		return created_user_id;
	}

	/**
	 * <h2>setCreated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_user_id
	 * @return void
	 */
	public void setCreated_user_id(Integer created_user_id) {
		this.created_user_id = created_user_id;
	}

	/**
	 * <h2>getCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	@Column(name = "created_at")
	public Date getCreated_at() {
		return created_at;
	}

	/**
	 * <h2>setCreated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param created_at
	 * @return void
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	/**
	 * <h2>getUpdated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Integer
	 */
	@Column(name = "updated_user_id")
	public Integer getUpdated_user_id() {
		return updated_user_id;
	}

	/**
	 * <h2>setUpdated_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_user_id
	 * @return void
	 */
	public void setUpdated_user_id(Integer updated_user_id) {
		this.updated_user_id = updated_user_id;
	}

	/**
	 * <h2>getUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	@Column(name = "updated_at")
	public Date getUpdated_at() {
		return updated_at;
	}

	/**
	 * <h2>setUpdated_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updated_at
	 * @return void
	 */
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * <h2>getDeleted_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Integer
	 */
	@Column(name = "deleted_user_id")
	public Integer getDeleted_user_id() {
		return deleted_user_id;
	}

	/**
	 * <h2>setDeleted_user_id</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_user_id
	 * @return void
	 */
	public void setDeleted_user_id(Integer deleted_user_id) {
		this.deleted_user_id = deleted_user_id;
	}

	/**
	 * <h2>getDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return Date
	 */
	@Column(name = "deleted_at")
	public Date getDeleted_at() {
		return deleted_at;
	}

	/**
	 * <h2>setDeleted_at</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param deleted_at
	 * @return void
	 */
	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

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
	}
}