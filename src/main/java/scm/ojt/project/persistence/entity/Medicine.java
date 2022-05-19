package scm.ojt.project.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicines")
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String m_name;
	private String m_description;
	private String m_code;
	private int category_id;
	private int unit_in_stock;
	private double amount;
	private String photo;
	private int create_user_id;
	private Date created_at;
	private int updated_user_id;
	private Date updated_at;
	private int deleted_user_id;
	private Date deleted_at;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "m_name")
	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	@Column(name = "m_description")
	public String getM_description() {
		return m_description;
	}

	public void setM_description(String m_description) {
		this.m_description = m_description;
	}

	@Column(name = "m_code")
	public String getM_code() {
		return m_code;
	}

	public void setM_code(String m_code) {
		this.m_code = m_code;
	}

	@Column(name = "category_id")
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Column(name = "unit_in_stock")
	public int getUnit_in_stock() {
		return unit_in_stock;
	}

	public void setUnit_in_stock(int unit_in_stock) {
		this.unit_in_stock = unit_in_stock;
	}

	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "create_user_id")
	public int getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(int create_user_id) {
		this.create_user_id = create_user_id;
	}

	@Column(name = "created_at")
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Column(name = "updated_user_id")
	public int getUpdated_user_id() {
		return updated_user_id;
	}

	public void setUpdated_user_id(int updated_user_id) {
		this.updated_user_id = updated_user_id;
	}

	@Column(name = "updated_at")
	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@Column(name = "deleted_user_id")
	public int getDeleted_user_id() {
		return deleted_user_id;
	}

	public void setDeleted_user_id(int deleted_user_id) {
		this.deleted_user_id = deleted_user_id;
	}

	@Column(name = "deleted_at")
	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

}
