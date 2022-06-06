package scm.ojt.project.persistence.dao;

import java.util.Date;
import java.util.List;

import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.MedicineForm;

/**
 * <h2>MedicineDao Class</h2>
 * <p>
 * Process for Displaying MedicineDao
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface MedicineDao {
	/**
	 * <h2>dbGetMedicineListPagi</h2>
	 * <p>
	 * method to get medicine list with pagination
	 * </p>
	 *
	 * @param currentPage
	 * @param noOfMedicine
	 * @param medicineForm
	 * @return
	 * @return List<Medicine>
	 */
	public List<Medicine> dbGetMedicineListPagi(int currentPage, int noOfMedicine, MedicineForm medicineForm);

	/**
	 * <h2>dbSearchMedicineList</h2>
	 * <p>
	 * method to search medicine list
	 * </p>
	 *
	 * @param medicineForm
	 * @return
	 * @return List<Medicine>
	 */
	public List<Medicine> dbSearchMedicineList(MedicineForm medicineForm);

	/**
	 * <h2>doGetMedicineList</h2>
	 * <p>
	 * method to get medicine list
	 * </p>
	 *
	 * @return
	 * @return List<Medicine>
	 */
	public List<Medicine> doGetMedicineList();

	/**
	 * <h2>addMedicine</h2>
	 * <p>
	 * method to add medicine
	 * </p>
	 *
	 * @param medicine
	 * @param currentDate
	 * @return void
	 */
	public void addMedicine(Medicine medicine, Date currentDate);

	/**
	 * <h2>deleteMedicine</h2>
	 * <p>
	 * method to delete medicine
	 * </p>
	 *
	 * @param medicineId
	 * @return void
	 */
	public void deleteMedicine(Integer medicineId);

	/**
	 * <h2>getMedicineById</h2>
	 * <p>
	 * method to get medicine by id
	 * </p>
	 *
	 * @param medicineId
	 * @return
	 * @return Medicine
	 */
	public Medicine getMedicineById(int medicineId);

	/**
	 * <h2>updateMedicine</h2>
	 * <p>
	 * method to update medicine
	 * </p>
	 *
	 * @param medicine
	 * @param currentDate
	 * @return void
	 */
	public void updateMedicine(Medicine medicine, Date currentDate);

	/**
	 * <h2>isMedicineCodeExist</h2>
	 * <p>
	 * method to check whether medicine code already exists or not
	 * </p>
	 *
	 * @param medCode
	 * @return
	 * @return Medicine
	 */
	public Medicine isMedicineCodeExist(String medCode);

	/**
	 * <h2>dbUpdateMedicine</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param medicine
	 * @return void
	 */
	public void dbUpdateMedicine(Medicine medicine);

	/**
	 * <h2>isMedicineNameExist</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param medName
	 * @return
	 * @return Medicine
	 */
	public Medicine isMedicineNameExist(String medName);
}