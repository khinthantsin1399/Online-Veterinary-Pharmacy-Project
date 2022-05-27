package scm.ojt.project.persistence.dao.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.persistence.dao.MedicineDao;
import scm.ojt.project.persistence.entity.Category;
import scm.ojt.project.persistence.entity.Medicine;
import scm.ojt.project.web.form.MedicineForm;

/**
 * <h2>MedicineDaoImpl Class</h2>
 * <p>
 * Process for Displaying MedicineDaoImpl
 * </p>
 * 
 * @author khinthantsin
 *
 */
@SuppressWarnings("deprecation")
@Repository
@Transactional
public class MedicineDaoImpl implements MedicineDao {
    @Autowired
    private SessionFactory sessionFactory;

    public static String SELECT_MEDICINE_LIST_HQL = "FROM Medicine m " + "WHERE m.deletedAt IS NULL ";

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static String SELECT_MEDICINE_BY_SEARCH_DATA = "AND (m.medicine_name like :medicine_name ) ";

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
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Medicine> dbGetMedicineListPagi(int currentPage, int noOfMedicine, MedicineForm medicineForm) {
        int start = currentPage * noOfMedicine - noOfMedicine;
        Query queryMedicineList = dbCreateQueryList(medicineForm);
        queryMedicineList.setFirstResult(start);
        queryMedicineList.setMaxResults(noOfMedicine);
        List<Medicine> medicineList = (List<Medicine>) queryMedicineList.list();
        return medicineList;
    }

    /**
     * <h2>dbSearchMedicineList</h2>
     * <p>
     * method to search medicine
     * </p>
     * 
     * @param medicineForm
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Medicine> dbSearchMedicineList(MedicineForm medicineForm) {
        Query queryMedicineList = dbCreateQueryList(medicineForm);

        List<Medicine> medicineList = (List<Medicine>) queryMedicineList.list();
        return medicineList;
    }

    /**
     * <h2>doGetMedicineList</h2>
     * <p>
     * method to get medicine list
     * </p>
     * 
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    @Override
    public List<Medicine> doGetMedicineList() {
        Query<Medicine> queryMedicineList = this.sessionFactory.getCurrentSession()
                .createQuery(SELECT_MEDICINE_LIST_HQL);
        List<Medicine> medicinelist = (List<Medicine>) queryMedicineList.list();
        return medicinelist;

    }

    /**
     * <h2>addMedicine</h2>
     * <p>
     * method to add medicine
     * </p>
     * 
     * @param medicine
     * @param currentDate
     */
    @Override
    public void addMedicine(Medicine medicine, Date currentDate) {

        medicine.setCreatedAt(currentDate);
        medicine.setUpdatedAt(currentDate);
        sessionFactory.getCurrentSession().saveOrUpdate(medicine);

    }

    /**
     * <h2>deleteMedicine</h2>
     * <p>
     * method to delete medicine
     * </p>
     * 
     * @param medicineId
     */
    @Override
    public void deleteMedicine(Integer medicineId) {
        Medicine medicine = (Medicine) sessionFactory.getCurrentSession().load(Medicine.class, medicineId);
        if (null != medicine) {
            this.sessionFactory.getCurrentSession().delete(medicine);
        }
    }

    /**
     * <h2>getMedicineById</h2>
     * <p>
     * method to get medicine by id
     * </p>
     * 
     * @param medicineId
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Medicine getMedicineById(int medicineId) {
        Query queryMedicineById = this.sessionFactory.getCurrentSession()
                .createQuery("SELECT m FROM Medicine m where m.id = :id");
        queryMedicineById.setParameter("id", medicineId);
        Medicine resultMedicine = (Medicine) queryMedicineById.uniqueResult();
        return resultMedicine;

    }

    /**
     * <h2>updateMedicine</h2>
     * <p>
     * method to update medicine
     * </p>
     * 
     * @param medicine
     * @param currentDate
     */
    @Override
    public void updateMedicine(Medicine medicine, Date currentDate) {
        medicine.setUpdatedAt(currentDate);
        this.sessionFactory.getCurrentSession().update(medicine);
    }

    /**
     * <h2>dbCreateQueryList</h2>
     * <p>
     * method to create query list
     * </p>
     *
     * @param medicineForm
     * @return
     * @return Query
     */
    @SuppressWarnings("rawtypes")
    private Query dbCreateQueryList(MedicineForm medicineForm) {
        StringBuffer query = new StringBuffer(SELECT_MEDICINE_LIST_HQL);

        if (medicineForm != null && (medicineForm.getMedicine_name() != null)) {
            query.append(SELECT_MEDICINE_BY_SEARCH_DATA);
        }
        Query queryMedicineList = this.sessionFactory.getCurrentSession().createQuery(query.toString());
        if (medicineForm != null && (medicineForm.getMedicine_name() != null)) {
            queryMedicineList.setParameter("medicine_name", "%" + medicineForm.getMedicine_name() + "%");
        }
        return queryMedicineList;
    }
    
    /**
     * <h2> isMedicineCodeExist</h2>
     * <p>
     * method to check whether medicine code already exists or not
     * </p>
     *
     * @param medCode
     * @return
     * @return Medicine
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Medicine isMedicineCodeExist(String medCode) {
        String HqlQuery = "SELECT m FROM Medicine m where m.medicine_code = :medicine_code";
        Query queryisExist = this.sessionFactory.getCurrentSession().createQuery(HqlQuery);
        queryisExist.setParameter("medicine_code", medCode);
        Medicine resultMedicine = (Medicine) queryisExist.uniqueResult();
        return resultMedicine;
    }
}