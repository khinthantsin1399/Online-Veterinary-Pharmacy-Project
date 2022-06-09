package scm.ojt.project.bl.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import scm.ojt.project.bl.dto.CategoryDto;
import scm.ojt.project.bl.dto.MedicineDto;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.CategoryForm;
import scm.ojt.project.web.form.MedicineForm;

/**<h2> MedicineService Class</h2>
 * <p>
 * Process for Displaying MedicineService
 * </p>
 * 
 * @author khinthantsin
 *
 */
public interface MedicineService {
    /**
     * <h2> doGetMedicineList</h2>
     * <p>
     * Method to get list
     * </p>
     *
     * @return
     * @return List<MedicineDto>
     * @throws IOException 
     */
    public List<MedicineDto> doGetMedicineList() throws IOException;

    /**
     * <h2> addMedicine</h2>
     * <p>
     * method to add medicine 
     * </p>
     *
     * @param medicineForm
     * @param medicineImagePath
     * @throws IOException
     * @return void
     */
    public void addMedicine(MedicineForm medicineForm,String medicineImagePath)throws IOException;

    /**
     * <h2> deleteMedicine</h2>
     * <p>
     * Method to delete medicine
     * </p>
     *
     * @param medicineId
     * @return void
     */
    public void deleteMedicine(Integer medicineId);

    /**
     * <h2> getMedicineById</h2>
     * <p>
     * method to get medicine by id
     * </p>
     *
     * @param medicineId
     * @return
     * @throws IOException
     * @return MedicineForm
     */
    public MedicineForm getMedicineById(int medicineId)throws IOException;

    /**
     * <h2> updateMedicine</h2>
     * <p>
     * method to update medicine
     * </p>
     *
     * @param medicineForm
     * @param userProfilePath
     * @throws IOException
     * @return void
     */
    public void updateMedicine(MedicineForm medicineForm,String userProfilePath)throws IOException;
    
    /**
     * <h2> doSearchMedicineWithPagi</h2>
     * <p>
     * method to search medicine with pagination
     * </p>
     *
     * @param currentPage
     * @param recordsPerPage
     * @param medicineForm
     * @return
     * @return List<MedicineDto>
     */
    public List<MedicineDto> doSearchMedicineWithPagi(int currentPage, int recordsPerPage, MedicineForm medicineForm)throws IOException; 
   
    /**
     * <h2> doSearchMedicineList</h2>
     * <p>
     * method to search medicine
     * </p>
     *
     * @param medicineForm
     * @return
     * @return List<MedicineDto>
     */
    public List<MedicineDto> doSearchMedicineList(MedicineForm medicineForm);
    
    /**
     * <h2>load</h2>
     * <p>
     * method to load data from database
     * </p>
     * 
     * @return ByteArrayInputStream
     */
public ByteArrayInputStream load();

    /**
     * <h2>save</h2>
     * <p>
     * Method to save excel file to database
     * </p>
     * 
     * @param file
     * @return void
     * @throws IOException 
     */
    public String save(MultipartFile file)throws EncryptedDocumentException, InvalidFormatException, IOException;

    /**
     * <h2> isMedicineCodeExist</h2>
     * <p>
     * method to check medicine code exists or not
     * </p>
     *
     * @param medCode
     * @return
     * @return boolean
     */
    public boolean isMedicineCodeExist(String medCode);

    public void doUpdateMedicine(Medicine medicine);

	/**
	 * <h2>isMedicineNameExist</h2>
	 * <p>
	 * method to check medicine name exists or not
	 * </p>
	 * 
	 * @param medName
	 * @return
	 */
	boolean isMedicineNameExist(String medName);

    /**
     * <h2> isUpdateMedicineCodeExist</h2>
     * <p>
     *  method to check updated medicine code exists or not
     * </p>
     *
     * @param medicineCode
     * @param medicineId
     * @return
     * @return boolean
     */
    boolean isUpdateMedicineCodeExist(String medicineCode, int medicineId);

    /**
     * <h2> isUpdateMedicineNameExist</h2>
     * <p>
     *  method to check updated medicine name exists or not
     * </p>
     *
     * @param medicine_name
     * @param id
     * @return
     * @return boolean
     */
    public boolean isUpdateMedicineNameExist(String medicineName, Integer medicineId);
}
